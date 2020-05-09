package com.example.demo.repositories;

import com.example.demo.models.Student;

import java.util.List;

public interface IStudentRepository {
    // CRUD operations
    public void create(Student student);

    public Student read(int id);

    public List<Student> readAll();

    public void update(Student student);

    public void delete(int id);
}


