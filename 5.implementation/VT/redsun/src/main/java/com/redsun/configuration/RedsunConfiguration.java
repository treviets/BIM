package com.redsun.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * Created by HauLL on 3/15/2017.
 */
@Configuration
public class RedsunConfiguration {

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
