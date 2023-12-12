package com.example.travelagencysystem.Repository;

import com.example.travelagencysystem.Model.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository

public interface PackageRepository extends JpaRepository<Package,Integer> {

    Package findPackageById(Integer id);

    List<Package> findPackageByTitleContaining(String s);

    List<Package> findPackageByContentContaining(String s);

    List<Package> findPackagesByTypeEqualsIgnoreCase(String s);

    List<Package> findPackagesByDestinationContains(String s);

    List<Package> findPackagesByPriceGreaterThanEqualAndPriceLessThanEqual(double min, double max);

    List<Package> findPackagesByDurationEquals(Integer days);

    List<Package> findPackagesByGuideIsTrue();

    @Query("select d from Package d where d.startTravelDate >= ?1 and d.endTravelDate <= ?2")
    List<Package> findPackagesByDates(LocalDate start, LocalDate end);

    List<Package> findPackagesByCapacityPerTripLessThanEqual(Integer capacity);

    //    @Query("select p from Package p where p.transfer = true")
    List<Package> findPackagesByTransferIsTrue();
}