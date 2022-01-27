package com.diploma.ustu.repo;

import com.diploma.ustu.models.Views.ViewStudent;
import com.diploma.ustu.models.ViewsEntity.ViewStudentEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ViewStudentRepoTest {

    @Autowired
    ViewStudentRepo viewStudentRepo;


    // НАДО ЧТОБЫ СТОЛБИКИ ПРИ QUERY СОВПАДАЛИ С VIEW
    // ИНТЕРФЕЙС РЕАЛИЗОВЫВАЛ ГЕТТЕРЫ С ТАКИМИ ЖЕ ИМЕННАМИ КАК И ВО ВЬЮ
    @Test
    public void getView() {
        List<ViewStudent> result = viewStudentRepo.getViewModel();
        List<ViewStudentEntity> student_view = new ArrayList<>();
        for (ViewStudent r: result) {
            student_view.add(new ViewStudentEntity(r.getID_MODEL(), r.getLAST_NAME(), r.getNAME_MODEL()));
        }
        System.out.println("student_view = " + student_view);
    }
}