package com.example.microservices.Employee.Service;

import com.example.microservices.Employee.Dao.EmployeeDao;
import com.example.microservices.Employee.Entity.Employee;
import com.example.microservices.Employee.FeignClient.AddressFeignClient;
import com.example.microservices.Employee.Model.AddressResponse;
import com.example.microservices.Employee.Model.EmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class EmpService {

    @Autowired
    EmployeeDao empDao;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WebClient webClient;

    @Autowired
    AddressFeignClient addressFeignClient;


;    public CompletableFuture<EmployeeResponse> getEmployeeById(int id){
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
                //even though this piece of code runs parallelly and asynchronously the thread is blocked by restTemplate
                //until and unless we get a response from the address server.
                //to resolve this webClient came into picture
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


    public CompletableFuture<EmployeeResponse> getEmployeeByIdUsingWebClient(int id){

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
        CompletableFuture<AddressResponse> addressFuture =
                webClient.get().uri("http://localhost:8082/getAddress/" + id)
                        .retrieve()
                        .bodyToMono(AddressResponse.class)
                        .toFuture();

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

    public CompletableFuture<EmployeeResponse> getEmployeeByIdUsingFeignClient(int id){

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
        CompletableFuture<AddressResponse> addressFuture = CompletableFuture.supplyAsync(()->addressFeignClient.getAddressById(id));

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
