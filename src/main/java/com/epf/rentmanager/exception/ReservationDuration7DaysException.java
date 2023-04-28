package com.epf.rentmanager.exception;

public class ReservationDuration7DaysException extends Exception{
    public ReservationDuration7DaysException(){

    }

    @Override
    public String getMessage() {
        return "Le véhicule ne peut pas être loué plus de 7 jours";
    }
}
