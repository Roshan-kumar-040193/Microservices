package com.example.microservices.Employee.Dao;

import com.example.microservices.Employee.Entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDao {
    @Autowired
    EntityManager entityManager;

    public Employee getEmployeeById(int id){
        Query q=entityManager.createNativeQuery("select * from emp where emp_Id= :id", Employee.class);
        q.setParameter("id",id);
        return (Employee) q.getSingleResult();
    }

    public List<Employee> getEmployeeList(int id){
        Query q=entityManager.createNativeQuery("select * from emp", Employee.class);
        return q.getResultList();
    }

    // Update Employee
    public Employee updateEmployee(int id, String newName, int newAge, String newDept) {
        Employee employee = entityManager.find(Employee.class, id);
        if (employee != null) {
            employee.setName(newName);  // Update fields as needed
            employee.setAge(newAge);
            employee.setDept(newDept);
            entityManager.merge(employee);  // Use merge for updating
        }
        else{
            Employee emp = new Employee();
            emp.setAge(newAge);
            emp.setDept(newDept);
            emp.setName(newName);
            emp.setEmp_id(id);
            addEmployee(emp);
        }
        return employee;  // Return updated employee
    }

    // Delete Employee
    public void deleteEmployee(int id) {
        Employee employee = entityManager.find(Employee.class, id);
        if (employee != null) {
            entityManager.remove(employee);  // Remove the entity
        }
    }

    // Add Employee
    public Employee addEmployee(Employee employee) {
        entityManager.persist(employee);  // Persist new employee
        return employee;  // Return the newly added employee
    }


}
