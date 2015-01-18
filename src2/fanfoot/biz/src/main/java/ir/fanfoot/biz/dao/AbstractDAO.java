package ir.fanfoot.biz.dao;

import ir.fanfoot.annotations.Sorted;
import ir.fanfoot.util.StringHelper;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.*;

public abstract class AbstractDAO<T> implements GenericDAO<T> {

    private String defaultOrderByClause;
    protected Class<T> clazz;

    @PersistenceContext(unitName = "fanfoot")
    protected EntityManager entityManager;

    public AbstractDAO(Class<T> clazz) {
        this.clazz = clazz;
        calculateDefaultOrderByClause();
    }

    protected String getDefaultOrderByClause() {
        if (defaultOrderByClause == null) {
            calculateDefaultOrderByClause();
        }
        return defaultOrderByClause;
    }

    @Override
    public UUID saveOrUpdate(T entity) {
        correctEntityStrings(entity);
        UUID id = getId(entity);
        if (id == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
        return getId(entity);
    }

    @Override
    public void delete(UUID id) {
        entityManager
                .createQuery("DELETE FROM " + getEntityName() + " e WHERE e.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public T getById(UUID id) {
        try {
            return (T) entityManager
                    .createQuery("SELECT e FROM " + getEntityName() + " e WHERE e.id = :id")
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            if (!(e instanceof NoResultException)) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    public List<T> getAll() {
        return (List<T>) entityManager
                .createQuery("SELECT e FROM " + getEntityName() + " e " + defaultOrderByClause)
                .getResultList();
    }

    @Override
    public List<T> getAllPaged(int first, int pageSize) {
        return (List<T>) entityManager
                .createQuery("SELECT e FROM " + getEntityName() + " e " + defaultOrderByClause)
                .setFirstResult(first)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Override
    public long count() {
        return (Long) entityManager
                .createQuery("SELECT COUNT(e.id) FROM " + getEntityName() + " e")
                .getSingleResult();
    }

    protected UUID getId(T entity) {
        try {
            return (UUID) clazz.getMethod("getId").invoke(entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected String getEntityName() {
        if (clazz.isAnnotationPresent(Table.class)) {
            return clazz.getName();
        }
        throw new RuntimeException("T is not an entity.");
    }

    protected void calculateDefaultOrderByClause() {

        class SortEntry {
            public int order;
            public String fieldName;
            public Sorted.Sort sort;

            public SortEntry(int order, String fieldName, Sorted.Sort sort) {
                this.order = order;
                this.fieldName = fieldName;
                this.sort = sort;
            }
        }

        SortedMap<Integer, SortEntry> sortEntries = new TreeMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Sorted.class)) {
                Sorted sorted = field.getAnnotation(Sorted.class);
                sortEntries.put(sorted.order(), new SortEntry(sorted.order(), field.getName(), sorted.sort()));
            }
        }

        this.defaultOrderByClause = "";
        if (sortEntries.size() > 0) {
            String delim = "ORDER BY ";
            StringBuilder stringBuilder = new StringBuilder();
            for (Integer order : sortEntries.keySet()) {
                stringBuilder
                        .append(delim)
                        .append(sortEntries.get(order).fieldName)
                        .append(" ")
                        .append(sortEntries.get(order).sort == Sorted.Sort.ASCENDING ? "ASC" : "DESC");
                delim = ", ";
            }
            this.defaultOrderByClause = stringBuilder.toString();
        }
    }

    protected void correctEntityStrings(T entity) {
        try {
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.getType().getName().equals("java.lang.String")) {
                    String newValue = StringHelper.correctPersianCharacters((String) field.get(entity));
                    field.set(entity, newValue);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}
