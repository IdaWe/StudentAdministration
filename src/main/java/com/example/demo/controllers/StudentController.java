package com.example.demo.controllers;
import com.example.demo.models.Student;
import com.example.demo.repositories.IStudentRepository;
import com.example.demo.repositories.InMemoryStudentRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {


    private IStudentRepository studentRepository;


    @Autowired
    public StudentController() {
        studentRepository = new InMemoryStudentRepositoryImpl();
    }


    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("students", studentRepository.readAll());
        return "index";
    }


    @GetMapping("/createStudent")
    public String createStudent(Model model){
        model.addAttribute("student", new Student());
        return "student/createStudent";
    }

    @PostMapping("/createStudent")
    public String saveStudent(@ModelAttribute Student student){
        studentRepository.create(student);
        return "redirect:/";
    }



    @GetMapping("/deleteStudent")
    public String deleteStud(Model model, @RequestParam int id){
        Student stu = studentRepository.read(id);
        model.addAttribute("student", stu);
        return "student/deleteStudent";
    }

    @PostMapping ("student/deleteStudent")
    public String deleteForGood(int id){
        studentRepository.delete(id);
        return "redirect:/";
    }


    @GetMapping("/updateStudent")
    public String edit(Model model, @RequestParam int id){
        Student stu = studentRepository.read(id);
        model.addAttribute("student", stu);
        return "student/updateStudent";
    }

    //Very simple prototype of GET-request with parameter
    //https://www.baeldung.com/spring-request-param
    //TODO Direct to detailed view of student
    @GetMapping("/student")
    @ResponseBody
    public String getStudentByParameter(@RequestParam int id) {
        Student stu = studentRepository.read(id);
        return "The id is " + stu.getFirstName() + " " + stu.getCpr(); //http://localhost:8080/student?id=2
    }


}