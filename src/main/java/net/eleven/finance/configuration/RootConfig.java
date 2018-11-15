package net.eleven.finance.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by eleven on 27.10.2018.
 */
@Configuration
@ComponentScan(basePackages = "net.eleven.finance", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
})
@EnableTransactionManagement
//@Import({DevDatabaseConfig.class, TestDatabaseConfig.class, ProductionDatabaseConfig.class, JpaConfig.class})
public class RootConfig {
}
