package com.epf.rentmanager.model;

import java.time.LocalDate;

public class Client {

    public long id;
    public String first_name;
    public String last_name;

    public String email;
    public LocalDate birth_date;


    public Client() {
    }

    public Client(long id, String first_name, String last_name, String email, LocalDate birth_date) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.birth_date = birth_date;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    @Override
    public String toString() {
        return "Client : \n" +
                "first name : " + first_name + "\n" +
                "last name : " + last_name + "\n" +
                "email : " + email + "\n" +
                "birth date : " + birth_date + "\n\n";
    }
}