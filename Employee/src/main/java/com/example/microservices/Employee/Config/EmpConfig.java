package com.example.microservices.Employee.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class EmpConfig {
    @Bean
    public RestTemplate buildRestTemplate(){
        return new RestTemplate();
    }
    @Bean
    public WebClient getWebClient(){
        return WebClient.builder().build();
    }
}
