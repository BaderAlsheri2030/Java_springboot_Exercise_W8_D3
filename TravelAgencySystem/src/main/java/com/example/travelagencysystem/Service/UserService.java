package com.example.travelagencysystem.Service;

import com.example.travelagencysystem.Api.ApiException;
import com.example.travelagencysystem.Model.User;
import com.example.travelagencysystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<User> getUsers(){
        if (repository.findAll().isEmpty()){
            throw new ApiException("There is no users");
        }
       return repository.findAll();
    }

    public void addUser(User user){
        repository.save(user);
    }

    public void updateUser(Integer id, User user){
        User user1 = repository.findUserById(id);
        if(user1 == null){
            throw new ApiException("Invalid id or user doesn't exist");
        }
        user1.setCard(user.getCard());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setPhoneNumber(user.getPhoneNumber());
        repository.save(user1);
    }

    public void deleteUser(Integer id){
        User user = repository.findUserById(id);
        if (user == null){
            throw new ApiException("Invalid id or user doesn't exist");
        }
        repository.delete(user);
    }

    //10% new users discount.a discount for a day when the user create an account
    public void addDiscountToUser(Integer userId){

        User user = repository.findUserById(userId);
        if (user == null){
            throw new ApiException("Invalid id or user doesn't exist");
        }
        if (user.isDiscount()){
            throw new ApiException("discount is applicable");
        }
        if (!repository.date(user.getRegistration_date())){
                throw new ApiException("Offer is not valid for this account");
        }

        user.setDiscount(true);
        repository.save(user);
    }





}
