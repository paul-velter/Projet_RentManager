package com.epf.rentmanager.exception;

public class ReservationDuration30DaysException extends Exception{
    public ReservationDuration30DaysException(){

    }
    @Override
    public String getMessage() {
        return "Le véhicule ne peut pas être loué 30 jours de suite";
    }
}
