package com.diploma.ustu.repo;

import com.diploma.ustu.models.Entities.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface AttributeRepo extends JpaRepository<Attribute, Long> {

    @Transactional
    void deleteByNameAttributeIgnoreCase(String nameAttribute);

    @Modifying
    @Transactional
    @Query(
            value = "UPDATE attribute SET id_entity = ?2 WHERE id_attribute = ?1",
            nativeQuery = true
    )
    void updateAttributeEntityId(Long idAttribute, Long idEntityToUpdate);

    @Query(
            value = "alter sequence attribute_id_sequence restart",
            nativeQuery = true
    )
    void restartSequence();

    @Query(
            value = "SELECT id_attribute, name_attribute, id_entity FROM attribute a where a.id_entity = ?1",
            nativeQuery = true
    )
    List<Attribute> findAttributeByIdEntity(Long IdEntity);

}
