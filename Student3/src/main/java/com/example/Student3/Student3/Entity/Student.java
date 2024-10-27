package com.example.Student3.Student3.Entity;

import lombok.Data;
import org.springframework.context.annotation.Bean;

@Data
public class Student {
    int id;
    String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

