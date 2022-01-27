package com.diploma.ustu.repo;

import com.diploma.ustu.models.Entities.Student;
import com.diploma.ustu.models.Views.ViewStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ViewStudentRepo extends JpaRepository<Student, Long> {

    @Query(
            value = "select ID_MODEL, LAST_NAME, NAME_MODEL from view_student",
            nativeQuery = true
    )
    List<ViewStudent> getViewModel();
}
