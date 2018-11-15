//package net.eleven.finance.web;
//
//import lombok.extern.log4j.Log4j2;
//import net.eleven.finance.configuration.RootConfig;
//import net.eleven.finance.configuration.database.TestDatabaseConfig;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.Arrays;
//
///**
// * Created by eleven on 27.10.2018.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {RootConfig.class})
////@ActiveProfiles("Test")
//@Log4j2
//public class PropertiesTest {
////    @Inject
////    ApplicationProperties properties;
////
////    @Inject
////    private EntityManagerFactory factory;
////
////    @Test
////    public void testProps() {
////        System.out.println("JPA: " + factory);
////        System.out.println("PROPS: " + properties);
////    }
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Autowired
//    private Environment env;
//
//    @Test
//    public void testDataSourceHasCorrectUrl()
//    {
//        log.debug("active profiles: " + Arrays.toString(env.getActiveProfiles()));
//        try {
//            Connection connection = dataSource.getConnection();
//            Statement statement = connection.createStatement();
//            statement.execute("create table user(" +
//                    "id integer primary key, " +
//                    "name varchar(100));");
//            log.warn("Connection is " + connection);
//            System.out.println("CONNECTION = " + connection);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
