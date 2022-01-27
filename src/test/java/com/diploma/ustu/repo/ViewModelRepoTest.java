package com.diploma.ustu.repo;

import com.diploma.ustu.models.Views.ViewModel;
import com.diploma.ustu.models.ViewsEntity.ViewModelEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ViewModelRepoTest {

    @Autowired
    ViewModelRepo viewModelRepo;

    @Test
    public void testView() {
        List<ViewModelEntity> model_students = new ArrayList<>();
        List<ViewModel> view = viewModelRepo.getViewModel();
        for (ViewModel v: view) {
            model_students.add(new ViewModelEntity(v.getMODEL(), v.getSTUDENT(), v.getMAJOR()));
        }
        System.out.println(model_students);
    }
}