package com.example.travelagencysystem.Controller;

import com.example.travelagencysystem.Model.User;
import com.example.travelagencysystem.Repository.TravelersRepository;
import com.example.travelagencysystem.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    Logger logger = LoggerFactory.getLogger(UserController.class);


    @GetMapping("/get")
    public ResponseEntity getUsers(){
        logger.info("displayed users");
        return ResponseEntity.status(HttpStatus.OK).body(service.getUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user){
        logger.info("add user");
        service.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body("User added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id,@Valid @RequestBody User user){
        logger.info("update user");
        service.updateUser(id,user);
        return ResponseEntity.status(HttpStatus.OK).body("User updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        logger.info("delete user");
        service.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted");
    }

    @PutMapping("/discount/{userId}")
    public ResponseEntity newAccountsDiscount(@PathVariable Integer userId){
        logger.info("applied discount");
        service.addDiscountToUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body("Offer discount added to account");
    }

}
