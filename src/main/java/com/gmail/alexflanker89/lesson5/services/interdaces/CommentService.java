package com.gmail.alexflanker89.lesson5.services.interdaces;

import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Comment;
import java.util.List;

public interface CommentService extends BaseService<Comment> {
    List<Comment> getByBook(Book book);
}
