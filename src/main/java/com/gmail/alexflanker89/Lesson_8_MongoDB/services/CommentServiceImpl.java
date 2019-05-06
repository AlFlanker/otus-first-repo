package com.gmail.alexflanker89.Lesson_8_MongoDB.services;

import com.gmail.alexflanker89.Lesson_8_MongoDB.domain.Book;
import com.gmail.alexflanker89.Lesson_8_MongoDB.domain.Comment;
import com.gmail.alexflanker89.Lesson_8_MongoDB.repo.BookRepo;
import com.gmail.alexflanker89.Lesson_8_MongoDB.services.interfaces.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
   private final MongoOperations mongoOperations;
   private final BookRepo bookRepo;


    @Override
    public void addComment(String book_id, Comment comment) {
        Book book = mongoOperations.findOne(Query.query(Criteria.where("_id").is(book_id)), Book.class);
        book.getComments().add(comment);
        bookRepo.save(book);
    }

    @Override
    public List<Comment> getAllCommentByBookId(String book_id) {
        Optional<Book> book = bookRepo.findById(book_id);
        if(book.isPresent()){
            return book.get().getComments();
        } else return Collections.emptyList();

    }
}
