package com.gmail.alexflanker89.lesson5.services;

import com.gmail.alexflanker89.lesson5.dao.AuthorRepository;
import com.gmail.alexflanker89.lesson5.dao.BookRepository;
import com.gmail.alexflanker89.lesson5.dao.GenreRepository;
import com.gmail.alexflanker89.lesson5.domain.Author;
import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Genre;
import com.gmail.alexflanker89.lesson5.execptions.BookNotExistExeption;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class SimpleBooksService implements BooksService{
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getAllByGenre(Genre genre){
        if(genre == null) throw  new BookNotExistExeption("genre not be null!");
        List<Book> books = bookRepository.findByGenre(genre);
        if(books.equals(Collections.emptyList())) throw  new BookNotExistExeption("books not found by genre" + genre.getKindOf());
        else return books;

    }

    @Override
    public List<Book> getAllByAuthor(Author authors) {
        if(authors == null) throw new BookNotExistExeption("author not be null!");
        List<Book> books = bookRepository.findByAuthor(authors);
        if(books.equals(Collections.emptyList())) throw new BookNotExistExeption("books not found by author " + authors.getName() +" "+ authors.getLastname());
        else return books;
    }

    @Override
    public List<Book> getAllByReleaseDateGreaterThan(LocalDate date) {
        List<Book> books = bookRepository.findByReleaseDateGreaterThan(date);
        if(books.equals(Collections.emptyList())) throw  new BookNotExistExeption("books not found by " +"release date " + date );
        else return books;
    }

    @Override
    public List<Book> getAllByReleaseDateLessThan(LocalDate date) {
        List<Book> books = bookRepository.findByReleaseDateLessThan(date);
        if(books.equals(Collections.emptyList())) throw  new BookNotExistExeption("books not found by " +"release date " + date );
        else return books;
    }

    @Override
    public List<Book> getAllByReleaseDate(LocalDate date) {
        List<Book> books = bookRepository.findByReleaseDate(date);
        if(books.equals(Collections.emptyList())) throw  new BookNotExistExeption("books not found by " +"release date " + date );
        else return books;
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void updateBook(Book book) {
         bookRepository.update(book);
    }

    @Override
    public int deleteBook(Book book) {
        return bookRepository.deleteById(book.getId());
    }



    @Override
    public Book getDetailInfoByID(long id) throws BookNotExistExeption{
        try {
            Book book = bookRepository.findById(id);
            book.setGenres(new HashSet<>(genreRepository.findAllByBook(book)));
            book.setAuthors(new HashSet<>(authorRepository.findAllByBook(book)));
            return book;
        }
        catch (EmptyResultDataAccessException e){ throw new BookNotExistExeption("not found book with id " + id);}

    }
}
