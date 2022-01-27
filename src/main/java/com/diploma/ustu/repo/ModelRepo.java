package com.diploma.ustu.repo;

import com.diploma.ustu.models.Entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ModelRepo extends JpaRepository<Model, Long> {

    Model findByIdModel(Long id);

    @Modifying
    @Transactional
    @Query(
            value = "update model set student_book = ?2 where id_model = ?1",
            nativeQuery = true
    )
    int updateModelOwner(Long id, String student_book);
}
