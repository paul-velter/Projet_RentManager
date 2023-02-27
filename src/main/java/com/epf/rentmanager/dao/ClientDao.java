package com.epf.rentmanager.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.persistence.ConnectionManager;

public class ClientDao {

    private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
    private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
    private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
    private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
    private static ClientDao instance = null;

    private ClientDao() {
    }

    public static ClientDao getInstance() {
        if (instance == null) {
            instance = new ClientDao();
        }
        return instance;
    }

    public long create(Client client) throws DaoException {

        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(CREATE_CLIENT_QUERY);
            ps.setString(1, client.getFirst_name());
            ps.setString(2, client.getLast_name());
            ps.setString(3, client.getEmail());
            ps.setObject(4, client.getBirth_date());
            ps.executeUpdate();
            ps.close();
            connection.close();

        } catch (SQLException e) {
            throw new DaoException();
        }
        return client.getId();
    }

    public long delete(Client client) throws DaoException { //TODO

        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE_CLIENT_QUERY);
            ps.setLong(1, client.getId());
            ResultSet rs = ps.executeQuery();

            rs.next();

            ps.close();
            rs.close();
            connection.close();

            return 0;

        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    public Client findById(long id) throws DaoException {

        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(FIND_CLIENT_QUERY);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            rs.next();
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String email = rs.getString("email");
            LocalDate date = rs.getDate("naissance").toLocalDate();

            ps.close();
            rs.close();
            connection.close();

            return new Client(id, nom, prenom, email, date);

        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    public List<Client> findAll() throws DaoException {
        List<Client> clients = new ArrayList<Client>();
        try {
            Connection connection = ConnectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(FIND_CLIENTS_QUERY);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String email = rs.getString("email");
                LocalDate date = rs.getDate("naissance").toLocalDate();

                clients.add(new Client(id, nom, prenom, email, date));
            }

            rs.close();
            connection.close();

        } catch (SQLException e) {
            throw new DaoException();
        }
        return clients;
    }

    public int count() throws DaoException {
        int count = 0;
        try {
            Connection connection = ConnectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(FIND_CLIENTS_QUERY);

            while (rs.next()) {
                count += 1;
            }

            rs.close();
            connection.close();

            return count;

        } catch (SQLException e) {
            throw new DaoException();
        }
    }

}


