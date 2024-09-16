package com.gmail.alexflanker89.lesson5.services;


import com.gmail.alexflanker89.lesson5.dao.repository.AuthorRepository;
import com.gmail.alexflanker89.lesson5.domain.Author;
import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.execptions.AuthorNotExistException;
import com.gmail.alexflanker89.lesson5.services.interdaces.AuthorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@RequiredArgsConstructor
@Transactional
@Service
public class SimpleAuthorsService implements AuthorsService {

    private final AuthorRepository authorRepository;


    @Override
    public List<Author> getAll() throws AuthorNotExistException {
        List<Author> authors = authorRepository.findAll();
        if (authors.isEmpty()) throw new AuthorNotExistException("not found!");
        else return authors;
    }

    @Override
    public Author getById(long id) {
        Author author = authorRepository.findById(id).orElse(null);
        if (Objects.isNull(author)) throw new AuthorNotExistException("not found!");
        else return author;
    }


    @Override
    public Set<Author> getByNameAndLastname(String name, String lastname) {
        Set<Author> authors = authorRepository.findAllByNameAndLastname(name, lastname);
        if (authors.isEmpty()) throw new AuthorNotExistException("not found!");
        else return authors;
    }


    @Override
    public List<Author> getByBooks(Set<Book> books) throws AuthorNotExistException {
        List<Author> authors = authorRepository.findByBooks(books);
        if (authors.isEmpty()) throw new AuthorNotExistException("not found authors");
        else return authors;
    }

    @Override
    public void delete(Author entry) {
        authorRepository.delete(entry);
    }

    @Override
    public void save(Author entry) {
        authorRepository.save(entry);
    }

    @Override
    public void update(Author entry) {
        authorRepository.save(entry);
    }
}
