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
        return 0;
    }

    public long delete(Client client) throws DaoException {
        return 0;
    }

    public Client findById(long id) throws DaoException {
        return new Client();
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
        } catch (SQLException e) {
            throw new DaoException();
        }
        return clients;
    }

}
