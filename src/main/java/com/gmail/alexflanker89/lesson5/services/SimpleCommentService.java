package com.gmail.alexflanker89.lesson5.services;

import com.gmail.alexflanker89.lesson5.dao.interfaces.CommentRepository;
import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Comment;
import com.gmail.alexflanker89.lesson5.execptions.CommentNotExistException;
import com.gmail.alexflanker89.lesson5.services.interdaces.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Transactional
@Service
public class SimpleCommentService implements CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public SimpleCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getByBook(Book book) throws CommentNotExistException {
        List<Comment> comments = commentRepository.findByBook(book);
        if(comments.equals(Collections.emptyList())) throw new CommentNotExistException("нет комментариев");
        return comments;
    }

    @Override
    public Comment getById(long id) {
        return commentRepository.findById(id);
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
        commentRepository.update(comment);
    }
}
