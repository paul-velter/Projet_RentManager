package com.epf.rentmanager.service;

import java.time.LocalDate;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.VehicleDao;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

	private VehicleDao vehicleDao;
	public static VehicleService instance;

	private VehicleService(VehicleDao vehicleDao){
		this.vehicleDao = vehicleDao;
	}

	public long create(Vehicle vehicle) throws ServiceException {
		try {
			return vehicleDao.create(vehicle);
		} catch (DaoException e) {
			throw new ServiceException();
		}
	}

	public long edit(Vehicle vehicle) throws ServiceException {
		try {
			return vehicleDao.edit(vehicle);
		} catch (DaoException e) {
			throw new ServiceException();
		}
	}
	public long delete(Vehicle vehicle) throws ServiceException {
		try {
			return vehicleDao.delete(vehicle);
		} catch (DaoException e) {
			throw new ServiceException();
		}
	}

	public Vehicle findById(long id) throws ServiceException {
		try {
			return vehicleDao.findById(id);
		} catch (DaoException e) {
			throw new ServiceException();
		}
	}

	public List<Vehicle> findAll() throws ServiceException {
		try {
			return vehicleDao.findAll();
		} catch (DaoException e) {
			throw new ServiceException();
		}

	}
	public int count() throws ServiceException {
		try {
			return vehicleDao.count();
		} catch (DaoException e) {
			throw new ServiceException();
		}
	}

	public List<LocalDate> reservationDates(Vehicle vehicle) throws ServiceException {
		try {
			return vehicleDao.reservationDates(vehicle);
		} catch (DaoException e) {
			throw new ServiceException();
		}
	}

}
