package com.epf.rentmanager.model;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

import java.time.LocalDate;

public class Reservation {

    private long id;
    private Client client;
    private Vehicle vehicle;
    private LocalDate start;
    private LocalDate end;

    private ClientDao clientDao;
    public Reservation() {

    }

    public Reservation(long id, Client client, Vehicle vehicle, LocalDate start, LocalDate end) {
        this.id = id;
        this.client = client;
        this.vehicle = vehicle;
        this.start = start;
        this.end = end;
    }

    public Reservation(Client client, Vehicle vehicle, LocalDate start, LocalDate end) {
        this.client = client;
        this.vehicle = vehicle;
        this.start = start;
        this.end = end;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public long getClientId(){
        return client.getId();
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Vehicle getVehicle() {return vehicle;}

    public long getVehicleId(){
        return vehicle.getId();
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    public String getFirtLastNameClient(Client client) throws ServiceException {;
        return client.getFirst_name() + " " + client.getLast_name();
    }
    public String getConstructroModeleVehicle(Vehicle vehicle) throws ServiceException {
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
                ", client_id=" + client +
                ", vehicle_id=" + vehicle +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
