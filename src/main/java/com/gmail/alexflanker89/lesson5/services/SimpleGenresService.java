package com.gmail.alexflanker89.lesson5.services;

import com.gmail.alexflanker89.lesson5.dao.interfaces.GenreRepository;
import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Genre;
import com.gmail.alexflanker89.lesson5.execptions.GenreNotExistException;
import com.gmail.alexflanker89.lesson5.services.interdaces.GenresService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class SimpleGenresService implements GenresService {

    private final GenreRepository genreRepository;

    @Override
    public List<Genre> getAll() {
       return  genreRepository.findAll();

    }

    @Override
    public List<Genre> getAllByBook(Book book) throws GenreNotExistException {
        if (book == null) throw new GenreNotExistException("book not be null");
        return genreRepository.findByBook(book);

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
