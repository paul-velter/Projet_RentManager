package com.epf.rentmanager.servlet.reservation;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/rents/delete")
public class DeleteReservationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
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
            reservationService.delete(reservationService.findById(id));
            request.setAttribute("reservation", reservationService.findAll());
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/list.jsp").forward(request, response);
        } catch (ServiceException e) {
            throw new ServletException();
        }
    }
}