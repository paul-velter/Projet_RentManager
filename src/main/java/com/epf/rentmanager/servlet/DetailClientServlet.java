package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users/details")//TODO : Afficher le nb de voiture Afficher le détail voiture (a quoi ça corresspond)
public class DetailClientServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            ClientService clientService = ClientService.getInstance();
            request.setAttribute("client", clientService.findById(id));

            ReservationService reservationService = ReservationService.getInstance();
            int nbClients = reservationService.countResaByClientId(id);
            request.setAttribute("nbClients", nbClients);
            request.setAttribute("listereservations", reservationService.findResaByClientId(id));

        } catch (ServiceException e) {
            throw new ServletException();
        }finally {
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/details.jsp").forward(request, response);
        }
    }
}
