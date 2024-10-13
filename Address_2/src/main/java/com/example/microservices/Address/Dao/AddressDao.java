package com.example.microservices.Address.Dao;

import com.example.microservices.Address.Entity.Address;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressDao {
    @Autowired
    EntityManager entityManager;

    public Address getAddressById(int id){
        Query q=entityManager.createNativeQuery("select * from address where emp_Id= :id", Address.class);
        q.setParameter("id",id);
        return (Address) q.getSingleResult();
    }

        // Update Address
        public Address updateAddress(int empId, String newStreet, String newCity, String newState, String newPostalCode, String newCountry) {
            Address address = entityManager.find(Address.class, empId);
            if (address != null) {
                // Update fields as needed
                address.setStreet(newStreet);
                address.setCity(newCity);
                address.setState(newState);
                address.setPostalCode(newPostalCode);
                address.setCountry(newCountry);
                entityManager.merge(address);  // Use merge for updating
            } else {
                // If the address doesn't exist, create a new one
                Address newAddress = new Address();
                newAddress.setEmpId(empId);
                newAddress.setStreet(newStreet);
                newAddress.setCity(newCity);
                newAddress.setState(newState);
                newAddress.setPostalCode(newPostalCode);
                newAddress.setCountry(newCountry);
                addAddress(newAddress);
                return newAddress;
            }
            return address;  // Return updated address
        }

        // Delete Address
        public void deleteAddress(int empId) {
            Address address = entityManager.find(Address.class, empId);
            if (address != null) {
                entityManager.remove(address);  // Remove the entity
            }
        }

        // Add Address
        public Address addAddress(Address address) {
            entityManager.persist(address);  // Persist new address
            return address;  // Return the newly added address
        }
    }
