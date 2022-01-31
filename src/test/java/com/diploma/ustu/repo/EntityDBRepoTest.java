package com.diploma.ustu.repo;

import com.diploma.ustu.models.Entities.Attribute;
import com.diploma.ustu.models.Entities.EntityDB;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class EntityDBRepoTest {

    @Autowired
    private EntityDBRepo entityDBRepo;

    @Autowired
    private AttributeRepo attributeRepo;

    @Test
    public void testAddEntity() {
        EntityDB entity = new EntityDB("Компания");
        entityDBRepo.save(entity);
    }

    @Test
    public void testAddEntityWithAttributes() {
        Attribute attribute = new Attribute("id_корпорация");
        Attribute attribute2 = new Attribute("корпорация");
        Attribute attribute3 = new Attribute("fk сотрудник");
        Attribute attribute4 = new Attribute("fk компания");
        List<Attribute> attributes = new ArrayList<>(Arrays.asList(attribute, attribute2, attribute3, attribute4));
        System.out.println("attributes = " + attributes);

        EntityDB entity = new EntityDB("Корпорация");
        entity.setAttributes(attributes);

        entityDBRepo.save(entity);
    }

    @Test
    public void testDeleteEntity() {
        entityDBRepo.deleteByIdEntity(3L);
    }

    @Test
    public void testFindAll() {
        System.out.println("entityDBRepo.findAll() = " + entityDBRepo.findAll());
    }

    @Test
    public void testFindOne() {
        System.out.println("entityDBRepo.findOne(3) = " + entityDBRepo.findByIdEntity(3L).orElse(null));
    }
}