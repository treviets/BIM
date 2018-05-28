package com.redsun.service.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;


/**
 * Annotation class configuration will config spring mvc, datasource, properties
 * and automatically create schema
 * db_schema file contain script to create tables
 * db_data file contain script to insert data into above tables
 * Created by sam on 2/7/17.
 */
@Configuration
@PropertySource("classpath:application.properties")
@EnableWebMvc
public class RedsunServiceConfiguration {

    @Autowired
    private Environment env;

    /**
     * create datasource bean
     * @return
     * @throws PropertyVetoException
     */
    @Bean(destroyMethod = "close")
    public DataSource getDataSource() throws PropertyVetoException {
        final ComboPooledDataSource datasource = new ComboPooledDataSource();
        datasource.setDriverClass(env.getProperty("datasource.jdbc.driverClassName"));
        datasource.setJdbcUrl(env.getProperty("datasource.jdbc.url"));
        datasource.setUser(env.getProperty("datasource.jdbc.username"));
        datasource.setPassword(env.getProperty("datasource.jdbc.password"));
        datasource.setMaxStatements(Integer.parseInt(env.getProperty("datasource.jdbc.max_statements")));
        datasource.setInitialPoolSize(Integer.parseInt(env.getProperty("datasource.jdbc.initial_pool_size")));
        datasource.setMaxPoolSize(Integer.parseInt(env.getProperty("datasource.jdbc.max_pool_size")));
        datasource.setMinPoolSize(Integer.parseInt(env.getProperty("datasource.jdbc.min_pool_size")));
        datasource.setAcquireIncrement(Integer.parseInt(env.getProperty("datasource.jdbc.acquire_increment")));
        return datasource;
    }

    /**
     * create jdbcTemplate bean
     * @param datasource
     * @return
     */
    @Bean
    public JdbcTemplate getJdbcTemplate(final DataSource datasource ){
        return new JdbcTemplate(datasource);
    }

    /**
     * create Transaction Manager bean
     * @param datasource
     * @return
     */
    @Bean
    public DataSourceTransactionManager getTransactionManager(final DataSource datasource){
        return new DataSourceTransactionManager(datasource);
    }

    /**
     * Create DataSourceInitializer to run automatically DB scripts
     * @param dataSource
     * @return
     */
    @Profile("test_db")
    @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulator());
        return initializer;
    }

    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource(env.getProperty("schema.script")));
        populator.addScript(new ClassPathResource(env.getProperty("data.script")));
        return populator;
    }

    /**
     * create ViewResolver bean for spring MVC
     * @return
     */
    @Bean
    public ViewResolver getViewResolver(){
        final InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix(env.getProperty("view.prefix"));
        resolver.setSuffix(env.getProperty("view.suffix"));
        return resolver;
    }

    /**
     * create PropertiesSource bean to get value from Environment bean
     * @return
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer getPlaceholder(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * config multipartResolver for file upload
     * @return
     */
    @Bean
    public CommonsMultipartResolver multipartResolver(){
        final CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(20971520); // 20MB
        commonsMultipartResolver.setMaxInMemorySize(1048576); // 1MB
        return commonsMultipartResolver;
    }

    /**
     * create object mapper bean
     * @return
     */
    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }
}
