package net.eleven.finance.service;

import net.eleven.finance.model.Merchandise;
import net.eleven.finance.model.Product;
import net.eleven.finance.repository.MerchandiseRepository;
import net.eleven.finance.repository.ProductRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by eleven on 28.11.2018.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private MerchandiseRepository merchandiseRepository;

    public ProductServiceImpl(ProductRepository productRepository, MerchandiseRepository merchandiseRepository) {
        this.productRepository = productRepository;
        this.merchandiseRepository = merchandiseRepository;
    }

    @Override
    public Product findProductById(long id) throws DataAccessException {
        return productRepository.findById(id);
    }

    @Override
    public Product findProductByName(String name) throws DataAccessException {
        return productRepository.findByName(name);
    }

    @Override
    public Collection<Product> findAllProducts() throws DataAccessException {
        return productRepository.findAll();
    }

    @Override
    public void addProduct(Product product) throws DataAccessException {
        productRepository.add(product);
    }

    @Override
    public void saveProduct(Product product) throws DataAccessException {
        productRepository.save(product);
    }

    @Override
    public Merchandise findMerchandiseById(long id) throws DataAccessException {
        return merchandiseRepository.findById(id);
    }

    @Override
    public Collection<Merchandise> findAllMerchandises() throws DataAccessException {
        return merchandiseRepository.findAll();
    }

    @Override
    public void addMerchandise(Merchandise entity) throws DataAccessException {
        merchandiseRepository.add(entity);
    }

    @Override
    public void saveMerchandise(Merchandise entity) throws DataAccessException {
        merchandiseRepository.save(entity);
    }
}
