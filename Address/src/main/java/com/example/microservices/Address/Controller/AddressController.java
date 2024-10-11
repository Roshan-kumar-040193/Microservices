package com.example.microservices.Address.Controller;

import com.example.microservices.Address.Model.AddressResponse;
import com.example.microservices.Address.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping("/getAddress/{id}")
    public CompletableFuture<AddressResponse> getAddressById(@PathVariable int id){
        return addressService.getEmployeeById(id);
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
