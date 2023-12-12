package com.example.travelagencysystem.Service;

import com.example.travelagencysystem.Api.ApiException;
import com.example.travelagencysystem.Model.BookedPackage;
import com.example.travelagencysystem.Model.Travelers;
import com.example.travelagencysystem.Model.User;
import com.example.travelagencysystem.Repository.BookedPackageRepository;
import com.example.travelagencysystem.Repository.PackageRepository;
import com.example.travelagencysystem.Repository.TravelersRepository;
import com.example.travelagencysystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TravelerService {
    private final BookedPackageRepository repository;
    private final UserRepository userRepository;
    private final TravelersRepository travelersRepository;

    public List<Travelers> getTravelers(){
        if (travelersRepository.findAll().isEmpty()){
            throw new ApiException("No results");
        }
        return travelersRepository.findAll();
    }
    public void addTravelerInformation(Integer bookedPack_id, Integer user_id, Travelers traveler){
        User user = userRepository.findUserById(user_id);
        User user1 = userRepository.findUserById(user_id);
        BookedPackage bookedPackage = repository.findBookedPackageById(bookedPack_id);
        BookedPackage bookedPackage2 = repository.findBookedPackageById(bookedPack_id);


        if (!bookedPackage2.getUser_id().equals(user1.getId())){
            throw new ApiException("User doesn't has a booked package with this ID");
        }
        if (user == null){
            throw new ApiException("Invalid user id");
        }
        if (bookedPackage == null){
            throw new ApiException("Invalid booked package id");
        }
        if (getTravelersByUserId(user_id).size() >= bookedPackage.getNumberOfTravelers()){
            throw new ApiException("You cannot add more travelers, please increase the number of travelers in the booked package");
        }

        traveler.setUserId(user.getId());
        traveler.setBookedPackageId(bookedPackage.getId());
        travelersRepository.save(traveler);
    }

    public void updateTraveler(Integer bookedPack_id,Integer traveler_id, Travelers traveler) {
        Travelers traveler1 = travelersRepository.findTravelersById(traveler_id);
        BookedPackage bookedPackage = repository.findBookedPackageById(bookedPack_id);
        BookedPackage bookedPackage2 = repository.findBookedPackageById(bookedPack_id);

        if (!traveler1.getBookedPackageId().equals(bookedPackage2.getPackage_id())){
            throw new ApiException("Traveler doesn't has a booked package with this ID");
        }
        if (traveler_id == null){
            throw new ApiException("Invalid traveler id");
        }
        if (bookedPackage == null){
            throw new ApiException("Invalid booked package id");
        }

        traveler1.setPassportInfo(traveler.getPassportInfo());
        travelersRepository.save(traveler1);
    }
    public void deleteTraveler(Integer traveler_id,Integer bookedPack_id){
        Travelers traveler = travelersRepository.findTravelersById(traveler_id);
        BookedPackage bookedPackage = repository.findBookedPackageById(bookedPack_id);
        if(!traveler.getBookedPackageId().equals(bookedPackage.getPackage_id())){
            throw new ApiException("Invalid, Traveler doesn't has a booked package with this ID");
        }
        travelersRepository.delete(traveler);
    }


    public List<Travelers> getTravelersByUserId(Integer user_id ){
        List<Travelers> travelers = travelersRepository.findTravelersByUserId(user_id);
        if (travelers.isEmpty()){
            throw new ApiException("No travelers exist");
        }
        return travelers;
    }
}
