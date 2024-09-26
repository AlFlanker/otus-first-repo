package com.gmail.alexflanker89.lesson5.services;


import com.gmail.alexflanker89.lesson5.dao.interfaces.BookRepository;
import com.gmail.alexflanker89.lesson5.domain.Author;
import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Genre;
import com.gmail.alexflanker89.lesson5.services.interdaces.BooksService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Transactional
@Service
public class SimpleBooksService implements BooksService {
    private final BookRepository bookRepository;

    @Override
    public List<Book> getAllByGenres(Set<Genre> genres){
        return bookRepository.findByGernes(genres);
    }

    @Override
    public Set<Book> getAllByAuthors(Set<Author> authors){
        return bookRepository.findByAuthors(authors);
    }

    @Override
    public Set<Book> getAllByReleaseDateGreaterThan(LocalDate date)  {
        return bookRepository.findAllByReleaseDateGreaterThan(date);
    }

    @Override
    public Set<Book> getAllByReleaseDateLessThan(LocalDate date) {
        return bookRepository.findAllByReleaseDateLessThan(date);

    }

    @Override
    public Set<Book> getAllByReleaseDate(LocalDate date) {
        return bookRepository.findAllByReleaseDate(date);
    }

    @Override
    public Book getById(long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public void delete(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void update(Book book) {
        bookRepository.update(book);
    }
}
