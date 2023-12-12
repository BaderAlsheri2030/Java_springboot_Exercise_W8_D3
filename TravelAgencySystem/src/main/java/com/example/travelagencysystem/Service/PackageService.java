package com.example.travelagencysystem.Service;

import com.example.travelagencysystem.Api.ApiException;
import com.example.travelagencysystem.Model.Package;
import com.example.travelagencysystem.Repository.PackageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PackageService {

    private final PackageRepository repository;

    public List<Package> getPackages(){
        if (repository.findAll().isEmpty()){
            throw new ApiException("No packages found");
        }
        return repository.findAll();
    }

    public void addPackage(Package pack){
        repository.save(pack);
    }

    public void updatePackage(Integer id, Package p){
        Package p1 = repository.findPackageById(id);
        if (p1 == null){
            throw new ApiException("Invalid package id");
        }
        p1.setContent(p.getContent());
        p1.setDuration(p.getDuration());
        p1.setTitle(p.getTitle());
        p1.setPrice(p.getPrice());
        p1.setDestination(p.getDestination());
        p1.setType(p.getType());
        p1.setGuide(p.isGuide());
        p1.setTransfer(p.isTransfer());
        p1.setCapacityPerTrip(p.getCapacityPerTrip());
        repository.save(p1);
    }
    public void deletePackage(Integer id){
        Package p = repository.findPackageById(id);
        if (p == null){
            throw new ApiException("Invalid Package id");
        }
        repository.delete(p);
    }

    //1
    public List<Package> searchPackagesTitle(String s){
        List<Package> packages = repository.findPackageByTitleContaining(s);
        if (packages.isEmpty()){
            throw new ApiException("No Results");
        }
        return packages;
    }
    //2
    public List<Package> searchPackageByPrice(double min, double max){
        List<Package> packages = repository.findPackagesByPriceGreaterThanEqualAndPriceLessThanEqual(min,max);
        if (packages.isEmpty()){
            throw new ApiException("No results found");
        }
        return packages;
    }
    //3
    public List<Package> searchPackageByType(String word){
        List<Package> packages = repository.findPackagesByTypeEqualsIgnoreCase(word);
        if (packages.isEmpty()){
            throw new ApiException("No results found");
        }
        return packages;
    }

    //4
    public List<Package> searchPackageContentContaining(String word){
        List<Package> p = repository.findPackageByContentContaining(word);
        if (p.isEmpty()){
            throw new ApiException("No results found");
        }
        return p;
    }
    //5
    public List<Package> searchPackageCity(String word) {
        List<Package> p = repository.findPackagesByDestinationContains(word);
        if (p.isEmpty()) {
            throw new ApiException("No results found");
        }
        return p;
    }
    //6
    public List<Package> searchPackagesByDays(Integer number){
        List<Package> p = repository.findPackagesByDurationEquals(number);
        if (p.isEmpty()) {
            throw new ApiException("No results found");
        }
        return p;
    }
    //
    public List<Package> searchPackageWithGuide() {
        List<Package> p = repository.findPackagesByGuideIsTrue();
        if (p.isEmpty()) {
            throw new ApiException("No results found");
        }
        return p;
    }

    public List<Package> searchPackageWithTransfers() {
        List<Package> p = repository.findPackagesByTransferIsTrue();
        if (p.isEmpty()) {
            throw new ApiException("No results found");
        }
        return p;
    }

    public List<Package> searchPackageDateRange(LocalDate start, LocalDate end){
        List<Package> packages = repository.findPackagesByDates(start,end);
        if (packages.isEmpty()){
            throw new ApiException("No results found");
        }
        return packages;
    }

    public List<Package> searchPackageCapacity(Integer capacity){
        List<Package> packages = repository.findPackagesByCapacityPerTripLessThanEqual(capacity);
        if (packages.isEmpty()){
            throw new ApiException("No results found");
        }
        return packages;
    }
}
