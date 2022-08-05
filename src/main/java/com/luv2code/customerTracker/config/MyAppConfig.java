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
    public DataSource myDataSource() {  // replace dataSource bean in servlet.xml

        // create connection pool
        ComboPooledDataSource myDataSource = new ComboPooledDataSource();

        // set the jdbc driver
        try {
            myDataSource.setDriverClass(env.getProperty("jdbc.driver"));
        }
        catch (PropertyVetoException exc) {
            throw new RuntimeException(exc);
        }

        // for sanity's sake, let's log url and user ... just to make sure we are reading the data
        logger.info("jdbc.url=" + env.getProperty("jdbc.url"));
        logger.info("jdbc.user=" + env.getProperty("jdbc.user"));

        // set database connection props
        myDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        myDataSource.setUser(env.getProperty("jdbc.user"));
        myDataSource.setPassword(env.getProperty("jdbc.password"));

        // set connection pool props
        myDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
        myDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
        myDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
        myDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

        return myDataSource;
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
    public LocalSessionFactoryBean sessionFactory(){    // replace sessionFactory bean in servlet.xml, for @Transactional

        // create session factory
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        // set the properties
        sessionFactory.setDataSource(myDataSource());
        sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
        sessionFactory.setHibernateProperties(getHibernateProperties());

        return sessionFactory;
    }

    @Bean
    @Autowired  // sessionFactory
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {  // replace transactionManager bean in servlet.xml, for @Transactional

        // setup transaction manager based on session factory
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

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
