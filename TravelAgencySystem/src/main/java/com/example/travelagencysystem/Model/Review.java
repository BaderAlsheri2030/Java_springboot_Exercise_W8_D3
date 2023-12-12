package com.example.travelagencysystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer user_id;
    @Column
    private Integer package_id;
    @NotNull(message = "review cannot be null")
    @Column(columnDefinition = "varchar(100)")
    private String review;
    @NotNull(message = "rating cannot be null")
    @Column(columnDefinition = "int not null")
    @Max(value = 10, message = "rating must be from 0-10")
    private Integer rating;






}
