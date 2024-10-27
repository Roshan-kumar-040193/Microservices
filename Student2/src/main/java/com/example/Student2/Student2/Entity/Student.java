package com.example.Student2.Student2.Entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Student {
    int id;
    String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
