package com.exceltodbjson.services;

import com.exceltodbjson.helper.ExcelHelper;
import com.exceltodbjson.models.Student;
import com.exceltodbjson.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    //method to save excel data into database
    public void save(MultipartFile file) throws IOException {

        //getting list of student object from excel-file
        List<Student> list = ExcelHelper.convertExcelToStudentList(file.getInputStream());

        //save list of students into database
        this.studentRepository.saveAll(list);
    }

    //method to get all data from database
    public List<Student> getAllStudents() {

        //getting all students from database
        return this.studentRepository.findAll();
    }
}
