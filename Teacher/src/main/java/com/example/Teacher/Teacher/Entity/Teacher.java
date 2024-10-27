package com.example.Teacher.Teacher.Entity;

import lombok.Data;

@Data
public class Teacher {
   int id;
   String Name;
   Student studentList;

    public Teacher(int id, String name, Student studentList) {
        this.id = id;
        Name = name;
        this.studentList = studentList;
    }
}
