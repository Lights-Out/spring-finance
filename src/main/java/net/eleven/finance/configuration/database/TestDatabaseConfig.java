package net.eleven.finance.configuration.database;

import net.eleven.finance.configuration.database.AbstractDatabaseConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by eleven on 27.10.2018.
 */
@Profile("Test")
@PropertySource({"classpath:props/application-test.properties"})
@Configuration
public class TestDatabaseConfig extends AbstractDatabaseConfig {
}
