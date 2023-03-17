package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;

public class VehicleDao {

    private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES(?, ?, ?);";
    private static final String EDIT_VEHICLE_QUERY = "UPDATE Vehicle SET constructeur=?, modele=?, nb_places=? WHERE id=?;";
    private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
    private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, modele, nb_places FROM Vehicle WHERE id=?;";
    private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, modele, nb_places FROM Vehicle;";
    private static VehicleDao instance = null;

    private VehicleDao() {
    }

    public static VehicleDao getInstance() {
        if (instance == null) {
            instance = new VehicleDao();
        }
        return instance;
    }

    public long create(Vehicle vehicle) throws DaoException {

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement ps= connection.prepareStatement(CREATE_VEHICLE_QUERY);){

            ps.setString(1, vehicle.getConstructor());
            ps.setString(2, vehicle.getModele());
            ps.setInt(3, vehicle.getNb_places());
            ps.executeUpdate();

            return  vehicle.getId();

        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    public long edit(Vehicle vehicle) throws DaoException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(EDIT_VEHICLE_QUERY);) {

            ps.setString(1, vehicle.getConstructor());
            ps.setString(2, vehicle.getModele());
            ps.setInt(3, vehicle.getNb_places());
            ps.setLong(4, vehicle.getId());
            ps.executeUpdate();

            return vehicle.getId();

        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    public long delete(Vehicle vehicle) throws DaoException {

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_VEHICLE_QUERY);){

            ps.setLong(1, vehicle.getId());
            ps.executeUpdate();

            return 0;

        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    public Vehicle findById(long id) throws DaoException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_VEHICLE_QUERY);){

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            rs.next();
            String construteur = rs.getString("constructeur");
            String modele = rs.getString("modele");
            int nb_places = rs.getInt("nb_places");

            return new Vehicle(id, construteur, modele, nb_places);

        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    public List<Vehicle> findAll() throws DaoException {
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(FIND_VEHICLES_QUERY);){

            while (rs.next()) {
                int id = rs.getInt("id");
                String construteur = rs.getString("constructeur");
                String modele = rs.getString("modele");
                int nb_places = rs.getInt("nb_places");

                vehicles.add(new Vehicle(id, construteur, modele, nb_places));
            }

            return vehicles;

        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    public int count() throws DaoException {
        int count = 0;
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(FIND_VEHICLES_QUERY);) {

            while (rs.next()) {
                count += 1;
            }

            return count;

        } catch (SQLException e) {
            throw new DaoException();
        }
    }
}
