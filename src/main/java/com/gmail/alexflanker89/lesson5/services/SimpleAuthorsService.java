package com.gmail.alexflanker89.lesson5.services;

import com.gmail.alexflanker89.lesson5.dao.AuthorRepository;
import com.gmail.alexflanker89.lesson5.domain.Author;
import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.execptions.AuthorNotExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class SimpleAuthorsService implements AuthorsService {
    private final AuthorRepository authorRepository;

    @Override
    public List<Author> getAll() throws AuthorNotExistException {
        List<Author> authors = authorRepository.findAll();
        if(authors.equals(Collections.emptyList())) throw new AuthorNotExistException("not found!");
        else return authors;
    }

    @Override
    public Author getById(long id) {
        Author author = authorRepository.findById(id);
        if(author ==null)throw new AuthorNotExistException("not found!");
        else return author;
    }

    @Override
    public List<Author> getByName(String name) {
        List<Author> authors = authorRepository.findByName(name);
        if(authors.equals(Collections.emptyList()))throw new AuthorNotExistException("not found!");
        else return authors;
    }

    @Override
    public List<Author> getByLastname(String lastname) {
        List<Author> authors = authorRepository.findByLastname(lastname);
        if(authors.equals(Collections.emptyList()))throw new AuthorNotExistException("not found!");
        else return authors;
    }

    @Override
    public Author getByNameAndLastname(String name, String lastname) {
        Author author = authorRepository.findByNameAndLastname(name, lastname);
        if(author ==null)throw new AuthorNotExistException("not found!");
        else return author;
    }

    @Override
    public List<Author> getAllByDateOfBirthGreaterThan(LocalDate date) throws AuthorNotExistException {
        List<Author> authors = authorRepository.findByDateOfBirthGreaterThan(date);
        if(authors.equals(Collections.emptyList())) throw new AuthorNotExistException("authors not found!");
        else return authors;
    }

    @Override
    public List<Author> getAllByDateOfBirthLessThan(LocalDate date) {
        List<Author> authors = authorRepository.findByDateOfBirthLessThan(date);
        if(authors.equals(Collections.emptyList())) throw new AuthorNotExistException("authors not found!");
        else return authors;
    }

    @Override
    public List<Author> getAllByDateOfBirth(LocalDate date) {
        List<Author> authors = authorRepository.findByDateOfBirth(date);
        if(authors.equals(Collections.emptyList())) throw new AuthorNotExistException("authors not found!");
        else return authors;
    }

    @Override
    public List<Author> getAllByBook(Book book) throws AuthorNotExistException {
        List<Author> authors = authorRepository.findAllByBook(book);
        if(authors.equals(Collections.emptyList())) throw new AuthorNotExistException("not found authos by book_id = " + book.getId());
        else return authors;
    }
}
