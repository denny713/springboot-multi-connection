package com.multi.config;


import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "postgresqlEntityManagerFactory", transactionManagerRef = "postgresqlTransactionManager", basePackages = {"com.multi.postgre.repository"})
public class PostgreConfig {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "data.postgre")
    public DataSourceProperties postgresqlDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    public DataSource postgresqlDataSource() {
        return postgresqlDataSourceProperties().initializeDataSourceBuilder().type(BasicDataSource.class).build();
    }

    @Primary
    @Bean(name = "postgresqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean postgresqlEntityManager(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(postgresqlDataSource()).packages("com.multi.postgre.entity").build();
    }

    @Primary
    @Bean(name = "postgresqlTransactionManager")
    public PlatformTransactionManager postgresqlTransactionManager(@Qualifier("postgresqlEntityManagerFactory") LocalContainerEntityManagerFactoryBean postgresqlEntityManagerFactoryBean) {
        return new JpaTransactionManager(Objects.requireNonNull(postgresqlEntityManagerFactoryBean.getObject()));
    }
}

