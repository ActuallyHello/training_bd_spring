package com.diploma.ustu.services;

import com.diploma.ustu.models.Entities.EntityDB;
import com.diploma.ustu.repo.EntityDBRepo;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CollectServiceTest {

    @Autowired
    CollectService collectService;

    @Autowired
    EntityDBRepo entityDBRepo;

    @Test
    public void testCollectEntitiesAndPutThemToFile() throws IOException {
        EntityDB employees = entityDBRepo.findByIdEntity(4L).orElse(null);
        EntityDB company = entityDBRepo.findByIdEntity(5L).orElse(null);
        EntityDB corporation = entityDBRepo.findByIdEntity(6L).orElse(null);

        collectService.collectDataInSingleEntity(employees, 5);
        collectService.collectDataInSingleEntity(company, 3);
        collectService.collectDataInSingleEntity(corporation, 1);
        collectService.writeToFile("output1.txt");
        collectService.insertIdToFK();
//        collectService.collectToView();
        collectService.writeToFile("output2.txt");
    }

    @Test
    public void testListEntities() {
        List<EntityDB> list = new ArrayList<>();
        list.add(entityDBRepo.findByIdEntity(4L).orElse(null));
        list.add(entityDBRepo.findByIdEntity(5L).orElse(null));
        list.add(entityDBRepo.findByIdEntity(6L).orElse(null));
        List<Integer> sizes = new ArrayList<>();
        sizes.add(5);
        sizes.add(3);
        sizes.add(1);

        collectService.collectEntities(list, sizes);
    }

    @Test
    public void testFilePath() {
        System.out.println();
    }
}