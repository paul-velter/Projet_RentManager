package com.epf.rentmanager.exception;

public class EmailAlreadyExist extends Exception {
    public EmailAlreadyExist(){

    }

    @Override
    public String getMessage() {
        return "Ce mail existe déja";
    }
}
