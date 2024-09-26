package com.gmail.alexflanker89.lesson11.events;

import com.gmail.alexflanker89.lesson11.domain.Book;
import com.gmail.alexflanker89.lesson11.events.callback.CascadeDeleteCallback;
import com.gmail.alexflanker89.lesson11.events.callback.CascadeSaveCallback;
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
import java.util.Objects;

@Component
@SuppressWarnings("unchecked")
@AllArgsConstructor
public class CascadeOperationListener extends AbstractMongoEventListener<Object> {

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
        final Object _id = document.get("_id");
        if (Objects.nonNull(_id) && !_id.getClass().equals(Document.class)) {
            ObjectId id = document.get("id", ObjectId.get().getClass());
            List<Book> deletedBooks = mongoOperations.find(Query.query(Criteria.where("id").is(id)), Book.class);
            deletedBooks.forEach(book -> {
                ReflectionUtils.doWithFields(event.getType(),
                        new CascadeDeleteCallback(book, mongoOperations));
            });
        } else {
            List<ObjectId> list = (List) document.get("id", Document.class).get("$in");
            Document obj = document.get("_id", Document.class);
            List<Book> deletedBooks = mongoOperations.find(Query.query(Criteria.where("id").in(list)), Book.class);
            deletedBooks.forEach(book -> {
                ReflectionUtils.doWithFields(event.getType(),
                        new CascadeDeleteCallback(book, mongoOperations));
            });
        }


    }
}
