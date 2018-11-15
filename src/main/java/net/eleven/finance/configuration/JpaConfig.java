package net.eleven.finance.configuration;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jca.support.LocalConnectionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Created by eleven on 27.10.2018.
 */
@Configuration
@ComponentScan("net.eleven.finance.repository")
public class JpaConfig {

    @Inject
    private Environment env;

    @Inject
    private DataSource dataSource;

    @Inject
    private JpaVendorAdapter jpaVendorAdapter;

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setJpaVendorAdapter(jpaVendorAdapter);
        em.setPackagesToScan("net.eleven.finance");
        em.afterPropertiesSet();
        return em.getObject();
    }

//    @Bean
//    public PersistenceAnnotationBeanPostProcessor paPostProcessor() {
//        return new PersistenceAnnotationBeanPostProcessor();
//    }

    @Bean(name="transactionManager")
    public JpaTransactionManager jpaTransactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory());
        return jpaTransactionManager;
    }

//    @Bean
//    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
//        return new PersistenceExceptionTranslationPostProcessor();
//    }
}
