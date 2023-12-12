package com.example.travelagencysystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@Entity
@RequiredArgsConstructor
public class Travelers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer bookedPackageId;
    @Column
    private Integer userId;
    @NotNull(message = "full name cannot be null")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String fullName;
    @NotNull(message = "date of birth cannot be null")
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    @NotNull(message = "Passport info cannot be null")
    @Column(columnDefinition = "varchar(60) not null")
    private String passportInfo;

}


