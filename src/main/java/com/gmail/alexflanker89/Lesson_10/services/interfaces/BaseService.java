package com.gmail.alexflanker89.Lesson_10.services.interfaces;

import java.util.List;

public interface BaseService<T> {
    List<T> getAll();

    void delete(T entry);

    T save(T entry);

    T update(T entry);

}
