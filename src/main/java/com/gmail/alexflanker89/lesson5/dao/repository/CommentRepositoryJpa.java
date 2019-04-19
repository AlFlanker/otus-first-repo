package com.gmail.alexflanker89.lesson5.dao.repository;

import com.gmail.alexflanker89.lesson5.dao.interfaces.CommentRepository;
import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Comment;
import com.gmail.alexflanker89.lesson5.execptions.BookNotExistExeption;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Transactional
@SuppressWarnings("JpaQlInspection")
@Repository
public class CommentRepositoryJpa extends BaseRepositoryImpl<Comment> implements CommentRepository {
    public CommentRepositoryJpa(EntityManager entityManager) {
        super(entityManager);
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    @Override
    public List<Comment> findByBook(Book book) {
        try {
            return entityManager.createQuery("select c from Comment c where c.book = :book").setParameter("book", book).getResultList();
        } catch (NoResultException e) {
            throw new BookNotExistExeption("Книга не найдена");
        }
    }
}
