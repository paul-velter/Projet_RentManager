package com.epf.rentmanager.service;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;

import java.util.List;

public class ReservationService {


    private ReservationDao reservationDao;
    public static ReservationService instance;

    private ReservationService() {
        this.reservationDao = ReservationDao.getInstance();
    }

    public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }

        return instance;
    }


    public long create(Client client) throws ServiceException {
        try {
            return ClientDao.getInstance().create(client);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public long delete(Client client) throws ServiceException {
        try {
            return ClientDao.getInstance().delete(client);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public List<Reservation> findResaByClientId(long clientId) throws ServiceException {
        try {
            return ReservationDao.getInstance().findResaByClientId(clientId);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public List<Reservation> findResaByVehicleId(long vehicleId) throws ServiceException {
        try {
            return ReservationDao.getInstance().findResaByVehicleId(vehicleId);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public List<Reservation> findAll() throws ServiceException {
        try {
            return ReservationDao.getInstance().findAll();
        } catch (DaoException e) {
            throw new ServiceException();
        }

    }

    public int count() throws ServiceException {
        try {
            return ReservationDao.getInstance().count();
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }
}
