/*package com.example.demo.repositories;

import com.example.demo.models.Student;
import com.example.demo.models.StudentDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class InMemoryStudentRepositoryImpl implements IStudentRepository{
    private List<StudentDTO> inMemoryDatabase;

    //fylder "fake" database med studerende her i konstrukteren
    public InMemoryStudentRepositoryImpl(){
        this.inMemoryDatabase = new ArrayList<Student>(
                Arrays.asList(
                        new Student(1, "Nicklas","Frederiksen", new Date(12312), "31134115-1231"),
                        new Student(2, "Bent","Karlsen", new Date(2141241), "31134115-4112"),
                        new Student(3, "Bob","Alicesen",new Date(12424141), "233124f14-5551")
                )
        );
    }

    @Override
    public void create(Student student) {
        inMemoryDatabase.add(student);
    }

    @Override
    public Student read(int id) {
        for(Student stu : inMemoryDatabase){
            if(stu.getId() == id){
                return stu;
            }
        }
        return null;
    }

    @Override
    public List<Student> readAll() {

        return inMemoryDatabase;
    }


    @Override
    public void update(Student student) {
        Student updateStudent = read(student.id);
        updateStudent.firstName=student.firstName;
        updateStudent.lastName=student.lastName;
        updateStudent.enrollmentDate=student.enrollmentDate;
        updateStudent.cpr=student.cpr;
    }

    @Override
    public void delete(int id) {
        Student deleteStudent = read(id);
        inMemoryDatabase.remove(deleteStudent);
    }
}*/
