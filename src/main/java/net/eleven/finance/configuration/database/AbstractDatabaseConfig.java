package net.eleven.finance.configuration.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

/**
 * Created by eleven on 27.10.2018.
 */
abstract public class AbstractDatabaseConfig implements DatabaseConfig {
    @Value("${db.driverClass}")
    private String driverClass;

    @Value("${db.connectionURL}")
    private String connectionURL;

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    @Value("${jpa.database}")
    private String database;

    @Value("${jpa.showSql}")
    private String showSql;

    @Value("${jpa.generateDdl}")
    private String generateDdl;

    @Override
    @Bean
    public DataSource createDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(connectionURL);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        // the 'database' parameter refers to the database dialect being used.
        vendorAdapter.setDatabase(Database.valueOf(database));
        vendorAdapter.setShowSql(Boolean.valueOf(showSql));
        vendorAdapter.setGenerateDdl(Boolean.valueOf(generateDdl));
        return vendorAdapter;
    }
}
