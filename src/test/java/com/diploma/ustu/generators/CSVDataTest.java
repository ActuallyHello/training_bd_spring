package com.diploma.ustu.generators;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CSVDataTest {

    CSVData csvData = new CSVData();

    @Test
    public void testDataFromCSV() {
        csvData.readCSV();
    }
}