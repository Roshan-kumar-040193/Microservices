package com.example.microservices.Employee.FeignClient;

import com.example.microservices.Employee.Model.AddressResponse;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.CompletableFuture;

@FeignClient(name = "address-service")
@RibbonClient(name="address-service")
public interface AddressFeignClient {

    @GetMapping("/getAddress/{id}")
    public AddressResponse getAddressById(@PathVariable int id);

}
