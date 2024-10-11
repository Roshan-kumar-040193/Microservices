package com.example.microservices.Employee.Controller;

import com.example.microservices.Employee.Model.EmployeeResponse;
import com.example.microservices.Employee.Service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class EmpController {
    @Autowired
    EmpService empService;

    @GetMapping("/getEmployee/{id}")
    public CompletableFuture<EmployeeResponse> getAddressById(@PathVariable int id){
       return empService.getEmployeeById(id);
    }
    public void getAddresses(){

    }
    public void UpdateAddress(){

    }
    public void addAddress(){

    }
    public void deleteAddress(){

    }
}
