package com.example.Teacher.Teacher.Controller;

import com.example.Teacher.Teacher.Entity.Student;
import com.example.Teacher.Teacher.Entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TeacherController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getTeachers")
    public ResponseEntity<Teacher> getTeachers() {
        // Call to STUDENT-SERVICE, expecting a ResponseEntity<Student>
        ResponseEntity<Student> response = restTemplate.getForEntity("http://STUDENT/getStudent", Student.class);

        // Check if the response status is OK (200)
        if (response.getStatusCode() == HttpStatus.OK) {
            Student student = response.getBody();  // Get the Student object from the response
            Teacher teacher = new Teacher(1, "Aditya", student);
            return ResponseEntity.ok(teacher);
        } else {
            return ResponseEntity.status(response.getStatusCode()).build();
        }
    }
}


