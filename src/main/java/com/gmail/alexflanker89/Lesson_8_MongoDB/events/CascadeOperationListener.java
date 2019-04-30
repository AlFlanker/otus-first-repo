package com.gmail.alexflanker89.Lesson_8_MongoDB.events;

import com.gmail.alexflanker89.Lesson_8_MongoDB.domain.Book;
import com.gmail.alexflanker89.Lesson_8_MongoDB.events.callback.CascadeDeleteCallback;
import com.gmail.alexflanker89.Lesson_8_MongoDB.events.callback.CascadeSaveCallback;
import com.gmail.alexflanker89.Lesson_8_MongoDB.repo.AuthorRepo;
import com.gmail.alexflanker89.Lesson_8_MongoDB.repo.BookRepo;
import com.gmail.alexflanker89.Lesson_8_MongoDB.repo.CommentRepo;
import com.gmail.alexflanker89.Lesson_8_MongoDB.repo.GenreRepo;
import lombok.AllArgsConstructor;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.util.List;

@Component
@SuppressWarnings("unchecked")
@AllArgsConstructor
public class CascadeOperationListener extends AbstractMongoEventListener<Object> {
    private final CommentRepo commentRepo;
    private final GenreRepo genreRepo;
    private final AuthorRepo authorRepo;
    private final BookRepo bookRepo;
    private final MongoOperations mongoOperations;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        super.onBeforeConvert(event);
        Object source = event.getSource();
        ReflectionUtils.doWithFields(source.getClass(),
                new CascadeSaveCallback(source, mongoOperations));

    }


    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Object> event) {
        super.onBeforeDelete(event);
        Document document = event.getDocument();
        if (!document.get("_id").getClass().equals(Document.class)) {
            ObjectId id = document.get("_id", ObjectId.get().getClass());
            List<Book> deletedBooks = mongoOperations.find(Query.query(Criteria.where("_id").is(id)), Book.class);
            deletedBooks.forEach(book -> {
                ReflectionUtils.doWithFields(event.getType(),
                        new CascadeDeleteCallback(book, mongoOperations));
            });
        } else {
            List<ObjectId> list = (List) document.get("_id", Document.class).get("$in");
            Document obj = document.get("_id", Document.class);
            List<Book> deletedBooks = mongoOperations.find(Query.query(Criteria.where("_id").in(list)), Book.class);
            deletedBooks.forEach(book -> {
                ReflectionUtils.doWithFields(event.getType(),
                        new CascadeDeleteCallback(book, mongoOperations));
            });
        }


    }
}
