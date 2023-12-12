package com.example.travelagencysystem.Repository;

import com.example.travelagencysystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository

public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserById(Integer id);
    @Query("select case when count(d) > 0 then true else false end from User d where d.registration_date = CURRENT_DATE ")
    Boolean date(LocalDate date);

}
