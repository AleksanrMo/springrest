package com.rest.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.mapping.Property;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.rest")
@EnableWebMvc
@EnableTransactionManagement


public class MyConfig {


    @Bean
     public DataSource dataSource()  {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("org.postgresql.Driver");
            dataSource.setJdbcUrl("jdbc:postgresql://127.0.0.1:5432/employees?useSSL=false&amp;serverTimezone=UTC");
            dataSource.setUser("postgres");
            dataSource.setPassword("oracle");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
         return  dataSource;

    }
    @Bean

    public LocalSessionFactoryBean localSessionFactoryBean() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("com.rest.entity");
        Properties property = new Properties();
        property.setProperty("hibernate.dialect", "rg.hibernate.dialect.PostgreSQL10Dialect");
        property.setProperty("hibernate.show_sql", "true");
        sessionFactoryBean.setHibernateProperties(property);
        return sessionFactoryBean;

    }

    @Bean
    public HibernateTransactionManager manager() {

        HibernateTransactionManager manager = new HibernateTransactionManager();
        manager.setSessionFactory(localSessionFactoryBean().getObject());
        return manager;
    }

}
