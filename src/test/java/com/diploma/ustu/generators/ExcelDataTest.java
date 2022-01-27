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
        List<String> data = ExcelData.getTestDataByKey("id");
        Collections.shuffle(data);
        System.out.println("data = " + data);
        Person person = new Person();
        person.name = ExcelData.getTestDataByKey("firstname");
        person.surname = ExcelData.getTestDataByKey("lastname");
        person.lastname = ExcelData.getTestDataByKey("middlename");
        System.out.println(person);
    }

    class Person {
        public List<String> name;
        public List<String> surname;
        public List<String> lastname;

        @Override
        public String toString() {
            StringJoiner sj = new StringJoiner(" ", "[", "]");
            for (int i = 0; i < name.size(); i++) {
                sj.add(name.get(i));
                sj.add(lastname.get(i));
                sj.add(surname.get(i));
                sj.add(",");
            }
            return sj.toString();
        }
    }
}