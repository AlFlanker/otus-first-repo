package com.gmail.alexflanker89.Lesson_8_MongoDB.services.interfaces;

import java.util.List;

public interface BaseService<T> {
    List<T> getAll();
    void delete(T entry);
    T save(T entry);
    T update(T entry);

}
