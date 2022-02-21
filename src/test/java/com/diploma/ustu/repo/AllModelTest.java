package com.diploma.ustu.repo;

import com.diploma.ustu.models.Entities.*;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestMethod;

import java.util.List;

@SpringBootTest
public class AllModelTest {

    private EntityDBRepo entityDBRepo;
    private AttributeRepo attributeRepo;
    private UserRepo userRepo;
    private StudentRepo studentRepo;
    private ModelRepo modelRepo;
    private RelationRepo relationRepo;

    @Autowired
    public AllModelTest(EntityDBRepo entityDBRepo, AttributeRepo attributeRepo, UserRepo userRepo, StudentRepo studentRepo, ModelRepo modelRepo, RelationRepo relationRepo) {
        this.entityDBRepo = entityDBRepo;
        this.attributeRepo = attributeRepo;
        this.userRepo = userRepo;
        this.studentRepo = studentRepo;
        this.modelRepo = modelRepo;
        this.relationRepo = relationRepo;
    }

    @Test
    public void testEntitiesRepo(){
        List<EntityDB> list = entityDBRepo.findAll();

        Assertions.assertEquals(0, list.size());

        entityDBRepo.save(new EntityDB("Сотрудник"));

        list = entityDBRepo.findAll();

        Assertions.assertEquals(1, list.size());
    }

    @Test
    public void testAttributesRepo() {
        List<Attribute> list = attributeRepo.findAll();

        Assertions.assertEquals(0, list.size());

        attributeRepo.save(new Attribute("id сотрудника"));
        attributeRepo.save(new Attribute("имя сотрудника"));

        list = attributeRepo.findAll();

        Assertions.assertEquals(2, list.size());
    }

    @Test
    public void testRelationRepo() {
        Relation relation = new Relation();
//        relation.setEntitiesTo(new EntityDB("Сотрудник"));
//        relation.setEntitiesFrom(new EntityDB("Компания"));
//        relation.setPower("1-n");
//        relationRepo.save(relation);
        System.out.println(relationRepo.findByEntitiesFromAndEntitiesTo(entityDBRepo.findByIdEntity(3L).get(), entityDBRepo.findByIdEntity(4L).get()));
        Assertions.assertEquals(1L, relation.getIdRelation());
    }

    @Test
    public void testModelRepo() {
        modelRepo.save(new Model("Корпорация"));
        Assertions.assertEquals(1, modelRepo.findAll().size());
    }

    @Test
    public void testStudentRepo() {
        studentRepo.save(new Student("999999", "aaa", "bbb", "major", "lll@mail.ru"));
        Assertions.assertEquals(1, studentRepo.findAll().size());
    }

    @Test
    public void testUserRepo() {
        userRepo.save(new User("123", "USER"));

    }

    @AfterEach
    public void clear() {
//        entityDBRepo.deleteAll();
//        entityDBRepo.restartSequence();
//        attributeRepo.deleteAll();
//        attributeRepo.restartSequence();
    }
}
