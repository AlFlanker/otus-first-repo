package com.gmail.alexflanker89.Lesson_8_MongoDB.services.interfaces;

import com.gmail.alexflanker89.Lesson_8_MongoDB.domain.Book;
import com.gmail.alexflanker89.Lesson_8_MongoDB.domain.Comment;

public interface CommentService {
    void addCommit(Book book,Comment comment);
}
