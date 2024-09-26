package com.gmail.alexflanker89.Lesson_8_MongoDB.services;

import com.gmail.alexflanker89.Lesson_8_MongoDB.domain.Author;
import com.gmail.alexflanker89.Lesson_8_MongoDB.domain.Book;
import com.gmail.alexflanker89.Lesson_8_MongoDB.repo.AuthorRepo;
import com.gmail.alexflanker89.Lesson_8_MongoDB.repo.BookRepo;
import com.gmail.alexflanker89.Lesson_8_MongoDB.services.interfaces.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceIImpl implements BookService {
    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;
    private final MongoOperations mongoOperations;

    @Override
    public List<Book> getAllByGenres(String line) {
        Set<String> genres;
        if(line.contains(",")){
            genres = new HashSet<String>(Arrays.asList(line.split(",")));
            genres = genres.stream().map(String::trim).collect(Collectors.toSet());
        }
        else{
            genres = Collections.singleton(line);
        }
        Query byGenre = new Query();
        byGenre.addCriteria(Criteria.where("genres").elemMatch(Criteria.where("genreName").in(genres)));
        return mongoOperations.find(byGenre, Book.class);
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
    public List<Book> getAllByAuthorsNameAndLastname(String name, String lastname) {
        Query findAuthors = new Query();
        Query byAuthosNameAndLastname = new Query();
        byAuthosNameAndLastname.addCriteria(Criteria.where("authors").elemMatch(Criteria.where("name").is(name).and("lastname").is(lastname)));
        return mongoOperations.find(byAuthosNameAndLastname, Book.class);

    }

    @Override
    public List<Author> getByBookId(String id) {
        return mongoOperations.find(Query.query(Criteria.where("_id").is(id)), Book.class).stream().flatMap(book -> book.getAuthors().stream()).collect(Collectors.toList());
    }
}
