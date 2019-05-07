package com.gmail.alexflanker89.Lesson_10.view;


import com.gmail.alexflanker89.Lesson_10.domain.Author;
import com.gmail.alexflanker89.Lesson_10.domain.Book;
import com.gmail.alexflanker89.Lesson_10.domain.Comment;
import com.gmail.alexflanker89.Lesson_10.domain.Genre;

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
