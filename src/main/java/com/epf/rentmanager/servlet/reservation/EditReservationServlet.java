package com.epf.rentmanager.servlet.reservation;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/rents/edit")
public class EditReservationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Autowired
    ClientService clientService;
    @Autowired
    VehicleService vehicleService;
    @Autowired
    ReservationService reservationService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            request.setAttribute("reservation", reservationService.findById(id));

            request.setAttribute("vehicles", vehicleService.findAll());

            request.setAttribute("clients", clientService.findAll());

        } catch (ServiceException e) {
            throw new ServletException();
        } finally {
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/edit.jsp").forward(request, response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            long clientId = Long.parseLong(request.getParameter("client"));
            Client client = clientService.findById(clientId);
            long vehicleId = Long.parseLong(request.getParameter("car"));
            Vehicle vehicle = vehicleService.findById(vehicleId);
            LocalDate start = LocalDate.parse(request.getParameter("begin"));
            LocalDate end = LocalDate.parse(request.getParameter("end"));

            Reservation reservation = new Reservation(id,client,vehicle,start,end);
            reservationService.edit(reservation);
            request.setAttribute("reservations", reservationService.findAll());


        } catch (ServiceException e) {
            throw new ServletException();
        }finally {
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/list.jsp").forward(request, response);
        }
    }
}
