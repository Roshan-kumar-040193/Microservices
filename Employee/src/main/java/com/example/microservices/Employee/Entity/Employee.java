package com.example.microservices.Employee.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity(name = "emp")
public class Employee implements Serializable {
    @Id
    @GeneratedValue
    int emp_id;
    String name;
    int age;
    String dept;

}
