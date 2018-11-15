package net.eleven.finance.configuration.database;

import javax.sql.DataSource;

/**
 * Created by eleven on 27.10.2018.
 */
public interface DatabaseConfig {
    DataSource createDataSource();
}
