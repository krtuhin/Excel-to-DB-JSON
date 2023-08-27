package com.exceltodbjson.repo;

import com.exceltodbjson.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
