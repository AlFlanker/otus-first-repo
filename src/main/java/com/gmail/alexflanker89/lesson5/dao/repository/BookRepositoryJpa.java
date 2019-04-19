package com.gmail.alexflanker89.lesson5.dao.repository;

import com.gmail.alexflanker89.lesson5.dao.interfaces.BookRepository;
import com.gmail.alexflanker89.lesson5.domain.Author;
import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Genre;
import com.gmail.alexflanker89.lesson5.execptions.BookNotExistExeption;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("JpaQlInspection")
@Transactional
@Repository
public class BookRepositoryJpa extends BaseRepositoryImpl<Book> implements BookRepository {
    public BookRepositoryJpa(EntityManager entityManager) {
        super(entityManager);
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    @Override
    public List<Book> findByGernes(Set<Genre> genres)  throws BookNotExistExeption {
        try{
            return entityManager.createQuery("select distinct b from Book b inner join b.genres g where g in (:genres)")
                    .setParameter("genres", genres).getResultList();
        }
        catch (IllegalStateException e){
            throw new BookNotExistExeption(" нет такой книги!");
        }
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    @Override
    public Set<Book> findByAuthors(Set<Author> authors) throws BookNotExistExeption {
        HashSet books = new HashSet();
        try{
            books = new HashSet<>(entityManager.createQuery("select distinct b from Book b inner join b.authors a where a in (:authors)")
                    .setParameter("authors", authors).getResultList());
        }
        catch (IllegalStateException e){
            throw new BookNotExistExeption(" нет такой книги!");
        }
        return books;
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    @Override
    public Set<Book> findByTitle(String title) {
        return new HashSet<>(entityManager.createQuery("select distinct b from Book b where b.title = :title")
                .setParameter("title",title)
                .getResultList());
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    @Override
    public Set<Book> findAllByReleaseDateGreaterThan(LocalDate date) {
        return new HashSet<>(entityManager.createQuery("select distinct b from Book b where b.releaseDate > :date")
                .setParameter("date",date)
                .getResultList());
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    @Override
    public Set<Book> findAllByReleaseDateLessThan(LocalDate date) {
        return new HashSet<>(entityManager.createQuery("select distinct b from Book b where b.releaseDate < :date")
                .setParameter("date",date)
                .getResultList());
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    @Override
    public Set<Book> findAllByReleaseDate(LocalDate date) {
        return new HashSet<>(entityManager.createQuery("select distinct b from Book b where b.releaseDate = :date")
                .setParameter("date",date)
                .getResultList());
    }
}
