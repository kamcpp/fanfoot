package ir.fanfoot.biz.dao;

import java.util.List;
import java.util.UUID;

public interface GenericDAO <T> {

    UUID saveOrUpdate(T entity);

    void delete(UUID id);

    T getById(UUID id);

    List<T> getAll();
}
