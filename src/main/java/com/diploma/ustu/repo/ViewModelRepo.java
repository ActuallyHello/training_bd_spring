package com.diploma.ustu.repo;

import com.diploma.ustu.models.Entities.Model;
import com.diploma.ustu.models.Views.ViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ViewModelRepo extends JpaRepository<Model, Long> {

    @Query(
            value = "SELECT MODEL, STUDENT, MAJOR from view_model",
            nativeQuery = true
    )
    List<ViewModel> getViewModel();
}
