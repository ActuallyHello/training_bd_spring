package com.diploma.ustu.services;

import com.diploma.ustu.generators.ExcelData;
import com.diploma.ustu.models.Entities.EntityDB;
import com.diploma.ustu.repo.EntityDBRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ConstructorServiceTest {

    @Autowired
    ConstructorService constructorService;

    @Autowired
    EntityDBRepo entityDBRepo;

//    @Autowired
//    ExcelData excelData;

    @Test
    public void testIsItWork() {
//        constructorService.countForeignKeys();
        //constructorService.testCountFKLIST();
        constructorService.collectEntities();
        constructorService.insertIdToFK();
    }

    @Test
    public void testAddingDataToEachEntity() {
        EntityDB employees = entityDBRepo.findByIdEntity(4L).orElse(null);
        EntityDB company = entityDBRepo.findByIdEntity(5L).orElse(null);
        EntityDB corporation = entityDBRepo.findByIdEntity(6L).orElse(null);

        constructorService.collectEntity(employees, 5);
        constructorService.collectEntity(company, 3);
        constructorService.collectEntity(corporation, 1);
        constructorService.insertIdToFK();
    }
}