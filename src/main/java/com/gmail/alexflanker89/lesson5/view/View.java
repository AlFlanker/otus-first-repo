package com.gmail.alexflanker89.lesson5.view;

import com.gmail.alexflanker89.lesson5.domain.Author;
import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Comment;
import com.gmail.alexflanker89.lesson5.domain.Genre;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface View {
    void showGenres(Collection<Genre> genreList);
    void showBooks(Collection<Book> books);
    void showAuthors(Collection<Author> authors);
    void showMessage(String message);
    void showBook(Book book);
    void shortShowBook(Book book);
    void showComments(Collection<Comment> comments);
}
