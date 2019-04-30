package com.gmail.alexflanker89.Lesson_8_MongoDB.services.interfaces;

import com.gmail.alexflanker89.Lesson_8_MongoDB.domain.Author;
import com.gmail.alexflanker89.Lesson_8_MongoDB.domain.Book;
import com.gmail.alexflanker89.Lesson_8_MongoDB.domain.Genre;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface LibraryService extends BaseService<Book>,AuthorService,CommentService{
    List<Book> getAllByGenres(Set<Genre> genres);
    List<Book> getAllByAuthors(Set<Author> authors);
    List<Book> getAllByReleaseDateGreaterThan(LocalDate date);
    List<Book> getAllByReleaseDateLessThan(LocalDate date);
    List<Book> getAllByReleaseDate(LocalDate date);
}
