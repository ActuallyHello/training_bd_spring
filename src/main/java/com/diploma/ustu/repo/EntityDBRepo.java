package com.diploma.ustu.repo;

import com.diploma.ustu.models.Entities.EntityDB;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface EntityDBRepo extends JpaRepository<EntityDB, Long> {

    @Transactional
    void deleteByIdEntity(Long idEntity);

    @Transactional
    void deleteByNameEntity(Long nameEntity);

    Optional<EntityDB> findByIdEntity(Long IdEntity);
}
