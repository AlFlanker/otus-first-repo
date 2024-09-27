package com.gmail.alexflanker89.lesson.services.interfaces;

import com.gmail.alexflanker89.lesson.domain.Book;
import com.gmail.alexflanker89.lesson.domain.Comment;
import com.gmail.alexflanker89.lesson.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    Book addComment(String bookid, CommentDTO comment);

    Book removeComment(String book, String comment);

    List<Comment> getAllCommentByBookId(String bookid);
}
