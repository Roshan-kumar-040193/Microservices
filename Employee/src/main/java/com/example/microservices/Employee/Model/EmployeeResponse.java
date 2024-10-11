package com.example.microservices.Employee.Model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class EmployeeResponse {
    int emp_id;
    String name;
    int age;
    String dept;
    AddressResponse addressResponse;
}
