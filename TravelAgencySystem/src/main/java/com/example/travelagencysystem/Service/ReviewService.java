package com.example.travelagencysystem.Service;

import com.example.travelagencysystem.Api.ApiException;
import com.example.travelagencysystem.Model.BookedPackage;
import com.example.travelagencysystem.Model.Package;
import com.example.travelagencysystem.Model.Review;
import com.example.travelagencysystem.Model.User;
import com.example.travelagencysystem.Repository.BookedPackageRepository;
import com.example.travelagencysystem.Repository.PackageRepository;
import com.example.travelagencysystem.Repository.ReviewRepository;
import com.example.travelagencysystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository repository;
    private final BookedPackageRepository bookedPackageRepository;
    private final UserRepository userRepository;
    private final PackageRepository packageRepository;

    public List<Review> getReviews(){
        if (repository.findAll().isEmpty()){
            throw new ApiException("No reviews found");
        }
        return repository.findAll();
    }


    public void addReview(Integer user_id,Integer pack_id,Integer bookedId,Review review){
        User user = userRepository.findUserById(user_id);
        User user1 = userRepository.findUserById(user_id);
        Package pack = packageRepository.findPackageById(pack_id);
        BookedPackage bookedPackage = bookedPackageRepository.findBookedPackageById(bookedId);
        BookedPackage bookedPackage1 = bookedPackageRepository.findBookedPackageById(bookedId);

        if (!bookedPackage1.getUser_id().equals(user1.getId())){
            throw new ApiException("User doesn't has a booked package with this ID");
        }
        if (user == null){
            throw new ApiException("Invalid user id");
        }
        if (pack == null){
            throw new ApiException("Invalid package Id");
        }
        if (bookedPackage == null){
            throw new ApiException("Invalid booked package id");
        }
        if (!checkIfComplete(user_id,bookedId)){
            throw new ApiException("Cannot review incomplete trips ");
        }

        repository.save(review);
    }

    public void updateReview(Integer id, Review review){
        Review review1 = repository.reviewFindByUserId(id);
        if(review1 == null){
            throw new ApiException("Invalid user id");
        }
        review1.setReview(review.getReview());
        repository.save(review1);
    }

    public void deleteReview(Integer id){
        Review review = repository.reviewFindByUserId(id);
        if(review == null){
            throw new ApiException("Invalid review id");
        }
        repository.delete(review);
    }

    public boolean checkIfComplete(Integer user_id, Integer bookedId){
        User user = userRepository.findUserById(user_id);
        User user1 = userRepository.findUserById(user_id);
        BookedPackage bookedPackage = bookedPackageRepository.findBookedPackageById(bookedId);
        BookedPackage bookedPackage1 = bookedPackageRepository.findBookedPackageById(bookedId);
        if (!bookedPackage1.getUser_id().equals(user1.getId())){
            throw new ApiException("Invalid user id or booked id");
        }
        if (user == null){
            throw new ApiException("Invalid user id");
        }
        if (bookedPackage == null){
            throw new ApiException("Invalid booked package Id");
        }
        //if end date is present change condition state of completed to true to add a review
        if (bookedPackageRepository.isCompleted(bookedPackage1.getEndTravelDate())){
            bookedPackage.setCompleted(true);
            bookedPackageRepository.save(bookedPackage);
            return true;
        }
        return false;
    }


    public List<Review> reviewsByRating(Integer rate){
        List<Review> reviews = repository.findReviewByRatingEquals(rate);
        if (reviews.isEmpty()){
            throw new ApiException("No results found");
        }
        return reviews;
    }

    public List<Review> reviewsContaining(String word){
        List<Review> reviews = repository.findReviewByReviewContaining(word);
        if (reviews.isEmpty()){
            throw new ApiException("No results found");
        }
        return reviews;
    }

}
