package com.exceltodbjson.helper;

import com.exceltodbjson.models.Student;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {

    //check the given file is excel or not
    public static boolean checkExcelFormat(MultipartFile file) {

        String contentType = file.getContentType();

        if (contentType.trim().
                equalsIgnoreCase("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {

            return true;
        } else {
            return false;
        }
    }

    //convert excel-file to list
    public static List<Student> convertExcelToStudentList(InputStream is) {

        List<Student> list = new ArrayList<>();
        try {

            //fetch total excel file
            XSSFWorkbook workBook = new XSSFWorkbook(is);

            //fetch sheet from the file
            XSSFSheet sheet = workBook.getSheet("SHEET-1");

            //row number
            int rowNumber = 0;

            //fetch list of row
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {

                //getting single row
                Row row = iterator.next();

                //ignore heading
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                //fetch list cells from each row
                Iterator<Cell> cells = row.iterator();

                //cell number
                int cid = 0;

                Student student = new Student();

                while (cells.hasNext()) {

                    //fetch single cell
                    Cell cell = cells.next();

                    //setting particular cell data into student object
                    switch (cid) {
                        case 1:
                            student.setRoll((int) cell.getNumericCellValue());
                            break;
                        case 2:
                            student.setName(cell.getStringCellValue());
                            break;
                        case 5:
                            student.setPhone((long) cell.getNumericCellValue());
                            break;
                        case 6:
                            student.setEmail(cell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;
                }

                //if data not null
                if (student.getRoll() != null && student.getRoll() != 0) {

                    //add student into student list
                    list.add(student);
                }
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return list;
    }
}
