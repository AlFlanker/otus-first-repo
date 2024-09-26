package com.gmail.alexflanker89.lesson5.services.interdaces;

import com.gmail.alexflanker89.lesson5.domain.Author;
import com.gmail.alexflanker89.lesson5.domain.Book;
import java.util.List;
import java.util.Set;

public interface AuthorsService extends BaseService<Author> {
    Set<Author> getByNameAndLastname(String name, String lastname);
    List<Author> getByBooks(Set<Book> books);

}
