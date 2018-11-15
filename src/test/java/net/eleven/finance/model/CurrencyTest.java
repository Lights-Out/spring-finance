//package net.eleven.finance.model;
//
//import lombok.extern.log4j.Log4j2;
//import net.eleven.finance.configuration.RootConfig;
//import net.eleven.finance.configuration.database.TestDatabaseConfig;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import java.time.LocalDateTime;
//
///**
// * Created by eleven on 27.10.2018.
// */
////@RunWith(SpringJUnit4ClassRunner.class)
////@ContextConfiguration(classes = {RootConfig.class})
//@Log4j2
//public class CurrencyTest {
//
//    @Inject
//    private EntityManagerFactory factory;
//
//    @Test
//    public void testCreatedTime() {
//        Currency currency = new Currency();
//        currency.setCurrencyCode("USD");
//        currency.setUpdated(LocalDateTime.of(2018, 10, 27, 15, 0, 0));
//        EntityManager entityManager = factory.createEntityManager();
//        entityManager.persist(currency);
//    }
//}
