package com.gmail.alexflanker89.lesson.services;

import com.gmail.alexflanker89.lesson.domain.Book;
import com.gmail.alexflanker89.lesson.domain.Genre;
import com.gmail.alexflanker89.lesson.repo.GenreRepo;
import com.gmail.alexflanker89.lesson.services.interfaces.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepo genreRepo;
    private final MongoOperations mongoOperations;

    @Override
    public List<Genre> getAll() {
        return genreRepo.findAll();
    }

    @Override
    public List<Genre> getAllGenreByBook(String id) {
        if (StringUtils.isEmpty(id)) return getAll();
        else
            return Optional.of((Objects.requireNonNull(mongoOperations.findOne(Query.query(Criteria.where("id").is(id).and("genres").exists(true)), Book.class))).getGenres()).orElse(Collections.emptyList());
    }
}
