package com.jhovanni.registropersonas.config;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Jhovanni
 */
@Configuration
@ComponentScan(basePackages = "com.jhovanni.registropersonas.hibernate")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.jhovanni.registropersonas.repositorio", entityManagerFactoryRef = "jpaEntityManagerFactory")
public class RootConfig {

    @Bean
    public BasicDataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        //jdbc:mysql://db4free.net:3306/jhovannidatabase?zeroDateTimeBehavior=convertToNul
        dataSource.setUrl("jdbc:mysql://db4free.net:3306/jhovannidatabase");
        dataSource.setUsername("jhovanni");
        dataSource.setPassword("jhovanniDatabase");
        return dataSource;
    }

    @Bean
    @Autowired
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    /**
     * Configura el entity manager. Usando este entity manager, el
     * {@code sessionFactory} usado anteriormente queda obsoleto. Por lo tanto
     * es posible eliminarlo (hay que recordar que éste nuevo entity manager se
     * empezó a manejar cuando se agrega SpringData al proyecto). <br>Como
     * referencia, y para futuras ocasiones en que no se desee usar un
     * entityManager; la forma de usar un session factory es creando un método
     * similar a este, pero que regrese un objeto
     * {@link  LocalSessionFactoryBean} en su lugar. La configuración interna es
     * practicamente la misma
     *
     * @param dataSource
     * @return
     */
    @Bean
    @Autowired
    public LocalContainerEntityManagerFactoryBean jpaEntityManagerFactory(DataSource dataSource) {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactory.setPackagesToScan("com.jhovanni.registropersonas.entidad");
        entityManagerFactory.setJpaProperties(properties);
        return entityManagerFactory;
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }
}
