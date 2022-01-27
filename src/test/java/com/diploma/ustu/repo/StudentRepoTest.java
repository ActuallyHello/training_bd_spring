package com.diploma.ustu.repo;

import com.diploma.ustu.models.Entities.Model;
import com.diploma.ustu.models.Entities.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StudentRepoTest {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private ModelRepo modelRepo;

//    @Test
//    public void testTryToAddMoreModelsToAlreadyExistStudent() {
//        Student student = studentRepo.findByStudentBook("111111");
//        List<Model> models = List.of(modelRepo.findByIdModel(4L),
//                                     modelRepo.findByIdModel(5L));
//        student.setModels(models);
//
//        //student.setModels();
//    }

    @Test
    public void testAddingToStudent() {

        List<Model> models = modelRepo.findAll();

        Student student = Student.builder()
                .studentBook("444444")
                .firstName("Антон")
                .lastName("Лобанов")
                .major("ИСТ")
                .models(models)
                .build();

        studentRepo.save(student);
    }

    @Test
    public void testPrintAllStudents() {
        List<Student> students = studentRepo.findAll();
        System.out.println("students = " + students);
    }

    @Test
    public void testPrintAllStudentsWithMajor() {
        List<Student> students = studentRepo.findByMajor("ИСТ");
        System.out.println("students = " + students);
    }

    @Test
    public void testPrintStudentsWithFirstAndLastName() {
        
        List<Student> s = studentRepo.findByFirstName("Серегей");
        System.out.println("s = " + s);
        
        List<Student> students = studentRepo.findByFirstNameAndLastName("Серегей", "Ананьев");
        System.out.println("students = " + students);
    }
    
    @Test
    public void testJPQLStudentByBook() {
        Student student = 
                studentRepo.getStudentByStudentBook("111111");

        System.out.println("student = " + student);
        
        List<Student> students = 
                studentRepo.getStudentsByJPQL();
        System.out.println("students = " + students);
    }

    @Test
    public void testUpdateStudentByBook() {
        studentRepo.updateStudentByStudentBook("Михаил", "222222");
    }
}