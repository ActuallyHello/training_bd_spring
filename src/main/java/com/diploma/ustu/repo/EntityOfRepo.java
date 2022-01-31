package com.diploma.ustu.repo;

import com.diploma.ustu.models.Entities.EntityOf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface EntityOfRepo extends JpaRepository<EntityOf, Long> {

    @Transactional
    void deleteByIdEntity(Long idEntity);

    @Transactional
    void deleteByNameEntity(Long nameEntity);

    Optional<EntityOf> findByIdEntity(Long IdEntity);
}
