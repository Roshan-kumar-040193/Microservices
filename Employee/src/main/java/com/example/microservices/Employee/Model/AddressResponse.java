package com.example.microservices.Employee.Model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AddressResponse {
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}
