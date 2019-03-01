package net.eleven.finance.service.currency;

import net.eleven.finance.model.Currency;
import net.eleven.finance.repository.CurrencyRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class CurrencyServiceImpl implements CurrencyService {

    private CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Currency findById(long id) throws DataAccessException {
        return currencyRepository.findById(id);
    }

    @Override
    public Currency findByCode(String code) throws DataAccessException {
        return currencyRepository.findByCode(code);
    }

    @Override
    public Collection<Currency> findAll() throws DataAccessException {
        return currencyRepository.findAll();
    }

    @Override
    public void add(Currency currency) throws DataAccessException {
        currencyRepository.add(currency);
    }

    @Override
    public void save(Currency currency) throws DataAccessException {
        currencyRepository.save(currency);
    }
}
