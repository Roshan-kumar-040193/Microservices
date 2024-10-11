package com.example.microservices.Employee.Service;

import com.example.microservices.Employee.Dao.EmployeeDao;
import com.example.microservices.Employee.Entity.Employee;
import com.example.microservices.Employee.Model.AddressResponse;
import com.example.microservices.Employee.Model.EmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.function.Supplier;

@Service
public class EmpService {

    @Autowired
    EmployeeDao empDao;
    @Autowired
    RestTemplate restTemplate;

    public CompletableFuture<EmployeeResponse> getEmployeeById(int id){

        CompletableFuture<EmployeeResponse> employeeFuture = CompletableFuture.supplyAsync(() -> {
            Employee emp = empDao.getEmployeeById(id);
            EmployeeResponse employeeResponse = new EmployeeResponse();
            employeeResponse.setEmp_id(emp.getEmp_id());
            employeeResponse.setName(emp.getName());
            employeeResponse.setDept(emp.getDept());
            employeeResponse.setAge(emp.getAge());
            return employeeResponse;
        });

        // Fetch Address asynchronously
        CompletableFuture<AddressResponse> addressFuture = CompletableFuture.supplyAsync(() ->
                restTemplate.getForObject("http://localhost:8082/getAddress/" + id, AddressResponse.class)
        );

        return employeeFuture.thenCombine(addressFuture, (employeeResponse, addressResponse) -> {
            // Combine the employee and address response here
            // Assuming you have a way to merge them into a single object
            employeeResponse.setAddressResponse(addressResponse);  // You need to add a field for address in EmployeeResponse
            return employeeResponse;
        }).exceptionally(throwable -> {
            // Log or handle the exception here
            throwable.printStackTrace();
            return null; // Or return a meaningful error response
        });
    }

}
