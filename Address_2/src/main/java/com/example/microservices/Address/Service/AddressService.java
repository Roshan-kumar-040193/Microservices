package com.example.microservices.Address.Service;

import com.example.microservices.Address.Dao.AddressDao;
import com.example.microservices.Address.Model.AddressResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AddressService {

    @Autowired
    AddressDao addDao;

    public CompletableFuture<AddressResponse> getEmployeeById(int id){
        return CompletableFuture.supplyAsync(() -> addDao.getAddressById(id))
                .thenApply(add -> {
                    AddressResponse addResponse = new AddressResponse();
                    addResponse.setCity(add.getCity());
                    addResponse.setState(add.getState());
                    addResponse.setCountry(add.getCountry());
                    addResponse.setPostalCode(add.getPostalCode());
                    addResponse.setStreet(add.getStreet());
                    return addResponse;
                })
                .exceptionally(throwable -> {
                    // Log or handle the exception here
                    throwable.printStackTrace();
                    return null;
                });
    }
}
