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

    private static List<String> getValuesFromExcel(int columnIndex, Sheet sheet, int size) {
        List<String> dataList = new ArrayList<>();
        int rowIndex = 1;
        if (columnIndex == -1) return new ArrayList<>();
        Row row = sheet.getRow(rowIndex);
        while (row != null && row.getCell(columnIndex) != null && rowIndex <= size) {
            Cell cell = row.getCell(columnIndex);
            switch (cell.getCellType()) {
                case STRING:
                    dataList.add(cell.toString());
                    break;
                case NUMERIC:
                    dataList.add(String.valueOf((int)cell.getNumericCellValue()));
                    break;
                case BLANK:
                    System.out.println("ПУСТОЕ ЗНАЧЕНИЕ EXCEL DATA FROM COLUMN INDEX (" + columnIndex + ") AND ROW INDEX (" + rowIndex + ");"); // поменять на Log
                    break;
                case _NONE:
                    System.out.println("NULL NONE значение EXCEL DATA FROM COLUMN INDEX (" + columnIndex + ") AND ROW INDEX (" + rowIndex + ");");
                    break;
                default:
                    System.out.println("UNKNOW на EXCEL DATA FROM COLUMN INDEX (" + columnIndex + ") AND ROW INDEX (" + rowIndex + ")");
            }
//            dataList.add(row.getCell(columnIndex).toString());
            rowIndex++;
            row = sheet.getRow(rowIndex);
        }
        return dataList;
    }

    //TODO:
    //1. change path to file with classpath

    // function will return List of values by the key from excel file
    public static List<String> getTestDataByKey(String key, int size) {
        List<String> dataList = new ArrayList<>();
        try (FileInputStream file = new FileInputStream(new File("D:\\ДИПЛОМ\\SpringDiploma\\ustu\\src\\main\\resources\\data\\data.xlsx"))) {
            XSSFWorkbook wb = new XSSFWorkbook(file);
            XSSFSheet sheet = wb.getSheetAt(0);
            dataList = getValuesFromExcel(
                    findColumnWithKey(key, sheet),
                    sheet, size);
        } catch (IOException ex) {
            System.out.println("NOTHING HERE");
            ex.printStackTrace();
        }
        return dataList;
    }
}
