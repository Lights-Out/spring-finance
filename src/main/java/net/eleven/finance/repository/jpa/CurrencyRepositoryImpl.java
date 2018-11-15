package net.eleven.finance.repository.jpa;

import net.eleven.finance.model.Currency;
import net.eleven.finance.repository.CurrencyRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

/**
 * Created by eleven on 28.10.2018.
 */
@Repository
@Transactional
public class CurrencyRepositoryImpl implements CurrencyRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Currency findById(long id) throws DataAccessException {
        return em.find(Currency.class, id);
    }

    @Override
    public Currency findByCode(String code) throws DataAccessException {
        Query query = em.createQuery("SELECT DISTINCT currency FROM Currency currency WHERE currencyCode = :code");
        query.setParameter("code", code);
        List resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            return (Currency) resultList.get(0);
        }
        return null;
    }

    @Override
    public Collection<Currency> findAll() throws DataAccessException {
        return this.em
                .createQuery("SELECT DISTINCT currency FROM Currency currency ORDER BY currency.created")
                .getResultList();

    }

    @Override
    public void add(Currency currency) throws DataAccessException {
        em.persist(currency);
    }

    @Override
    public void save(Currency currency) throws DataAccessException {
        em.merge(currency);
    }
}
