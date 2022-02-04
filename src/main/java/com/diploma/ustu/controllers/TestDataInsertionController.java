package com.diploma.ustu.controllers;

import com.diploma.ustu.generators.ExcelData;
import com.diploma.ustu.models.Entities.Student;
import com.diploma.ustu.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
//@Controller
public class TestDataInsertionController {

//    @GetMapping
//    public List<Person> index() {
//        List<String> names = ExcelData.getTestDataByKey("firstname", 1);
//        List<String> surnames = ExcelData.getTestDataByKey("lastname", 2);
//        List<String> lastnames = ExcelData.getTestDataByKey("middlename", 1);
//
//        List<Person> persons = new ArrayList<>();
//        for (int i = 0; i < surnames.size(); i++) {
//            Person p = new Person();
//            p.surname = surnames.get(i);
//            p.name = names.get(i);
//            p.lastname = lastnames.get(i);
//            persons.add(p);
//        }
//
//        return persons;
//    }
//
//    class Person {
//
//        public String name;
//        public String surname;
//        public String lastname;
//
//        @Override
//        public String toString() {
//            return "person = [" +
//                    surname + " " +
//                    name + " " +
//                    lastname + " " +
//                    "]";
//        }
//    }
//
//    @Autowired
//    private StudentRepo studentRepo;
//
//
//    @GetMapping("/students")
//    public List<Student> getStudents() {
//        return studentRepo.findAll();
//    }
//
//    @PostMapping("/students")
//    public List<Student> addStudent(@RequestParam String student_book,
//                                    @RequestParam String name,
//                                    @RequestParam String surname,
//                                    @RequestParam String major) {
//        System.out.println(student_book + " " + surname);
//        System.out.println("ALOHALO");
//        studentRepo.save(new Student(student_book, surname, name, major));
//
//        return studentRepo.findAll();
//    }
}
