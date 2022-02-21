package com.diploma.ustu.repo;

import com.diploma.ustu.models.Entities.EntityDB;
import com.diploma.ustu.models.Entities.Relation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RelationRepo extends JpaRepository<Relation, Long> {

    Relation findByEntitiesFromAndEntitiesTo(EntityDB from, EntityDB to);

    @Query(
            value = "alter sequence relation_id_sequence restart",
            nativeQuery = true
    )
    void restartSequence();
}
