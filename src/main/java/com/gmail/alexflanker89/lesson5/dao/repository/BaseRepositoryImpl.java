package com.gmail.alexflanker89.lesson5.dao.repository;

import com.gmail.alexflanker89.lesson5.dao.interfaces.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.List;


@SuppressWarnings("unchecked")
public abstract class BaseRepositoryImpl<T> implements BaseRepository<T> {
    @PersistenceContext
    protected final EntityManager entityManager;
    private final Class<T> entryClass;

    @Autowired
    public BaseRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.entryClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    @Transactional(readOnly = true)
    public T findById(long id){
        return entityManager.find(entryClass, id);
    }
    @Override
    @Transactional(readOnly = true)
    public List<T> findAll(){
        return entityManager.createQuery("from "+ entryClass.getName()).getResultList();

    };
    @Transactional
    @Override
    public void delete(T entry){
        entityManager.remove(entry);
    };
    @Override
    @Transactional
    public void save(T entry){
        entityManager.persist(entry);
    };
    @Transactional
    @Override
    public void update(T entry){
        entityManager.merge(entry);
    };

}
