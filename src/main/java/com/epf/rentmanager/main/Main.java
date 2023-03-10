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

        Client client = new Client("prénomclient2","nomclient2","nom.prérom@gmail.com",LocalDate.now());
//        try {
//            client = ClientService.getInstance().findById(5);
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }
        /*
        try {
            System.out.println(ClientService.getInstance().create(client));
        } catch (ServiceException e) {
            e.printStackTrace();
        }*/
        try {
            System.out.println(ReservationService.getInstance().findAll());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
//        try {
//            System.out.println(ClientService.getInstance().delete(client));
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }

/*       try {
            System.out.println(ReservationService.getInstance().findResaByClientId(1));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
//*/
//        try {
//            System.out.println(ClientService.getInstance().findAll());
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }

//        try {
//            System.out.println(ClientService.getInstance().findById(1));
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }

//        Vehicle vehicle = new Vehicle(01, "constructeur1", "modèle1", 4);
//        try {
//            System.out.println(VehicleService.getInstance().create(vehicle));
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            System.out.println(VehicleService.getInstance().findAll());
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            System.out.println(VehicleService.getInstance().findById(2));
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }
//    try {
//            System.out.println(ClientService.getInstance().count());
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }
    }
}

