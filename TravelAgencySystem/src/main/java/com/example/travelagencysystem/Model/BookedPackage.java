package com.example.travelagencysystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class BookedPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "int not null")
    private Integer package_id;
    @Column(columnDefinition = "int not null")
    private Integer user_id;
    @Column(columnDefinition = "varchar(100) not null")
    private String title;
    @NotNull(message = "Number of travelers cannot be null")
    @Min(value = 1, message = "number of travelers must be greater than 0")
    @Column(columnDefinition = "int not null")
    private Integer numberOfTravelers;
    @Column
    private Integer days;
    @Column(columnDefinition = "double not null")
    private double totalPrice;
    @Column
    private Boolean completed = false;
    @Future(message = "date can only be in the future")
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startTravelDate;
    @Future(message = "date can only be in the future")
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endTravelDate;

}
