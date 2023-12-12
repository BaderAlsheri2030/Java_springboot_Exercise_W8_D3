package com.example.travelagencysystem.Api;

public class ApiException extends RuntimeException{
    public ApiException(String message){
        super(message);
    }
}
