package com.jhovanni.registropersonas.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Jhovanni
 */
@Configuration
@ComponentScan(basePackages = "com.jhovanni.registropersonas.hibernate")
@EnableTransactionManagement
public class RootConfig {

    @Bean
    public BasicDataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
         dataSource.setUrl("jdbc:mysql://db4free.net:3306/jhovannidb");
        dataSource.setUsername("jhovanni");
        dataSource.setPassword("Artista13");
        return dataSource;
    }

    @Bean
    @Autowired
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("com.jhovanni.registropersonas.entidad");
        sessionFactory.setHibernateProperties(properties);
        return sessionFactory;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;

    }
    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        return passwordEncoder;
    }
}
