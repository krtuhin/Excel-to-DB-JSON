package com.exceltodbjson.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "excel_student")
public class Student {

    @Id
    private Integer roll;
    private String name;
    private Long phone;
    private String email;
}
