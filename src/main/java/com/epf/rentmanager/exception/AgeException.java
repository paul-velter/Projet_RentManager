package com.epf.rentmanager.exception;

public class AgeException extends Exception{

    public AgeException(){

    }

    @Override
    public String getMessage() {
        return "Le client doit avoir plus de 18 ans";
    }
}
