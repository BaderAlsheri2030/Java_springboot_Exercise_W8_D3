package com.example.travelagencysystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Type cannot be null")
    @Column(columnDefinition = "varchar(30) not null check(type = 'International' or type = 'Cruise' or type = 'Educational' or type = 'Domestic')")
    private String type;
    @NotNull(message = "title cannot be null")
    @Column(columnDefinition = "varchar(100) not null")
    private String title;
    @NotNull(message = "destination cannot be null")
    @Column(columnDefinition = "varchar(100) not null")
    private String destination;
    @NotNull(message = "Type cannot be null")
    @Column(columnDefinition = "varchar(2000) not null")
    private String content;
    @NotNull(message = "Type cannot be null")
    @Column(columnDefinition = "double not null")
    @Positive(message = "only positive numbers")
    private Double price;
    @NotNull(message = "guide cannot be null")
    @Column(columnDefinition = "boolean not null")
    private boolean guide;
    @NotNull(message = "Transfers cannot be null")
    @Column(columnDefinition = "boolean not null")
    private boolean transfer;
    @NotNull(message = "duration cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer duration;
    @NotNull(message = "capacity cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer capacityPerTrip;
    @Future(message = "date can only be in the future")
    @NotNull(message = "start Travel Date cannot be null")
    @Column()
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startTravelDate;
    @Future(message = "date can only be in the future")
    @NotNull(message = "end Travel Date cannot be null")
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endTravelDate;



}
