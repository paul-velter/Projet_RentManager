package com.epf.rentmanager.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.NameLenghtException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;
@Repository
public class ClientDao {

    private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
    private static final String EDIT_CLIENT_QUERY = "UPDATE Client SET nom=?, prenom=?, email=?, naissance=? WHERE id=?;";
    private static final String DELETE_CLIENT_QUERY = "DELETE FROM Reservation WHERE client_id IN (SELECT id FROM Client WHERE id = ?); DELETE FROM Client WHERE id = ?;";
    private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
    private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";

    private static ClientDao instance = null;

    private ClientDao() {

    }

    public long create(Client client) throws DaoException {

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(CREATE_CLIENT_QUERY);) {

            ps.setString(1, client.getFirst_name());
            ps.setString(2, client.getLast_name());

            ps.setString(3, client.getEmail());
            ps.setObject(4, client.getBirth_date());
            ps.executeUpdate();

            return client.getId();

        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    public long edit(Client client) throws DaoException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(EDIT_CLIENT_QUERY);) {

            ps.setString(1, client.getFirst_name());
            ps.setString(2, client.getLast_name());
            ps.setString(3, client.getEmail());
            ps.setObject(4, client.getBirth_date());
            ps.setLong(5, client.getId());
            ps.executeUpdate();

            return client.getId();

        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    public long delete(Client client) throws DaoException {

        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE_CLIENT_QUERY);)
        {
            ps.setLong(1, client.getId());
            ps.setLong(2, client.getId());
            ps.executeUpdate();

            return 0;

        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    public Client findById(long id) throws DaoException {

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_CLIENT_QUERY);){

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String email = rs.getString("email");
            LocalDate date = rs.getDate("naissance").toLocalDate();

            return new Client(id, nom, prenom, email, date);

        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    public List<Client> findAll() throws DaoException {
        List<Client> clients = new ArrayList<Client>();

        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(FIND_CLIENTS_QUERY);){

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String email = rs.getString("email");
                LocalDate date = rs.getDate("naissance").toLocalDate();

                clients.add(new Client(id, nom, prenom, email, date));
            }

            return clients;

        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    public int count() throws DaoException {
        int count = 0;
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(FIND_CLIENTS_QUERY);) {

            while (rs.next()) {
                count += 1;
            }

            return count;

        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    public List<String> findAllEmails () throws DaoException{
        List<String> emails = new ArrayList<String>();

        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(FIND_CLIENTS_QUERY);){

            while (rs.next()) {
                String email = rs.getString("email");
                emails.add(email);
            }

            return emails;

        } catch (SQLException e) {
            throw new DaoException();
        }
    }
}


