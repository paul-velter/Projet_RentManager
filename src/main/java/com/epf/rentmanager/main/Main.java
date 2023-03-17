package com.epf.rentmanager.main;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Client client = new Client(8,"client8","client8","nom.prérom@gmail.com",LocalDate.now());

        try {
            System.out.println(ClientService.getInstance().findAll());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(ClientService.getInstance().findById(1));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

       Vehicle vehicle = new Vehicle(7,"lenouveua", "model pas ouf", 3);
        try {
            System.out.println(VehicleService.getInstance().findAll());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(VehicleService.getInstance().findById(1));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

       Reservation reservation = new Reservation(8,5,1,LocalDate.now(),LocalDate.now());
        try {
            System.out.println(ReservationService.getInstance().findAll());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(ReservationService.getInstance().findById(1));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}

