package com.example.travelagencysystem.Repository;

import com.example.travelagencysystem.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ReviewRepository extends JpaRepository<Review,Integer> {

    @Query("select u from Review u where u.user_id = ?1")
    Review reviewFindByUserId(Integer id);

    List<Review> findReviewByRatingEquals(Integer rate);
    List<Review> findReviewByReviewContaining(String word);

}
