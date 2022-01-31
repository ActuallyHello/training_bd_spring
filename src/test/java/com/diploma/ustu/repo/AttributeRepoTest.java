package com.diploma.ustu.repo;

import com.diploma.ustu.models.Entities.Attribute;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaSystemException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AttributeRepoTest {

    @Autowired
    private AttributeRepo attributeRepo;

//    Logger logger = LoggerFactory.getLogger("attributeRepo");

    @Test
    public void testFindAttribute() {
        System.out.println(attributeRepo.findAll());
    }

    @Test
    public void testAddAttribute() {
        Attribute attribute = new Attribute("Добавь что-нибудь");
        attributeRepo.save(attribute);
    }

    @Test
    public void testDeleteAttribute() {
        attributeRepo.deleteByNameAttributeIgnoreCase("рулька");
    }

    @Test
    public void testRestartSeq() {
        try {
            attributeRepo.restartSequence();
        } catch (JpaSystemException ex) {
//            logger.info(" // ATTRIBUTE SEQUENCE HAS BEEN RESTARTED //");
            System.out.println("attribute sequence has been restarted");
        }
    }

    @Test
    public void testUpdateEntityOfAttribute() {
        attributeRepo.updateAttributeEntityId(1L, 3L);
        attributeRepo.updateAttributeEntityId(2L, 3L);
    }

    @Test
    public void testFindAll() {
        System.out.println("attributeRepo.findAll() = " + attributeRepo.findAll());
    }

    @Test
    public void testFindAllByEntity() {
        System.out.println(attributeRepo.findAttributeByIdEntity(3L));
    }
}