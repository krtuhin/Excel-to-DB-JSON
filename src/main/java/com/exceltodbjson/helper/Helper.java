package com.exceltodbjson.helper;

import com.exceltodbjson.models.Student;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class Helper {

    //column names
    private static String[] HEADERS = {"Name", "Roll No", "Email Id", "Phone No"};

    //SHEET name
    private static String SHEET_NAME = "Student Details";

    //method to convert json data into excel
    public static ByteArrayInputStream dataToExcel(List<Student> list) {

        Workbook workbook = new XSSFWorkbook();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        //create sheet name
        Sheet sheet = workbook.createSheet(SHEET_NAME);

        Row row = sheet.createRow(0);

        //setting column name
        for (int i = 0; i < HEADERS.length; i++) {

            Cell cell = row.createCell(i);
            cell.setCellValue(HEADERS[i]);

        }

        Integer rowIndex = 1;

        //creating rows according to list of students
        for (Student student : list) {

            Row dataRow = sheet.createRow(rowIndex);

            //inserting student data into cell
            dataRow.createCell(0).setCellValue(student.getName());
            dataRow.createCell(1).setCellValue(student.getRoll());
            dataRow.createCell(2).setCellValue(student.getEmail());
            dataRow.createCell(3).setCellValue(student.getPhone());

            rowIndex++;
        }
        try {
            workbook.write(outputStream);

            workbook.close();
            outputStream.close();

            return new ByteArrayInputStream(outputStream.toByteArray());

        } catch (IOException e) {

            e.printStackTrace();
            throw new RuntimeException("Error occurred during create outputStream..!");
        }
    }
}
