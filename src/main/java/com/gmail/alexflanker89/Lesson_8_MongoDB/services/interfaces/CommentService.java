package com.gmail.alexflanker89.Lesson_8_MongoDB.services.interfaces;

import com.gmail.alexflanker89.Lesson_8_MongoDB.domain.Comment;

import java.util.List;

public interface CommentService {
    void addComment(String book_id, Comment comment);
    List<Comment> getAllCommentByBookId(String book_id);
}
