package com.gmail.alexflanker89.lesson5.services;

import com.gmail.alexflanker89.lesson5.dao.repository.CommentRepository;
import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Comment;
import com.gmail.alexflanker89.lesson5.execptions.CommentNotExistException;
import com.gmail.alexflanker89.lesson5.services.interdaces.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class SimpleCommentService implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public List<Comment> getByBook(Book book) throws CommentNotExistException {
        List<Comment> comments = commentRepository.findByBook(book);
        if(comments.isEmpty()) throw new CommentNotExistException("нет комментариев");
        return comments;
    }

    @Override
    public Comment getById(long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void update(Comment comment) {
        commentRepository.save(comment);
    }
}
