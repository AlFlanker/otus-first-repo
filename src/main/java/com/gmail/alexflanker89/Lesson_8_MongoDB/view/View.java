package com.gmail.alexflanker89.Lesson_8_MongoDB.view;


import com.gmail.alexflanker89.Lesson_8_MongoDB.domain.Author;
import com.gmail.alexflanker89.Lesson_8_MongoDB.domain.Book;
import com.gmail.alexflanker89.Lesson_8_MongoDB.domain.Comment;
import com.gmail.alexflanker89.Lesson_8_MongoDB.domain.Genre;

import java.util.Collection;

public interface View {
    void showGenres(Collection<Genre> genreList);
    void showBooks(Collection<Book> books);
    void showAuthors(Collection<Author> authors);
    void showMessage(java.lang.String message);
    void showBook(Book book);
    void shortShowBook(Book book);
    void showComments(Collection<Comment> comments);
}
