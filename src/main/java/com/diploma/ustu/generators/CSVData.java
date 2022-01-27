package com.diploma.ustu.generators;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class CSVData {

    public void readCSV() {

        String line = "";
        String splitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\ДИПЛОМ\\SpringDiploma\\ustu\\src\\main\\resources\\data\\test.csv"))){
            while ((line = br.readLine()) != null) {
                String[] employee = line.split(splitBy);
                System.out.println(Arrays.toString(employee));
                //System.out.println("Emp[First Name=" + employee[1] + ", Last Name=" + employee[2] + ", Contact=" + employee[3] + ", City= " + employee[4] + "]");
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

}
