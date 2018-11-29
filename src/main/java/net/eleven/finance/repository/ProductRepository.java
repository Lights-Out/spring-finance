package net.eleven.finance.repository;

import net.eleven.finance.model.Product;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

/**
 * Created by eleven on 12.11.2018.
 */
public interface ProductRepository {
    Product findById(long id) throws DataAccessException;
    Product findByName(String code) throws DataAccessException;
    Collection<Product> findAll() throws DataAccessException;
    void add(Product product) throws DataAccessException;
    void save(Product product) throws DataAccessException;
}
