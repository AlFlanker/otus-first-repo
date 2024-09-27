package com.gmail.alexflanker89.lesson.events.callback;

import com.gmail.alexflanker89.lesson.events.annotations.CascadeDelete;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

public class CascadeDeleteCallback implements ReflectionUtils.FieldCallback {
    private Object source;
    private MongoOperations mongoOperations;

    public CascadeDeleteCallback(final Object source, final MongoOperations mongoOperations) {
        this.source = source;
        this.setMongoOperations(mongoOperations);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void doWith(final Field field) throws IllegalArgumentException, IllegalAccessException {
        ReflectionUtils.makeAccessible(field);
        if (field.isAnnotationPresent(DBRef.class) && field.isAnnotationPresent(CascadeDelete.class)) {
            final Object fieldValue = field.get(getSource());
            if (Objects.nonNull(fieldValue)) {
                final FieldCallback callback = new FieldCallback();
                ReflectionUtils.doWithFields(fieldValue.getClass(), callback);
                if (Iterable.class.isAssignableFrom(fieldValue.getClass())) {
                    ParameterizedType genericType = (ParameterizedType) field.getGenericType();
                    Type actualTypeArgument = genericType.getActualTypeArguments()[0];
                    try {
                        Class<?> aClass = Class.forName(actualTypeArgument.getTypeName());
                        ((Iterable) fieldValue).forEach((v) -> {
                            try {
                                Field id1 = aClass.getDeclaredField("id");
                                ReflectionUtils.makeAccessible(id1);
                                Object id = id1.get(v);
                                getMongoOperations().findAndRemove(Query.query(Criteria.where("id").is(id)), aClass);
                                System.out.println();
                            } catch (IllegalAccessException | NoSuchFieldException e) {
                                e.printStackTrace();
                            }
                        });
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } else getMongoOperations().remove(fieldValue);
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
