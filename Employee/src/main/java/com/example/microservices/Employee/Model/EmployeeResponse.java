package com.example.microservices.Employee.Model;

import org.springframework.stereotype.Component;

@Component
public class EmployeeResponse {
    int emp_id;
    String name;
    int age;
    String dept;
    AddressResponse addressResponse;

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public AddressResponse getAddressResponse() {
        return addressResponse;
    }

    public void setAddressResponse(AddressResponse addressResponse) {
        //System.out.println(addressResponse);
        this.addressResponse = addressResponse;
    }

    @Override
    public String toString() {
        return "EmployeeResponse{" +
                "emp_id=" + emp_id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", dept='" + dept + '\'' +
                ", addressResponse=" + addressResponse +
                '}';
    }
}
