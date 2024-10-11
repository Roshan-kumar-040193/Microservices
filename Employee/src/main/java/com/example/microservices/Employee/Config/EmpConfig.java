package com.example.microservices.Employee.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class EmpConfig {
    @Bean
    public RestTemplate buildRestTemplate(){
        return new RestTemplate();
    }
}
