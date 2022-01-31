package com.diploma.ustu.services;

import com.diploma.ustu.generators.ExcelData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ConstructorServiceTest {

    @Autowired
    ConstructorService constructorService;

//    @Autowired
//    ExcelData excelData;

    @Test
    public void testIsItWork() {
        //constructorService.test();
        constructorService.construct();
    }
}