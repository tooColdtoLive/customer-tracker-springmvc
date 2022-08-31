package com.luv2code.customerTracker.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

@Configuration  // indicates the existence of @Bean definition methods, to have those being processed
@EnableWebMvc   // enables springmvc, @Controller annotated class, replace <mvc:annotation-driven/> in servlet.xml
@EnableAspectJAutoProxy // enables SpringAOP to use proxy
@EnableTransactionManagement    // enables @Transactional
@ComponentScan("com.luv2code.customerTracker")
@PropertySource({ "classpath:persistence-mysql.properties" })
public class MyAppConfig implements WebMvcConfigurer {

    @Autowired
    private Environment env;    // replace the property tags in servlet xml

    private Logger logger = Logger.getLogger(getClass().getName()); // find or create a logger with the provided name

    @Bean
    public ViewResolver viewResolver() {    // replace the prefix suffix setting in servlet.xml

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }

    @Bean
    public DataSource cusDataSource() {  // replace dataSource bean in servlet.xml

        // create connection pool
        ComboPooledDataSource cusDataSource = new ComboPooledDataSource();

        // set the jdbc driver
        try {
            cusDataSource.setDriverClass(env.getProperty("jdbc.driver"));
        }
        catch (PropertyVetoException exc) {
            throw new RuntimeException(exc);
        }

        // for sanity's sake, let's log url and user ... just to make sure we are reading the data
        logger.info("jdbc.url=" + env.getProperty("jdbc.url.cus"));
        logger.info("jdbc.user=" + env.getProperty("jdbc.user.cus"));

        // set database connection props
        cusDataSource.setJdbcUrl(env.getProperty("jdbc.url.cus"));
        cusDataSource.setUser(env.getProperty("jdbc.user.cus"));
        cusDataSource.setPassword(env.getProperty("jdbc.password.cus"));

        // set connection pool props
        cusDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
        cusDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
        cusDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
        cusDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

        return cusDataSource;
    }

    @Bean
    public DataSource securityDataSource() {  // replace dataSource bean in servlet.xml

        // create connection pool
        ComboPooledDataSource userDataSource = new ComboPooledDataSource();

        // set the jdbc driver
        try {
            userDataSource.setDriverClass(env.getProperty("jdbc.driver"));
        }
        catch (PropertyVetoException exc) {
            throw new RuntimeException(exc);
        }

        // for sanity's sake, let's log url and user ... just to make sure we are reading the data
        logger.info("jdbc.url=" + env.getProperty("jdbc.url.user"));
        logger.info("jdbc.user=" + env.getProperty("jdbc.user.user"));

        // set database connection props
        userDataSource.setJdbcUrl(env.getProperty("jdbc.url.user"));
        userDataSource.setUser(env.getProperty("jdbc.user.user"));
        userDataSource.setPassword(env.getProperty("jdbc.password.user"));

        // set connection pool props
        userDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
        userDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
        userDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
        userDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

        return userDataSource;
    }

    private int getIntProperty(String propName) {

        String propVal = env.getProperty(propName);

        // now convert to int
        int intPropVal = Integer.parseInt(propVal);

        return intPropVal;
    }

    private Properties getHibernateProperties() {

        // set hibernate properties
        Properties props = new Properties();

        props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));

        return props;
    }

    @Bean
    public LocalSessionFactoryBean cusSessionFactory(){    // replace sessionFactory bean in servlet.xml, for @Transactional

        // create session factory
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        // set the properties
        sessionFactory.setDataSource(cusDataSource());
        sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
        sessionFactory.setHibernateProperties(getHibernateProperties());

        return sessionFactory;
    }

    @Bean
    public LocalSessionFactoryBean securitySessionFactory(){    // replace sessionFactory bean in servlet.xml, for @Transactional

        // create session factory
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        // set the properties
        sessionFactory.setDataSource(securityDataSource());
        sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
        sessionFactory.setHibernateProperties(getHibernateProperties());

        return sessionFactory;
    }

    @Bean
    @Autowired  // sessionFactory
    public HibernateTransactionManager cusTransactionManager(SessionFactory cusSessionFactory) {  // replace transactionManager bean in servlet.xml, for @Transactional

        // setup transaction manager based on session factory
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(cusSessionFactory);

        return txManager;
    }

    @Bean
    @Autowired  // sessionFactory
    public HibernateTransactionManager securityTransactionManager(SessionFactory securitySessionFactory) {  // replace transactionManager bean in servlet.xml, for @Transactional

        // setup transaction manager based on session factory
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(securitySessionFactory);

        return txManager;
    }

    // replace mvc:resources tag in servlet.xml
    // add support for reading web resources: css, images, js, etc ...
    // location = physical directories, ** in mapping = taking all lower level directories and files
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }
}
