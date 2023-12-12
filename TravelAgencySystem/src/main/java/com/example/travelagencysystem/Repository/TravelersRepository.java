package com.example.travelagencysystem.Repository;

import com.example.travelagencysystem.Model.Travelers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TravelersRepository extends JpaRepository<Travelers,Integer> {
    List<Travelers> findTravelersByUserId(Integer id);
    Travelers findTravelersById(Integer id);
}
