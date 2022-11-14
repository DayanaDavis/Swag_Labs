package com.swagLabs.utilities;

import com.swagLabs.constants.Constants;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtility {
    public Object[][] readDataFromExcelUsingDataFormatter(String filePath, String sheetName) throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        int noOfRows = sheet.getLastRowNum();
        int noOfColumns = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[noOfRows][noOfColumns];
        for (int i = 1; i <= noOfRows; i++) {
            for (int j = 0; j < noOfColumns; j++) {
                DataFormatter formatter = new DataFormatter();
                data[i - 1][j] = formatter.formatCellValue(sheet.getRow(i).getCell(j));
            }
            System.out.println();
        }
        file.close();
        return data;
    }
    public String readSingleData(int i, int j, String sheetname) {
        String filepath = System.getProperty("user.dir") + Constants.EXCEL_FILE;
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filepath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        XSSFSheet sheet = wb.getSheet(sheetname);
        Row r = sheet.getRow(i);
        Cell c = r.getCell(j);
        DataFormatter formatter = new DataFormatter();
        String value = formatter.formatCellValue(sheet.getRow(i).getCell(j));
        return value;
    }
}
