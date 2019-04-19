package com.gmail.alexflanker89.lesson5.dao.repository;

import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByBook(Book book);
}
