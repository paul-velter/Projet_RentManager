package com.epf.rentmanager.servlet.reservation;

import com.epf.rentmanager.exception.AlreadyReservedException;
import com.epf.rentmanager.exception.ReservationDuration30DaysException;
import com.epf.rentmanager.exception.ReservationDuration7DaysException;
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
import java.time.temporal.ChronoUnit;

@WebServlet("/rents/create")
public class AddReservationServlet extends HttpServlet {
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
            request.setAttribute("clients", clientService.findAll());

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
            Client client = clientService.findById(clientId);
            long vehicleId = Long.parseLong(request.getParameter("car"));
            Vehicle vehicle = vehicleService.findById(vehicleId);
            LocalDate start = LocalDate.parse(request.getParameter("begin"));
            LocalDate end = LocalDate.parse(request.getParameter("end"));
            if (vehicleService.reservationDates(vehicle).contains(start) || vehicleService.reservationDates(vehicle).contains(start)){
                throw new AlreadyReservedException();
            }
            if (reservationService.countConcecutiveReservationDays(reservationService.testReservationDates(reservationService.reservationDatesByClientIdAndVehicleId(client, vehicle), start, end) ) > 7){
                throw new ReservationDuration7DaysException();
            }
            if (reservationService.countConcecutiveReservationDays(reservationService.testReservationDates(vehicleService.reservationDates(vehicle), start, end)) > 30){
                throw new ReservationDuration30DaysException();
            }
            Reservation reservation = new Reservation(client,vehicle,start,end);
            reservationService.create(reservation);
            request.setAttribute("reservations", reservationService.findAll());
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/list.jsp").forward(request, response);

        } catch (ServiceException e) {
            throw new ServletException();
        } catch (AlreadyReservedException e) {
            request.setAttribute("errorMessage", e.getMessage());
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/create.jsp").forward(request, response);
        } catch (ReservationDuration7DaysException e) {
            request.setAttribute("errorMessage", e.getMessage());
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/create.jsp").forward(request, response);
        } catch (ReservationDuration30DaysException e) {
            request.setAttribute("errorMessage", e.getMessage());
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/create.jsp").forward(request, response);
        }
    }
}