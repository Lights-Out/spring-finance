package net.eleven.finance.repository.jpa;

import net.eleven.finance.model.Merchandise;
import net.eleven.finance.repository.MerchandiseRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

/**
 * Created by eleven on 26.11.2018.
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY, rollbackFor = Throwable.class)
public class MerchandiseRepositoryImpl implements MerchandiseRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Merchandise findById(long id) throws DataAccessException {
        return em.find(Merchandise.class, id);
    }

    @Override
    public Collection<Merchandise> findAll() throws DataAccessException {
        return this.em
                .createQuery("SELECT DISTINCT merchandise FROM Merchandise merchandise ORDER BY merchandise.id")
                .getResultList();
    }

    @Override
    public void add(Merchandise merchandise) throws DataAccessException {
        em.persist(merchandise);
    }

    @Override
    public void save(Merchandise merchandise) throws DataAccessException {
        em.merge(merchandise);
    }
}
