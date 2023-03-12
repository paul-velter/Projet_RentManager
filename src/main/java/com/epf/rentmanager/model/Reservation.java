package com.epf.rentmanager.model;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

import java.time.LocalDate;

public class Reservation {

    public long id;
    public long client_id;
    public long vehicle_id;

    public LocalDate start;

    public LocalDate end;

    public Reservation() {

    }

    public Reservation(long id, long client_id, long vehicle_id, LocalDate start, LocalDate end) {
        this.id = id;
        this.client_id = client_id;
        this.vehicle_id = vehicle_id;
        this.start = start;
        this.end = end;
    }

    public Reservation(long client_id, long vehicle_id, LocalDate start, LocalDate end) {
        this.client_id = client_id;
        this.vehicle_id = vehicle_id;
        this.start = start;
        this.end = end;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClient_id() {
        return client_id;
    }

    public void setClient_id(long client_id) {
        this.client_id = client_id;
    }

    public long getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(long vehicle_id) {
        this.vehicle_id = vehicle_id;
    }
    public String getFirtLastNameClient(long client_id) throws ServiceException {
        ClientService clientService = ClientService.getInstance();
        Client client = clientService.findById(client_id);
        return client.getFirst_name() + " " + client.getLast_name();
    }
    public String getConstructroModeleVehicle(long vehicle_id) throws ServiceException {
        VehicleService vehicleService = VehicleService.getInstance();
        Vehicle vehicle = vehicleService.findById(vehicle_id);
        return vehicle.getConstructor() + " " + vehicle.getModele();
    }
    public LocalDate getStart() {
        return start;
    }


    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", client_id=" + client_id +
                ", vehicle_id=" + vehicle_id +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
