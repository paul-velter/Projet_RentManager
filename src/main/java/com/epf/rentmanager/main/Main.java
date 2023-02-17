package com.epf.rentmanager.main;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Client client = new Client(02, "nomclient2", "prenomclient2", "email2", LocalDate.of(1990,04,20));
        try {
            System.out.println(ClientService.getInstance().create(client));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(ClientService.getInstance().findAll());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

//        try {
//            System.out.println(VehicleService.getInstance().findAll());
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }
    }
}

