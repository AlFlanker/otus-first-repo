package com.gmail.alexflanker89.lesson5.dao.interfaces;

import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Genre;
import java.util.List;

public interface GenreRepository extends BaseRepository<Genre> {
    List<Genre> findByBook(Book book);
    Genre findByName(String name);

}
