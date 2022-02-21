package com.diploma.ustu.repo;

import com.diploma.ustu.models.Entities.EntityDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface EntityDBRepo extends JpaRepository<EntityDB, Long> {

    @Query(
            value = "alter sequence entity_id_sequence restart",
            nativeQuery = true
    )
    void restartSequence();

    @Transactional
    void deleteByIdEntity(Long idEntity);

    @Transactional
    void deleteByNameEntity(Long nameEntity);

    Optional<EntityDB> findByIdEntity(Long IdEntity);
}
