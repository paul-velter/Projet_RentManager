package com.epf.rentmanager.main;

import com.epf.rentmanager.config.AppConfiguration;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context=new AnnotationConfigApplicationContext(AppConfiguration.class);
        ClientService clientService = context.getBean(ClientService.class);
        VehicleService vehicleService = context.getBean(VehicleService.class);
        ReservationService reservationService = context.getBean(ReservationService.class);

        Client client = new Client(8,"client8","client8","nom.prérom@gmail.com",LocalDate.now());

        try {
            System.out.println(clientService.findAll());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

//        try {
//            System.out.println(clientService.findById(1));
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }

       Vehicle vehicle = new Vehicle(7,"lenouveua", "model pas ouf", 3);
        try {
            System.out.println(vehicleService.findAll());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

//        try {
//            System.out.println(VehicleService.getInstance().findById(1));
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }

       Reservation reservation = new Reservation(8,client,vehicle,LocalDate.now(),LocalDate.now());
        try {
            System.out.println(reservationService.findAll());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

//        try {
//            System.out.println(ReservationService.getInstance().findById(1));
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }


    }

}

