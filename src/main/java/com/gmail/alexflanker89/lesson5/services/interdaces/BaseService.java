package com.gmail.alexflanker89.lesson5.services.interdaces;

import java.util.List;

public interface BaseService<T> {
    T getById(long id);
    List<T> getAll();
    void delete(T entry);
    void save(T entry);
    void update(T entry);

}
