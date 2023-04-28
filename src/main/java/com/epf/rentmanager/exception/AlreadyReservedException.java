package com.epf.rentmanager.exception;

import java.time.LocalDate;

public class AlreadyReservedException extends Exception{
    public AlreadyReservedException(){

    }

    @Override
    public String getMessage() {
        return "Voiture déjà réservée.";
    }
}
