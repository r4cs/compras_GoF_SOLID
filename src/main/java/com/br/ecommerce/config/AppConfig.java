// src/main/java/com/br/ecommerce/config/AppConfig.java
package com.br.ecommerce.config;

import net.sf.log4jdbc.sql.jdbcapi.DataSourceSpy;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
public class AppConfig {
    @Autowired
    DataSourceProperties dataSourceProperties;

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url(dataSourceProperties.getUrl())
                .username(dataSourceProperties.getUsername())
                .password(dataSourceProperties.getPassword())
                .driverClassName(dataSourceProperties.getDriverClassName())
                .build();
    }
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    // @Bean
    // DataSource dataSource() {
    //     DataSource dataSource = DataSourceBuilder
    //             .create(this.dataSourceProperties.getClassLoader())
    //             .url(this.dataSourceProperties.getUrl())
    //             .username(this.dataSourceProperties.getUsername())
    //             .password(this.dataSourceProperties.getPassword())
    //             .build();
    //     return new DataSourceSpy(dataSource);
    // }
}