package com.diploma.ustu.repo;

import com.diploma.ustu.models.Entities.EntityOf;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface EntityOfRepo extends JpaRepository<EntityOf, Long> {

    @Transactional
    void deleteByIdEntity(Long idEntity);

    @Transactional
    void deleteByNameEntity(Long nameEntity);
}
