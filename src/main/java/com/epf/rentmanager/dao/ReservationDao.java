package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;

public class ReservationDao {

	private static ReservationDao instance = null;
	private ReservationDao() {}
	public static ReservationDao getInstance() {
		if(instance == null) {
			instance = new ReservationDao();
		}
		return instance;
	}
	
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
		
	public long create(Reservation reservation) throws DaoException {
		return 0;
	}
	
	public long delete(Reservation reservation) throws DaoException {
		return 0;
	}

	
	public List<Reservation> findResaByClientId(Client clientId) throws DaoException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);
			ps.setLong(1, clientId.getId());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				long id = rs.getInt("id");
				long client_id = rs.getLong("client_id");
				long vehicle_id = rs.getLong("vehicle_id");
				LocalDate start = rs.getDate("debut").toLocalDate();
				LocalDate end = rs.getDate("fin").toLocalDate();

				reservations.add(new Reservation(id, client_id, vehicle_id, start, end));
			}

		} catch (SQLException e) {
			throw new DaoException();
		}
		return reservations;
	}
	
	public List<Reservation> findResaByVehicleId(Vehicle vehicleId) throws DaoException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);
			ps.setLong(1, vehicleId.getId());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				long id = rs.getInt("id");
				long client_id = rs.getLong("client_id");
				long vehicle_id = rs.getLong("vehicle_id");
				LocalDate start = rs.getDate("debut").toLocalDate();
				LocalDate end = rs.getDate("fin").toLocalDate();

				reservations.add(new Reservation(id, client_id, vehicle_id, start, end));
			}

		} catch (SQLException e) {
			throw new DaoException();
		}
		return reservations;
	}

	public List<Reservation> findAll() throws DaoException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		try {
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(FIND_RESERVATIONS_QUERY);

			while (rs.next()) {
				long id = rs.getInt("id");
				long client_id = rs.getLong("client_id");
				long vehicle_id = rs.getLong("vehicle_id");
				LocalDate start = rs.getDate("debut").toLocalDate();
				LocalDate end = rs.getDate("fin").toLocalDate();

				reservations.add(new Reservation(id, client_id, vehicle_id, start, end));
			}

			rs.close();
			connection.close();

		} catch (SQLException e) {
			throw new DaoException();
		}
		return reservations;
	}

	public int count() throws DaoException {
		int count = 0;
		try {
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(FIND_RESERVATIONS_QUERY);

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
