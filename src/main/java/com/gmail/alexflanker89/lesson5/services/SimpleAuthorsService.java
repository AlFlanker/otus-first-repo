package com.gmail.alexflanker89.lesson5.services;

import com.gmail.alexflanker89.lesson5.dao.interfaces.AuthorRepository;
import com.gmail.alexflanker89.lesson5.domain.Author;
import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.services.interdaces.AuthorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Transactional
@Service
public class SimpleAuthorsService implements AuthorsService {

    private final AuthorRepository authorRepository;

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author getById(long id) {
        return authorRepository.findById(id);
    }


    @Override
    public Set<Author> getByNameAndLastname(String name, String lastname) {
        return authorRepository.findByNameAndLastname(name, lastname);
    }


    @Override
    public List<Author> getByBooks(Set<Book> books) {
        return authorRepository.findByBook(books);
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
        authorRepository.update(entry);
    }
}
