package com.diploma.ustu.repo;

import com.diploma.ustu.models.Entities.Attribute;
import com.diploma.ustu.models.Entities.EntityOf;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EntityOfRepoTest {

    @Autowired
    private EntityOfRepo entityOfRepo;

    @Autowired
    private AttributeRepo attributeRepo;

    @Test
    public void testAddEntity() {
        EntityOf entity = new EntityOf("Компания");
        entityOfRepo.save(entity);
    }

    @Test
    public void testAddEntityWithAttributes() {
        Attribute attribute = new Attribute("Колесо");
        Attribute attribute2 = new Attribute("Двигатель");
        Attribute attribute3 = new Attribute("Дверь");
        List<Attribute> attributes = new ArrayList<>(Arrays.asList(attribute, attribute2, attribute3));
        System.out.println("attributes = " + attributes);

        EntityOf entity = new EntityOf("Супер тачка");
        entity.setAttributes(attributes);

        entityOfRepo.save(entity);
    }

    @Test
    public void testDeleteEntity() {
        entityOfRepo.deleteByIdEntity(2L);
    }

}