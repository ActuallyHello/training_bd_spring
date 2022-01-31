package com.diploma.ustu.generators;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Component
public class ExcelData {
//    public Map<String, List<String>> getAllData();

    // function will return column with key in header
    private static int findColumnWithKey(String key, Sheet sheet) {
        int rowIndex = 0; int colIndex = 0;
        Row row = sheet.getRow(rowIndex); // take row
        while (row != null) {
            Cell cell = row.getCell(colIndex); // take cell from row
            while (cell != null) {
                if (key.equals(cell.toString())) { // if header = key
                    return colIndex;
                }
                colIndex++;
                cell = row.getCell(colIndex);
            }
            colIndex = 0;
            rowIndex++;
            row = sheet.getRow(rowIndex);
        }
        return -1; // if nothing was found
    }

    private static List<String> getValuesFromExcel(int columnIndex, Sheet sheet) {
        List<String> dataList = new ArrayList<>();
        int rowIndex = 1;
        if (columnIndex == -1) return new ArrayList<>();
        Row row = sheet.getRow(rowIndex);
        while (row != null && row.getCell(columnIndex) != null) {
            dataList.add(row.getCell(columnIndex).toString());
            rowIndex++;
            row = sheet.getRow(rowIndex);
        }
        return dataList;
    }

    //TODO:
    //1. change path to file with classpath

    // function will return List of values by the key from excel file
    public static List<String> getTestDataByKey(String key) {
        List<String> dataList = new ArrayList<>();
        try (FileInputStream file = new FileInputStream(new File("D:\\ДИПЛОМ\\SpringDiploma\\ustu\\src\\main\\resources\\data\\data.xlsx"))) {
            XSSFWorkbook wb = new XSSFWorkbook(file);
            XSSFSheet sheet = wb.getSheetAt(0);
            dataList = getValuesFromExcel(
                    findColumnWithKey(key, sheet),
                    sheet);
        } catch (IOException ex) {
            System.out.println("NOTHING HERE");
            //ex.printStackTrace();
        }
        return dataList;
    }

    public static void main(String[] args) {
        System.out.println(getTestDataByKey("имя"));
        System.out.println(getTestDataByKey("сотрудник"));
        System.out.println(getTestDataByKey("ALOHA"));
//        String kok = "id";
//        String kok2 = "Имя_ывфывф";
//        System.out.println(kok2.toLowerCase(Locale.ROOT).replaceAll("_.+|\\s.+", ""));
//        System.out.println(kok.toLowerCase(Locale.ROOT).replaceAll("_.+|\\s.+", ""));
    }
}
