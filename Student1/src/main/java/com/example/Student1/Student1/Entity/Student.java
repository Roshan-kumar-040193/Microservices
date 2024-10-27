package com.example.Student1.Student1.Entity;

import lombok.Data;

@Data
public class Student {
    int id;
    String name;

    public Student(int i, String sName) {
        this.id=i;
        this.name=sName;
    }
}
