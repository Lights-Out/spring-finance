package net.eleven.finance.repository;

import org.springframework.dao.DataAccessException;

import java.util.Collection;

/**
 * Created by eleven on 26.11.2018.
 */
public interface GenericRepository<T> {
    T findById(long id) throws DataAccessException;
    Collection<T> findAll() throws DataAccessException;
    void add(T entity) throws DataAccessException;
    void save(T entity) throws DataAccessException;
}
