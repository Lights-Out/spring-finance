package net.eleven.finance.repository;

import net.eleven.finance.model.Currency;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by eleven on 28.10.2018.
 */
@Repository
public interface CurrencyRepository {
    Currency findById(long id) throws DataAccessException;
    Currency findByCode(String code) throws DataAccessException;
    Collection<Currency> findAll() throws DataAccessException;
    void add(Currency currency) throws DataAccessException;
    void save(Currency currency) throws DataAccessException;
}
