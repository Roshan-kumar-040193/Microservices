package com.example.Student3.Student3.Controller;

import com.example.Student3.Student3.Entity.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetStudent {
    @GetMapping(value = "/getStudent", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> getStudent() {
        Student student = new Student(3, "Ram");
        System.out.println("Student 3" + student);
        return ResponseEntity.ok(student);
    }
}
