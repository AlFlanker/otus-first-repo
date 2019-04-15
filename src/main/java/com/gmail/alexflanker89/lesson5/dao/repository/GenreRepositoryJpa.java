package com.gmail.alexflanker89.lesson5.dao.repository;

import com.gmail.alexflanker89.lesson5.dao.interfaces.GenreRepository;
import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Genre;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
public class GenreRepositoryJpa extends BaseRepositoryImpl<Genre> implements GenreRepository {
    public GenreRepositoryJpa(EntityManager entityManager) {
        super(entityManager);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public List<Genre> findByBook(Book book) {
        return entityManager.createQuery("select b.genres from Book b where b = :book").setParameter("book",book).getResultList();
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public Genre findByName(String name) {
        TypedQuery<Genre> query =(TypedQuery) entityManager.createQuery("select g from Genre g where g.genreName = :name").setParameter("name", name);
        return query.getSingleResult();
    }
}
