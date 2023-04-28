package com.epf.rentmanager.exception;

public class NameLenghtException extends Exception{
    public NameLenghtException(){

    }
    @Override
    public String getMessage() {
        return "Le nom et le prénom doivent contenir au moins 3 lettres";
    }
}
