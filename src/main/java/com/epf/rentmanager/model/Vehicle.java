package com.epf.rentmanager.model;

public class Vehicle {

    public long id;

    public String constructor;
    public String modele;
    public int nb_places;

    public Vehicle() {

    }

    public Vehicle(long id, String constructor, String modele, int nb_places) {
        this.id = id;
        this.constructor = constructor;
        this.modele = modele;
        this.nb_places = nb_places;
    }

    public Vehicle(String constructor, String modele, int nb_places) {
        this.constructor = constructor;
        this.modele = modele;
        this.nb_places = nb_places;
    }

    public long getId() {
        return id;
    }

    public String getConstructor() {
        return constructor;
    }

    public String getModele() {
        return modele;
    }

    public int getNb_places() {
        return nb_places;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setConstructor(String constructor) {
        this.constructor = constructor;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public void setNb_places(int nb_places) {
        this.nb_places = nb_places;
    }

    @Override
    public String toString() {
        return "Vehicle : \n" +
                "Id : " + id + "\n"+
                "Constructor : " + constructor + "\n" +
                "Modele : " + modele + "\n" +
                "Nb_places : " + nb_places + "\n\n";
    }
}
