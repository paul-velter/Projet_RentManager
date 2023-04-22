package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

	private ClientDao clientDao;
	public static ClientService instance;

	private ClientService(ClientDao clientDao){
		this.clientDao = clientDao;
	}

	public long create(Client client) throws ServiceException {
		try {
			return clientDao.create(client);
		} catch (DaoException e){
			throw new ServiceException();
		}
	}

	public long edit(Client client) throws ServiceException {
		try {
			return clientDao.edit(client);
		} catch (DaoException e){
			throw new ServiceException();
		}
	}

	public long delete(Client client) throws ServiceException {
		try {
			return clientDao.delete(client);
		} catch (DaoException e){
			throw new ServiceException();
		}
	}

	public Client findById(long id) throws ServiceException {
		try {
			return clientDao.findById(id);
		} catch (DaoException e){
			throw new ServiceException();
		}
	}

	public List<Client> findAll() throws ServiceException {
		try {
			return clientDao.findAll();
		} catch (DaoException e){
			throw new ServiceException();
		}
	}

	public int count() throws ServiceException{
		try {
			return clientDao.count();
		}catch (DaoException e){
			throw new ServiceException();
		}
	}

	public List<String> findAllEmails() throws ServiceException{
		try{
			return clientDao.findAllEmails();
		}catch (DaoException e) {
			throw new ServiceException();
		}
	}

}
