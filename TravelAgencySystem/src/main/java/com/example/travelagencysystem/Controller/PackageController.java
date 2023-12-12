package com.example.travelagencysystem.Controller;

import com.example.travelagencysystem.Model.Package;
import com.example.travelagencysystem.Service.PackageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/package")
@RequiredArgsConstructor
public class PackageController {

    private final PackageService service;

    Logger logger = LoggerFactory.getLogger(PackageController.class);

    @GetMapping("/get")
    public ResponseEntity getPackages(){
        logger.info("get packages");
        return ResponseEntity.status(HttpStatus.OK).body(service.getPackages());
    }
    @PostMapping("/add")
    public ResponseEntity addPackage(@Valid @RequestBody Package p ){
        logger.info("add package");
        service.addPackage(p);
        return ResponseEntity.status(HttpStatus.OK).body("Package Added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updatePackage(@PathVariable Integer id, @Valid @RequestBody Package p){
        logger.info("update package");
        service.updatePackage(id,p);
        return ResponseEntity.status(HttpStatus.OK).body("Package Updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePackage(@PathVariable Integer id){
        logger.info("delete package");
        service.deletePackage(id);
        return ResponseEntity.status(HttpStatus.OK).body("Package deleted");
    }

    @GetMapping("/title/{word}")
    public ResponseEntity searchTitle(@PathVariable String word){
        logger.info("search titles");
        return ResponseEntity.status(HttpStatus.OK).body(service.searchPackagesTitle(word));
    }

    @GetMapping("/price/{min}/{max}")
    public ResponseEntity searchPackageByPrice(@PathVariable double min,@PathVariable double max){
        logger.info("search prices");
        return ResponseEntity.status(HttpStatus.OK).body(service.searchPackageByPrice(min,max));
    }

    @GetMapping("/type/{word}")
    public ResponseEntity searchType(@PathVariable String word){
        logger.info("search package types");
        return ResponseEntity.status(HttpStatus.OK).body(service.searchPackageByType(word));
    }

    @GetMapping("/content/{word}")
    public ResponseEntity searchContent(@PathVariable String word){
        logger.info("search packages content");
        return ResponseEntity.status(HttpStatus.OK).body(service.searchPackageContentContaining(word));
    }

    @GetMapping("/city/{word}")
    public ResponseEntity searchDestination(@PathVariable String word){
        logger.info("search packages city");
        return ResponseEntity.status(HttpStatus.OK).body(service.searchPackageCity(word));
    }

    @GetMapping("/days/{number}")
    public ResponseEntity searchPackagesByDays(@PathVariable Integer number){
        logger.info("search packages days");
        return ResponseEntity.status(HttpStatus.OK).body(service.searchPackagesByDays(number));
    }

    @GetMapping("/guide")
    public ResponseEntity searchPackagesByGuide(){
        logger.info("search packages guide");
        return ResponseEntity.status(HttpStatus.OK).body(service.searchPackageWithGuide());
    }

    @GetMapping("/dates/{start}/{end}")
    public ResponseEntity searchPackagesByDatesBetween(@PathVariable LocalDate start, @PathVariable LocalDate end){
        logger.info("search packages dates");
        return ResponseEntity.status(HttpStatus.OK).body(service.searchPackageDateRange(start,end));
    }

    @GetMapping("/capacity/{n}")
    public ResponseEntity searchPackageByCapacity(@PathVariable Integer n){
        logger.info("search package capacity");
        return ResponseEntity.status(HttpStatus.OK).body(service.searchPackageCapacity(n));
    }

    @GetMapping("/transfers")
    public ResponseEntity searchPackagesWithTransfers(){
        logger.info("search packages transfers");
        return ResponseEntity.status(HttpStatus.OK).body(service.searchPackageWithTransfers());
    }

}
