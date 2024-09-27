package com.gmail.alexflanker89.lesson.services;

import com.gmail.alexflanker89.lesson.domain.Book;
import com.gmail.alexflanker89.lesson.domain.Comment;
import com.gmail.alexflanker89.lesson.dto.CommentDTO;
import com.gmail.alexflanker89.lesson.exceptions.book.BookNotFoundExceptions;
import com.gmail.alexflanker89.lesson.repo.BookRepo;
import com.gmail.alexflanker89.lesson.services.interfaces.BookService;
import com.gmail.alexflanker89.lesson.services.interfaces.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final BookRepo bookRepo;
    private final BookService bookService;

    @Override
    public Book addComment(String bookid, CommentDTO dto) {
        Book book = bookRepo.findById(bookid).orElseThrow(BookNotFoundExceptions::new);
        Comment comment = new Comment();
        comment.setComment(dto.getComment());
        comment.setCreated(LocalDateTime.now());
        comment.setUsername(dto.getUsername());
        book.getComments().add(comment);
        return bookService.update(book);
    }

    @Override
    public Book removeComment(String bookid, String comment) {
        Book book = bookRepo.findById(bookid).orElseThrow(BookNotFoundExceptions::new);
        book.getComments().stream().filter(c -> c.getId().equals(comment)).findFirst().ifPresent(c -> book.getComments().remove(c));
        return bookService.update(book);
    }

    @Override
    public List<Comment> getAllCommentByBookId(String book_id) {
        Optional<Book> book = bookRepo.findById(book_id);
        if (book.isPresent()) {
            return book.get().getComments();
        } else return Collections.emptyList();

    }
}
