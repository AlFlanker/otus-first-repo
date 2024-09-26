package com.gmail.alexflanker89.lesson11.events.callback;

import com.gmail.alexflanker89.lesson11.events.annotations.CascadeSave;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Collection;

public class CascadeSaveCallback implements ReflectionUtils.FieldCallback {
    private Object source;
    private MongoOperations mongoOperations;

    public CascadeSaveCallback(final Object source, final MongoOperations mongoOperations) {
        this.source = source;
        this.setMongoOperations(mongoOperations);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void doWith(final Field field) throws IllegalArgumentException, IllegalAccessException {
        ReflectionUtils.makeAccessible(field);
        if (field.isAnnotationPresent(DBRef.class) && field.isAnnotationPresent(CascadeSave.class)) {
            final Object fieldValue = field.get(getSource());
            if (fieldValue != null) {
                final FieldCallback callback = new FieldCallback();
                ReflectionUtils.doWithFields(fieldValue.getClass(), callback);
                if (Iterable.class.isAssignableFrom(fieldValue.getClass()))
                    ((Collection) fieldValue).forEach(v -> getMongoOperations().save(v));
                else getMongoOperations().save(fieldValue);
            }
        }

    }

    private Object getSource() {
        return source;
    }

    public void setSource(final Object source) {
        this.source = source;
    }

    private MongoOperations getMongoOperations() {
        return mongoOperations;
    }

    private void setMongoOperations(final MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }
}
