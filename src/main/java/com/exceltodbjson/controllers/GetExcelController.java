package com.exceltodbjson.controllers;

import com.exceltodbjson.services.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
public class GetExcelController {

    @Autowired
    private ExcelService excelService;

    //handler for getting excel file from database
    @GetMapping("/get-excel")
    public ResponseEntity<Resource> getExcel() {

        //file name
        String fileName = "students.xlsx";

        //data sa ByteArray
        ByteArrayInputStream allStudents = this.excelService.getAllStudents();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(new InputStreamResource(allStudents));
    }
}
