package com.epf.rentmanager;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import org.junit.Test;
import org.junit.Before;
import com.epf.rentmanager.service.ClientService;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class AppTest {
    private ClientDao clientDaoMock;
    private ClientService clientService;

    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Before
    public void setUp() {
        clientDaoMock = mock(ClientDao.class);
        clientService = new ClientService(clientDaoMock);
    }

    @Test
    public void testCreateClient() throws DaoException, ServiceException {
        // Création d'un client à ajouter
        Client clientToAdd = new Client("Doe", "John", "johndoe@test.com", LocalDate.of(1980, 1, 1));

        // Configuration du comportement simulé de la méthode create() de ClientDao
        when(clientDaoMock.create(clientToAdd)).thenReturn(1L);

        // Appel de la méthode create() de ClientService
        long id = clientService.create(clientToAdd);

        // Vérification que la méthode create() de ClientDao a bien été appelée avec les bons paramètres
        verify(clientDaoMock).create(clientToAdd);

        // Vérification que l'id retourné par la méthode create() de ClientService correspond à celui retourné par la méthode create() de ClientDao
        assertEquals(1L, id);
    }
}

