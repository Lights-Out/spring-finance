package net.eleven.finance.repository.jpa;

import net.eleven.finance.model.Currency;
import net.eleven.finance.model.Product;
import net.eleven.finance.repository.ProductRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

/**
 * Created by eleven on 12.11.2018.
 */
@Repository
@Transactional
public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Product findById(long id) throws DataAccessException {
        return em.find(Product.class, id);
    }

    @Override
    public Product findByName(String code) throws DataAccessException {
        Query query = em.createQuery("SELECT DISTINCT product FROM Product product WHERE name = :code");
        query.setParameter("code", code);
        List resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            return (Product) resultList.get(0);
        }
        return null;
    }

    @Override
    public Collection<Product> findAll() throws DataAccessException {
        return this.em
                .createQuery("SELECT DISTINCT product FROM Product product ORDER BY product.created")
                .getResultList();

    }

    @Override
    public void add(Product currency) throws DataAccessException {
        em.persist(currency);
    }

    @Override
    public void save(Product currency) throws DataAccessException {
        em.merge(currency);
    }
}
