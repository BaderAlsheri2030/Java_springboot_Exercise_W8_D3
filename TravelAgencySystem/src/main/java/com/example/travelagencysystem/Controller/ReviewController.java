package com.example.travelagencysystem.Controller;

import com.example.travelagencysystem.Model.Review;
import com.example.travelagencysystem.Service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService service;
    Logger logger = LoggerFactory.getLogger(ReviewController.class);



    @GetMapping("/get")
    public ResponseEntity getReviews() {
        logger.info("reviews displayed");
        return ResponseEntity.status(HttpStatus.OK).body(service.getReviews());
    }

    @PostMapping("/add/{user_id}/{pack_id}/{bookedId}")
    public ResponseEntity addReview(@PathVariable Integer user_id, @PathVariable Integer pack_id, @PathVariable Integer bookedId, @Valid @RequestBody Review review) {
        logger.info("add review");
        service.addReview(user_id, pack_id, bookedId, review);
        return ResponseEntity.status(HttpStatus.OK).body("Review Added");
    }

    @PutMapping("/update/{user_id}")
    public ResponseEntity updateReview(@PathVariable Integer user_id, @Valid @RequestBody Review review) {
        logger.info("updated review");
        service.updateReview(user_id, review);
        return ResponseEntity.status(HttpStatus.OK).body("Review updated");
    }

    @DeleteMapping("/delete{user_id}")
    public ResponseEntity deleteReview(@PathVariable Integer user_id) {
        logger.info("delete review");
        service.deleteReview(user_id);
        return ResponseEntity.status(HttpStatus.OK).body("Review Deleted");
    }

    @GetMapping("/rating/{number}")
    public ResponseEntity searchReviewsByRating(@PathVariable Integer number) {
        logger.info("search reviews");
        return ResponseEntity.status(HttpStatus.OK).body(service.reviewsByRating(number));
    }

    @GetMapping("/contain/{word}")
    public ResponseEntity searchReviewsContaining(@PathVariable String word) {
        logger.info("search reviews rating");
        return ResponseEntity.status(HttpStatus.OK).body(service.reviewsContaining(word));
    }

}