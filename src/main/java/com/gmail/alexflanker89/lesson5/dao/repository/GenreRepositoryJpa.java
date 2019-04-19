package com.gmail.alexflanker89.lesson5.dao.repository;

import com.gmail.alexflanker89.lesson5.dao.interfaces.GenreRepository;
import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Genre;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Transactional
@SuppressWarnings("JpaQlInspection")
@Repository
public class GenreRepositoryJpa extends BaseRepositoryImpl<Genre> implements GenreRepository {
    public GenreRepositoryJpa(EntityManager entityManager) {
        super(entityManager);
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<Genre> findByBook(Book book) {
        return entityManager.createQuery("select b.genres from Book b where b = :book").setParameter("book", book).getResultList();
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public Genre findByName(String name) throws NoResultException {
        try {
            return (Genre) entityManager.createQuery("select g from Genre g where g.genreName = :name").setParameter("name", name).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }
}
