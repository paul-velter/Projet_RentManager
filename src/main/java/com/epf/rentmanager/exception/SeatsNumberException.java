package com.epf.rentmanager.exception;

public class SeatsNumberException extends Exception{
    public SeatsNumberException(){

    }

    @Override
    public String getMessage() {
        return "Le nombre de sièges doit être compris entre 2 et 9";
    }
}
