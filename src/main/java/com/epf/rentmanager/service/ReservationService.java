package com.epf.rentmanager.service;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;

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

    public long create(Reservation reservation) throws ServiceException {
        try {
            return ReservationDao.getInstance().create(reservation);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }
    public long edit(Reservation reservation) throws ServiceException {
        try {
            return ReservationDao.getInstance().edit(reservation);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public long delete(Reservation reservation) throws ServiceException {
        try {
            return ReservationDao.getInstance().delete(reservation);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public Reservation findById(long id) throws ServiceException {
        try {
            return ReservationDao.getInstance().findById(id);
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

    public int countResaByClientId(long clientId) throws ServiceException {
        try {
            return ReservationDao.getInstance().countResaByClientId(clientId);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

}
