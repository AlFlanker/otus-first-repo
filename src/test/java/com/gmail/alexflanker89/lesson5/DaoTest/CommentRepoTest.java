package com.gmail.alexflanker89.lesson5.DaoTest;

import com.gmail.alexflanker89.lesson5.dao.interfaces.BookRepository;
import com.gmail.alexflanker89.lesson5.dao.interfaces.CommentRepository;
import com.gmail.alexflanker89.lesson5.dao.repository.AuthorRepositoryJpa;
import com.gmail.alexflanker89.lesson5.dao.repository.BookRepositoryJpa;
import com.gmail.alexflanker89.lesson5.dao.repository.CommentRepositoryJpa;
import com.gmail.alexflanker89.lesson5.dao.repository.GenreRepositoryJpa;
import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
@DisplayName("Тестирование методов репозитария комментариев")
@ActiveProfiles("test")
@DataJpaTest
@Import({BookRepositoryJpa.class, GenreRepositoryJpa.class, AuthorRepositoryJpa.class, CommentRepositoryJpa.class})
@ExtendWith(SpringExtension.class)
public class CommentRepoTest {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CommentRepository commentRepository;

    @Test
    public void getAll(){
        List<Comment> comments = commentRepository.findAll();
        Assertions.assertTrue(comments.size() > 0);
    }

    @Test
    @DisplayName("поиск по книге")
    public void getByBook(){
        Book book = bookRepository.findById(1);
        List<Comment> byBook = commentRepository.findByBook(book);
        Assertions.assertEquals(5,byBook.size());
    }
}
