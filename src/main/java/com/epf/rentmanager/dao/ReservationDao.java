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
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationDao {
	ClientDao clientDao;
	VehicleDao vehicleDao;
	private ReservationDao(ClientDao clientDao, VehicleDao vehicleDao) {
		this.clientDao = clientDao;
		this.vehicleDao = vehicleDao;
	}

	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String EDIT_RESERVATION_QUERY = "UPDATE Reservation SET client_id=?, vehicle_id=?, debut=?, fin=? WHERE id=?;";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATION_QUERY = "SELECT client_id, vehicle_id, debut, fin FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
	private static final String FIND_RESERVATION_DATES_BY_CLIENT_AND_VEHICLE_QUERY = "SELECT debut, fin FROM Reservation WHERE client_id=? AND vehicle_id=?;";
		
	public long create(Reservation reservation) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement ps = connection.prepareStatement(CREATE_RESERVATION_QUERY);) {

			ps.setLong(1, reservation.getClientId());
			ps.setLong(2, reservation.getVehicleId());
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

			ps.setLong(1, reservation.getClientId());
			ps.setLong(2, reservation.getVehicleId());
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
			long clientId = rs.getLong("client_id");
			Client client = clientDao.findById(clientId);
			long vehicleId = rs.getLong("vehicle_id");
			Vehicle vehicle = vehicleDao.findById(vehicleId);
			LocalDate start = rs.getDate("debut").toLocalDate();
			LocalDate end = rs.getDate("fin").toLocalDate();

			return new Reservation(id, client, vehicle, start, end);

		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public List<Reservation> findResaByClientId(Client client) throws DaoException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement ps = connection.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);){

			long clientId = client.getId();
			ps.setLong(1, clientId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				long id = rs.getLong("id");
				long vehicleId = rs.getLong("vehicle_id");
				Vehicle vehicle = vehicleDao.findById(vehicleId);
				LocalDate start = rs.getDate("debut").toLocalDate();
				LocalDate end = rs.getDate("fin").toLocalDate();

				reservations.add(new Reservation(id, client, vehicle, start, end));
			}

			return reservations;

		} catch (SQLException e) {
			throw new DaoException();
		}
	}
	
	public List<Reservation> findResaByVehicleId(Vehicle vehicle) throws DaoException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement ps = connection.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);){

			long vehicleId = vehicle.getId();
			ps.setLong(1, vehicleId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				long id = rs.getInt("id");
				long clientId = rs.getLong("client_id");
				Client client = clientDao.findById(clientId);
				LocalDate start = rs.getDate("debut").toLocalDate();
				LocalDate end = rs.getDate("fin").toLocalDate();

				reservations.add(new Reservation(id, client, vehicle, start, end));
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
				long clientId = rs.getLong("client_id");
				Client client = clientDao.findById(clientId);
				long vehicleId = rs.getLong("vehicle_id");
				Vehicle vehicle = vehicleDao.findById(vehicleId);
				LocalDate start = rs.getDate("debut").toLocalDate();
				LocalDate end = rs.getDate("fin").toLocalDate();

				reservations.add(new Reservation(id, client, vehicle, start, end));
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

	public List<LocalDate> reservationDatesByClientIdAndVehicleId(Client client, Vehicle vehicle) throws DaoException{
		List<LocalDate> dates = new ArrayList<LocalDate>();
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement ps = connection.prepareStatement(FIND_RESERVATION_DATES_BY_CLIENT_AND_VEHICLE_QUERY);) {

			ps.setObject(1, client.getId());
			ps.setObject(2, vehicle.getId());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				LocalDate start = rs.getDate("debut").toLocalDate();
				LocalDate end = rs.getDate("fin").toLocalDate();
				for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
					dates.add(date);
				}
			}

			return dates;

		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public List<LocalDate> testReservationDates(List<LocalDate> reservation_dates, LocalDate start, LocalDate end) throws DaoException{
		List<LocalDate> reservation_dates_test = reservation_dates;
		for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
			reservation_dates_test.add(date);
		}
			return reservation_dates_test;
	}


	public int countConcecutiveReservationDays (List<LocalDate> reservation_dates) throws DaoException{
		int maxConsecutiveDays = 1;
		int currentConsecutiveDays = 1;
		for (int i = 0; i < reservation_dates.size() - 1; i++) {
			LocalDate currentDate = reservation_dates.get(i);
			LocalDate nextDate = reservation_dates.get(i + 1);

			if (nextDate.equals(currentDate.plusDays(1))) {
				currentConsecutiveDays++;
			} else {
				maxConsecutiveDays = Math.max(maxConsecutiveDays, currentConsecutiveDays);
				currentConsecutiveDays = 1;
			}
		}
		maxConsecutiveDays = Math.max(maxConsecutiveDays, currentConsecutiveDays);
		return maxConsecutiveDays;
	}
}
