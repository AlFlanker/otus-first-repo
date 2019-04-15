package com.gmail.alexflanker89.lesson5.dao.interfaces;

import java.util.List;

public interface BaseRepository<T> {
    T findById(long id);
    List<T> findAll();
    void delete(T entry);
    void save(T entry);
    void update(T entry);

}
