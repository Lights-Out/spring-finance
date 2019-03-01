package net.eleven.finance.service.currency;

import net.eleven.finance.model.Currency;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

public interface CurrencyService {
    Currency findById(long id) throws DataAccessException;
    Currency findByCode(String code) throws DataAccessException;
    Collection<Currency> findAll() throws DataAccessException;
    void add(Currency currency) throws DataAccessException;
    void save(Currency currency) throws DataAccessException;
}
