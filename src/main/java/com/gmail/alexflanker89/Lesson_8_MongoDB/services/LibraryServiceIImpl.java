package com.gmail.alexflanker89.Lesson_8_MongoDB.services;

import com.gmail.alexflanker89.Lesson_8_MongoDB.domain.Author;
import com.gmail.alexflanker89.Lesson_8_MongoDB.domain.Book;
import com.gmail.alexflanker89.Lesson_8_MongoDB.domain.Comment;
import com.gmail.alexflanker89.Lesson_8_MongoDB.domain.Genre;
import com.gmail.alexflanker89.Lesson_8_MongoDB.repo.AuthorRepo;
import com.gmail.alexflanker89.Lesson_8_MongoDB.repo.BookRepo;
import com.gmail.alexflanker89.Lesson_8_MongoDB.repo.CommentRepo;
import com.gmail.alexflanker89.Lesson_8_MongoDB.services.interfaces.LibraryService;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class LibraryServiceIImpl implements LibraryService {
    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;
    private final CommentRepo commentRepo;
    private final MongoOperations mongoOperations;

    @Override
    public List<Book> getAllByGenres(Set<Genre> genres) {
        return bookRepo.findByGenresIn(genres);
    }

    @Override
    public List<Book> getAllByAuthors(Set<Author> authors) {
        return bookRepo.findByAuthorsIn(authors);
    }

    @Override
    public List<Book> getAllByReleaseDateGreaterThan(LocalDate date) {
        return bookRepo.findByReleaseDateGreaterThan(date);
    }

    @Override
    public List<Book> getAllByReleaseDateLessThan(LocalDate date) {
        return bookRepo.findByReleaseDateBefore(date);
    }

    @Override
    public List<Book> getAllByReleaseDate(LocalDate date) {
        return bookRepo.findByReleaseDate(date);
    }

    @Override
    public List<Book> getAll() {
        return bookRepo.findAll();
    }

    @Override
    public void delete(Book entry) {
        bookRepo.delete(entry);
    }

    @Override
    public Book save(Book entry) {
        return bookRepo.save(entry);
    }

    @Override
    public Book update(Book book) {
        Book bookFromDb = mongoOperations.findOne(Query.query(Criteria.where("_id").is(book.getId())), Book.class);
        if (bookFromDb != null) return bookRepo.save(book);
        return null;
    }

    @Override
    public List<Author> getByNameAndLastname(String name, String lastname) {
        return authorRepo.findByNameAndLastname(name, lastname);
    }

    @Override
    public List<Author> getAllAuthor() {
        return authorRepo.findAll();
    }

    @Override
    public void addCommit(Book book, Comment comment) {
        book.getComments().add(comment);
        bookRepo.save(book);
    }
}
