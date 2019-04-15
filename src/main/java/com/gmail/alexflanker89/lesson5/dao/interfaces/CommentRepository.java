package com.gmail.alexflanker89.lesson5.dao.interfaces;

import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Comment;
import java.util.List;

public interface CommentRepository extends BaseRepository<Comment> {
    List<Comment> findByBook(Book book);

}
