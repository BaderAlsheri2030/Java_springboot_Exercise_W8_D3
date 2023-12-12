package com.example.travelagencysystem.Repository;

import com.example.travelagencysystem.Model.BookedPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookedPackageRepository extends JpaRepository<BookedPackage,Integer> {


    BookedPackage findBookedPackageById(Integer id);

    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM BookedPackage d WHERE d.endTravelDate < current_date ")
    boolean isCompleted(LocalDate endTravelDate);

    @Query("SELECT b from BookedPackage b where b.completed and b.user_id =?1")
    List<BookedPackage> findCompletedTrips(Integer id);

    @Query("SELECT b from BookedPackage b where b.user_id  =?1")
    List<BookedPackage> findBookedPackagesByUser_id(Integer id);




}