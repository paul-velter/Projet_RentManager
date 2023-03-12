package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/rents/create")
public class AddReservationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ClientService clientService = ClientService.getInstance();
            request.setAttribute("clients", clientService.findAll());

            VehicleService vehicleService = VehicleService.getInstance();
            request.setAttribute("vehicles", vehicleService.findAll());

        } catch (ServiceException e) {
            throw new ServletException();
        } finally {
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/create.jsp").forward(request, response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            long clientId = Long.parseLong(request.getParameter("client"));
            long vehicleId = Long.parseLong(request.getParameter("car"));
            LocalDate start = LocalDate.parse(request.getParameter("begin"));
            LocalDate end = LocalDate.parse(request.getParameter("end"));


            ReservationService reservationService =ReservationService.getInstance();
            Reservation reservation = new Reservation(clientId,vehicleId,start,end);
            reservationService.create(reservation);
            request.setAttribute("reservations", reservationService.findAll());


        } catch (ServiceException e) {
            throw new ServletException();
        }finally {
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/list.jsp").forward(request, response);
        }
    }
}