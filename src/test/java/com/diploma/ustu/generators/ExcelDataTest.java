package com.diploma.ustu.generators;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExcelDataTest {

    @Test
    public void testExtractionFromExcel() {
        List<String> data = ExcelData.getTestDataByKey("phone", 30);
        Collections.shuffle(data);
        System.out.println("data = " + data);
    }


}