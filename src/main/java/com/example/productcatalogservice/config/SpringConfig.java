package com.example.productcatalogservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpringConfig {

    @Bean
    public RestTemplate getRestTemplate() {
       return new RestTemplate();
    }
}
