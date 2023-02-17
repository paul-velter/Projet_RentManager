package com.epf.rentmanager.model;

import java.time.LocalDate;

public class Reservation {

    public long id;
    public Client client_id;
    public Vehicle vehicle_id;

    public LocalDate start;

    public LocalDate end;

    public Reservation() {

    }

    public Reservation(long id, Client client_id, Vehicle vehicle_id, LocalDate start, LocalDate end) {
        this.id = id;
        this.client_id = client_id;
        this.vehicle_id = vehicle_id;
        this.start = start;
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
