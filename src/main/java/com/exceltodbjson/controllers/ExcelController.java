package com.exceltodbjson.controllers;

import com.exceltodbjson.helper.ExcelHelper;
import com.exceltodbjson.models.Student;
import com.exceltodbjson.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("*")
public class ExcelController {

    @Autowired
    private StudentService studentService;

    //handler to save excel file data into database
    @PostMapping("/upload-excel")
    public ResponseEntity<?> saveExcel(@RequestParam("file") MultipartFile file) {

        //checking the uploaded file is excel or not
        if (!ExcelHelper.checkExcelFormat(file)) {

            return ResponseEntity.badRequest().body("Invalid file..!");
        }

        try {
            //saving data into database
            this.studentService.save(file);

        } catch (IOException e) {

            return ResponseEntity.internalServerError().body("File not saved..!");
        }
        return ResponseEntity.ok("Uploaded...!");
    }

    //handler for getting all students from database
    @GetMapping("/get-data")
    public ResponseEntity<?> getData() {

        //getting list of students from database
        List<Student> list = this.studentService.getAllStudents();

        return ResponseEntity.ok(list);
    }
}
