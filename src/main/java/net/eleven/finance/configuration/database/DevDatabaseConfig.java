package net.eleven.finance.configuration.database;

import net.eleven.finance.configuration.database.DatabaseConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created by eleven on 27.10.2018.
 */
@Profile("Development")
@PropertySource({"classpath:props/application-dev.properties"})
@Configuration
public class DevDatabaseConfig extends AbstractDatabaseConfig {
}
