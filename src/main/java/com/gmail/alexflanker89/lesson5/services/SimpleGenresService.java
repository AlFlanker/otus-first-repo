package com.gmail.alexflanker89.lesson5.services;

import com.gmail.alexflanker89.lesson5.dao.interfaces.GenreRepository;
import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Genre;
import com.gmail.alexflanker89.lesson5.execptions.GenreNotExistException;
import com.gmail.alexflanker89.lesson5.services.interdaces.GenresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Transactional
@Service
public class SimpleGenresService implements GenresService {

    private final GenreRepository genreRepository;

    @Autowired
    public SimpleGenresService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> getAll() throws GenreNotExistException {
        List<Genre> genres = genreRepository.findAll();
        if (genres.equals(Collections.emptyList())) throw new GenreNotExistException("not found!");
        else return genres;
    }

    @Override
    public List<Genre> getAllByBook(Book book) throws GenreNotExistException {
        if (book == null) throw new GenreNotExistException("book not be null");
        List<Genre> genres = genreRepository.findByBook(book);
        if (genres.equals(Collections.emptyList()))
            throw new GenreNotExistException("not found by book_id = " + book.getId());
        else return genres;
    }

    @Override
    public Genre getByGenreName(String name) {
        return genreRepository.findByName(name);
    }

    @Override
    public Genre getById(long id) {
        return genreRepository.findById(id);
    }

    @Override
    public void delete(Genre genre) {
        genreRepository.delete(genre);
    }

    @Override
    public void save(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    public void update(Genre genre) {
        genreRepository.update(genre);
    }


}
