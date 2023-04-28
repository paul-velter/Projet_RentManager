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

        ApplicationContext context= new AnnotationConfigApplicationContext(AppConfiguration.class);
        ClientService clientService = context.getBean(ClientService.class);
        VehicleService vehicleService = context.getBean(VehicleService.class);
        ReservationService reservationService = context.getBean(ReservationService.class);

        try {
            System.out.println(reservationService.findAll());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        try {
            Vehicle vehicle = vehicleService.findById(1);
            Client client = clientService.findById(1);
            System.out.println(reservationService.reservationDatesByClientIdAndVehicleId(client, vehicle));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }

}

