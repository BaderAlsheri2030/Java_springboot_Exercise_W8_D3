package com.example.travelagencysystem.Service;

import com.example.travelagencysystem.Api.ApiException;
import com.example.travelagencysystem.Model.BookedPackage;
import com.example.travelagencysystem.Model.Package;
import com.example.travelagencysystem.Model.Travelers;
import com.example.travelagencysystem.Model.User;
import com.example.travelagencysystem.Repository.BookedPackageRepository;
import com.example.travelagencysystem.Repository.PackageRepository;
import com.example.travelagencysystem.Repository.TravelersRepository;
import com.example.travelagencysystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookedPackageService {
    private final BookedPackageRepository repository;
    private final UserRepository userRepository;
    private final PackageRepository packageRepository;


    public List<BookedPackage> getBookedPackages(){
        if (repository.findAll().isEmpty()){
            throw new ApiException("No booked packages found");
        }
        return repository.findAll();
    }

    public void addBookedPackage(Integer packId,Integer userId,BookedPackage bookedPackage){
        double percent = applyDiscount(userId);
        double discount;

        Package p = packageRepository.findPackageById(packId);
        User user = userRepository.findUserById(userId);

        if (user == null){
            throw new ApiException("Invalid user id");
        }
        if (p == null) {
            throw new ApiException("Invalid package id");
        }
        if (bookedPackage.getNumberOfTravelers() > p.getCapacityPerTrip()){
            throw new ApiException("Tickets are out of stock");
        }

        discount = percent*(p.getPrice()*bookedPackage.getNumberOfTravelers());
        bookedPackage.setPackage_id(p.getId());
        bookedPackage.setUser_id(user.getId());
        bookedPackage.setDays(p.getDuration());
        bookedPackage.setTotalPrice(p.getPrice()*bookedPackage.getNumberOfTravelers()-discount);
        bookedPackage.setStartTravelDate(p.getStartTravelDate());
        bookedPackage.setEndTravelDate(p.getEndTravelDate());
        bookedPackage.setTitle(p.getTitle());
        repository.save(bookedPackage);
        user.setDiscount(false);
        userRepository.save(user);
        p.setCapacityPerTrip(p.getCapacityPerTrip()-bookedPackage.getNumberOfTravelers());
        packageRepository.save(p);
    }

    public void updatePackage(Integer user_id,Integer pack_id, Integer bookedId,BookedPackage bookedPackage){
        Package p = packageRepository.findPackageById(pack_id);
        BookedPackage bookedPackage1 = repository.findBookedPackageById(bookedId);
        BookedPackage bookedPackage2 = repository.findBookedPackageById(bookedId);
        User user = userRepository.findUserById(user_id);
        User user1 = userRepository.findUserById(user_id);
        if (!bookedPackage2.getUser_id().equals(user1.getId())){
            throw new ApiException("User doesn't has a booked package with this ID");
        }
        if (user == null){
            throw new ApiException("Invalid user id");
        }
        if (p == null){
            throw new ApiException("Invalid Package id");
        }
        if (bookedPackage1 == null){
            throw new ApiException("Invalid booked package id");
        }
        if (bookedPackage.getNumberOfTravelers() > p.getCapacityPerTrip()){
            throw new ApiException("Tickets are out of stock");
        }
        bookedPackage1.setNumberOfTravelers(bookedPackage.getNumberOfTravelers());
        bookedPackage1.setTotalPrice(p.getPrice()*bookedPackage.getNumberOfTravelers());
        bookedPackage1.setStartTravelDate(bookedPackage.getStartTravelDate());
        bookedPackage1.setStartTravelDate(bookedPackage.getStartTravelDate());
        repository.save(bookedPackage1);
    }

    public void deletePackage(Integer user_id,Integer pack_id){
        BookedPackage bookedPackage = repository.findBookedPackageById(pack_id);
        BookedPackage bookedPackage1 = repository.findBookedPackageById(pack_id);
        User user = userRepository.findUserById(user_id);
        User user1 = userRepository.findUserById(user_id);

        if (!bookedPackage1.getUser_id().equals(user1.getId())){
            throw new ApiException("User doesn't has a booked package with this ID");
        }
        if (user == null){
            throw new ApiException("Invalid user id");
        }
        if (bookedPackage == null){
            throw new ApiException("Invalid booked package id");
        }
        repository.delete(bookedPackage);
    }

    public double applyDiscount(Integer user_id){
        double discount = 0;
        User user = userRepository.findUserById(user_id);

        if (user == null){
            throw new ApiException("Invalid user id");
        }
        if ( userTrips(user.getId()) > 0 && userTrips(user.getId()) <2){
            return 0.20;
        }
        if (user.isDiscount() && userTrips(user.getId()) == 0){
            return 0.10;
        }
        return discount;
    }

    public List<BookedPackage> completedTrips(Integer user_id){
        List<BookedPackage> packages = repository.findCompletedTrips(user_id);
        if(packages.isEmpty()){
            throw new ApiException("Invalid user id or no booked trips");
        }
        return packages;
    }
    public Integer userTrips(Integer user_id){
        List<BookedPackage> packages = repository.findBookedPackagesByUser_id(user_id);
        return packages.size();
    }









}
