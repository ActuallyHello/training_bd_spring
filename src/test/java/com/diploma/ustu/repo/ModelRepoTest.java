package com.diploma.ustu.repo;

import com.diploma.ustu.models.Entities.Model;
import com.diploma.ustu.models.Entities.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ModelRepoTest {

    @Autowired
    private ModelRepo modelRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Test
    public void testPrintAllModels() {
        List<Model> models =
                modelRepo.findAll();
        System.out.println("models = " + models);
    }

    @Test
    public void testUpdateModelOwner() {

        Student student = studentRepo.findByStudentBook("222222");
        modelRepo.updateModelOwner(4L, student.getStudentBook());
        modelRepo.updateModelOwner(5L, student.getStudentBook());
    }

    @Test
    public void testAddingToModel() {

        Model model = Model.builder()
                .nameModel("ТРЕТЬЯ")
                .build();

        modelRepo.save(model);

//        Student student = studentRepo.findByStudentBook("111111");
//        System.out.println(student);
//
//        Model model = Model.builder()
//                .name_model("Вторая")
//                .build();
//
//        System.out.println(modelRepo.save(model));
    }
}