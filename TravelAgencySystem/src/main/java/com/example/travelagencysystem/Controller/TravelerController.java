package com.example.travelagencysystem.Controller;

import com.example.travelagencysystem.Api.ApiResponse;
import com.example.travelagencysystem.Model.Travelers;
import com.example.travelagencysystem.Service.TravelerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/traveler")
@RequiredArgsConstructor
public class TravelerController {



    private final TravelerService service;
    Logger logger = LoggerFactory.getLogger(TravelerController.class);


    @GetMapping("/get")
    public ResponseEntity getTravelers(){
        logger.info("displayed travelers");
        return ResponseEntity.status(200).body(service.getTravelers());
    }
    @PostMapping("/add/{bookedPack_id}/{user_id}")
    public ResponseEntity addTraveler(@PathVariable Integer bookedPack_id, @PathVariable Integer user_id, @Valid @RequestBody Travelers traveler){
        logger.info("add traveler");
        service.addTravelerInformation(bookedPack_id,user_id,traveler);
        return ResponseEntity.status(HttpStatus.OK).body("Traveler added to the list");
    }

    @PostMapping("/update/{traveler_id}/{bookedPack_id}")
    public ResponseEntity updateTraveler(@PathVariable Integer traveler_id, @PathVariable Integer bookedPack_id, @Valid @RequestBody Travelers traveler){
        logger.info("updated traveler");
        service.updateTraveler(traveler_id,bookedPack_id,traveler);
        return ResponseEntity.status(HttpStatus.OK).body("Traveler updated");
    }

    @DeleteMapping("/delete/{traveler_id}/{bookedPack_id}")
    public ResponseEntity deleteTraveler(@PathVariable Integer traveler_id, @PathVariable Integer bookedPack_id){
        logger.info("delete traveler");
        service.deleteTraveler(traveler_id,bookedPack_id);
        return ResponseEntity.status(HttpStatus.OK).body("Traveler deleted");
    }
    @GetMapping("travelers/{user_id}")
    public ResponseEntity displayTravelersByUserId(@PathVariable Integer user_id){
        logger.info("display account travelers");
        return ResponseEntity.status(HttpStatus.OK).body(service.getTravelersByUserId(user_id));
    }

}
