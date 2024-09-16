package com.gmail.alexflanker89.lesson5.services;

import com.gmail.alexflanker89.lesson5.dao.repository.BookRepository;
import com.gmail.alexflanker89.lesson5.domain.Author;
import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Genre;
import com.gmail.alexflanker89.lesson5.execptions.BookNotExistExeption;
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
    public List<Book> getAllByGenres(Set<Genre> genres) throws BookNotExistExeption{
        List<Book> books = bookRepository.findByGenresIn(genres);
        if(books.isEmpty()) throw new BookNotExistExeption("нет книг с таким жанром");
        return books;
    }

    @Override
    public Set<Book> getAllByAuthors(Set<Author> authors) throws BookNotExistExeption{
        Set<Book> byAuthors = bookRepository.findByAuthorsIn(authors);
        if(byAuthors.isEmpty()) throw new BookNotExistExeption("нет книг такого автора!");
        return byAuthors;
    }

    @Override
    public Set<Book> getAllByReleaseDateGreaterThan(LocalDate date) throws BookNotExistExeption {
        Set<Book> books = bookRepository.findAllByReleaseDateGreaterThan(date);
        if(books.isEmpty()) throw new BookNotExistExeption("нет книг!");
        return books;
    }

    @Override
    public Set<Book> getAllByReleaseDateLessThan(LocalDate date) throws BookNotExistExeption {
        Set<Book> books = bookRepository.findAllByReleaseDateLessThan(date);
        if(books.isEmpty()) throw new BookNotExistExeption("нет книг!");
        return books;
    }

    @Override
    public Set<Book> getAllByReleaseDate(LocalDate date)throws BookNotExistExeption {
        Set<Book> books = bookRepository.findAllByReleaseDate(date);
        if(books.isEmpty()) throw new BookNotExistExeption("нет книг!");
        return books;
    }

    @Override
    public Book getById(long id) {
        return bookRepository.findById(id).orElse(null);
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
        bookRepository.save(book);
    }
}
