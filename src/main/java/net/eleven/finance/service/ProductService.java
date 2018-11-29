package net.eleven.finance.service;

import net.eleven.finance.model.Merchandise;
import net.eleven.finance.model.Product;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

/**
 * Created by eleven on 28.11.2018.
 */
public interface ProductService {
    Product findProductById(long id) throws DataAccessException;
    Product findProductByName(String name) throws DataAccessException;
    Collection<Product> findAllProducts() throws DataAccessException;
    void addProduct(Product product) throws DataAccessException;
    void saveProduct(Product product) throws DataAccessException;

    Merchandise findMerchandiseById(long id) throws DataAccessException;
    Collection<Merchandise> findAllMerchandises() throws DataAccessException;
    void addMerchandise(Merchandise entity) throws DataAccessException;
    void saveMerchandise(Merchandise entity) throws DataAccessException;
}
