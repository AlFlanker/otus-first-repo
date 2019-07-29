package com.gmail.alexflanker89.Lesson12.services.interfaces;

import com.gmail.alexflanker89.Lesson12.domain.Book;
import com.gmail.alexflanker89.Lesson12.domain.Comment;
import com.gmail.alexflanker89.Lesson12.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    Book addComment(String bookid, CommentDTO comment);

    Book removeComment(String book, String comment);

    List<Comment> getAllCommentByBookId(String bookid);
}
