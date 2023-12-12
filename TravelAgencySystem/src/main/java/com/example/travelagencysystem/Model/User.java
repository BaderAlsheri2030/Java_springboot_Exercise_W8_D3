package com.example.travelagencysystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "username cannot be null")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String username;
    @NotNull(message = "username cannot be null")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "name must contain only letters")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String fullName;
    @NotNull(message = "password cannot be null")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Password must contain numbers and letters")
    @Column(columnDefinition = "varchar(20) not null")
    private String password;
    @NotNull(message = "Phone number cannot be null")
    @Column(columnDefinition = "varchar(20) not null")
    private String phoneNumber;
    @NotNull(message = "email cannot be null")
    @Email(message = "must be a valid email")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String email;
    @NotNull(message = "card cannot be null")
    @Column(columnDefinition = "int not null unique")
    private Integer card;
    @Column
    private boolean discount = false;
    @Column
    private LocalDate registration_date = LocalDate.now();

}
