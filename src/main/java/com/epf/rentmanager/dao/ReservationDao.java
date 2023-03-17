package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Reservation;
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
	private static final String EDIT_RESERVATION_QUERY = "UPDATE Reservation SET client_id=?, vehicle_id=?, debut=?, fin=? WHERE id=?;";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATION_QUERY = "SELECT client_id, vehicle_id, debut, fin FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
		
	public long create(Reservation reservation) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement ps = connection.prepareStatement(CREATE_RESERVATION_QUERY);) {

			ps.setLong(1, reservation.getClient_id());
			ps.setLong(2, reservation.getVehicle_id());
			ps.setObject(3, reservation.getStart());
			ps.setObject(4,reservation.getEnd());
			ps.executeUpdate();

			return reservation.getId();

		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public long edit(Reservation reservation) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement ps = connection.prepareStatement(EDIT_RESERVATION_QUERY);) {

			ps.setLong(1, reservation.getClient_id());
			ps.setLong(2, reservation.getVehicle_id());
			ps.setObject(3, reservation.getStart());
			ps.setObject(4, reservation.getEnd());
			ps.setLong(5, reservation.getId());
			ps.executeUpdate();

			return reservation.getId();

		} catch (SQLException e) {
			throw new DaoException();
		}
	}
	
	public long delete(Reservation reservation) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement ps = connection.prepareStatement(DELETE_RESERVATION_QUERY);){

			ps.setLong(1, reservation.getId());
			ps.executeUpdate();

			return 0;

		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public Reservation findById(long id) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement ps = connection.prepareStatement(FIND_RESERVATION_QUERY);){

			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();

			rs.next();
			long client_id = rs.getLong("client_id");
			long vehicle_id = rs.getLong("vehicle_id");
			LocalDate start = rs.getDate("debut").toLocalDate();
			LocalDate end = rs.getDate("fin").toLocalDate();

			return new Reservation(id, client_id, vehicle_id, start, end);

		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public List<Reservation> findResaByClientId(long clientId) throws DaoException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement ps = connection.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);){

			ps.setLong(1, clientId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				long id = rs.getLong("id");
				long vehicle_id = rs.getLong("vehicle_id");
				LocalDate start = rs.getDate("debut").toLocalDate();
				LocalDate end = rs.getDate("fin").toLocalDate();

				reservations.add(new Reservation(id, clientId, vehicle_id, start, end));
			}

			return reservations;

		} catch (SQLException e) {
			throw new DaoException();
		}
	}
	
	public List<Reservation> findResaByVehicleId(long vehicleId) throws DaoException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement ps = connection.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);){

			ps.setLong(1, vehicleId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				long id = rs.getInt("id");
				long client_id = rs.getLong("client_id");
				long vehicle_id = rs.getLong("vehicle_id");
				LocalDate start = rs.getDate("debut").toLocalDate();
				LocalDate end = rs.getDate("fin").toLocalDate();

				reservations.add(new Reservation(id, client_id, vehicle_id, start, end));
			}

			return reservations;

		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public List<Reservation> findAll() throws DaoException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		try (Connection connection = ConnectionManager.getConnection();
			 Statement statement = connection.createStatement();
			 ResultSet rs = statement.executeQuery(FIND_RESERVATIONS_QUERY);){

			while (rs.next()) {
				long id = rs.getInt("id");
				long client_id = rs.getLong("client_id");
				long vehicle_id = rs.getLong("vehicle_id");
				LocalDate start = rs.getDate("debut").toLocalDate();
				LocalDate end = rs.getDate("fin").toLocalDate();

				reservations.add(new Reservation(id, client_id, vehicle_id, start, end));
			}

			return reservations;

		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public int count() throws DaoException {
		int count = 0;
		try (Connection connection = ConnectionManager.getConnection();
			 Statement statement = connection.createStatement();
			 ResultSet rs = statement.executeQuery(FIND_RESERVATIONS_QUERY);){

			while (rs.next()) {
				count += 1;
			}

			return count;

		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public int countResaByClientId(long clientId) throws DaoException{
		int count = 0;
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement ps = connection.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);){

			ps.setLong(1, clientId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				count += 1;
			}

			return count;

		} catch (SQLException e) {
			throw new DaoException();
		}
	}
}
