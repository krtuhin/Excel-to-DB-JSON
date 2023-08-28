package com.exceltodbjson.services;

import com.exceltodbjson.helper.Helper;
import com.exceltodbjson.models.Student;
import com.exceltodbjson.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class ExcelService {

    @Autowired
    private StudentRepository studentRepository;

    //method to get list of students from database as ByteArray
    public ByteArrayInputStream getAllStudents() {

        List<Student> students = this.studentRepository.findAll();

        return Helper.dataToExcel(students);
    }
}
