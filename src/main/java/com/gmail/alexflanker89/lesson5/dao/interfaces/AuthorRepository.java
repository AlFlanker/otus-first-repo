package com.gmail.alexflanker89.lesson5.dao.interfaces;

import com.gmail.alexflanker89.lesson5.domain.Author;
import com.gmail.alexflanker89.lesson5.domain.Book;
import java.util.List;
import java.util.Set;

public interface AuthorRepository extends BaseRepository<Author>{
    List<Author> findByBook(Set<Book> books);
    Set<Author> findByNameAndLastname(String name, String lastname);


}
