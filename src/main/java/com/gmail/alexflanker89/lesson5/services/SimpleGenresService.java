package com.gmail.alexflanker89.lesson5.services;

import com.gmail.alexflanker89.lesson5.dao.GenreRepository;
import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Genre;
import com.gmail.alexflanker89.lesson5.execptions.GenreNotExistException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class SimpleGenresService implements GenresService {

    private final GenreRepository genreRepository;

    public SimpleGenresService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> getAll() throws GenreNotExistException{
        List<Genre> genres = genreRepository.findAll();
        if(genres.equals(Collections.emptyList())) throw new GenreNotExistException("not found!");
        else return genres;
    }

    @Override
    public List<Genre> getAllByBook(Book book) throws GenreNotExistException {
        if(book ==null) throw new GenreNotExistException("book not be null");
        List<Genre> genres = genreRepository.findAllByBook(book);
        if(genres.equals(Collections.emptyList())) throw new GenreNotExistException("not found by book_id = " + book.getId());
        else return genres;
    }
}
