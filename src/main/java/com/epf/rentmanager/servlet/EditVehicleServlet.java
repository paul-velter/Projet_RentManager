package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cars/edit")//TODO: ajouter le propriétaire
public class EditVehicleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            VehicleService vehicleService = VehicleService.getInstance();
            request.setAttribute("vehicle", vehicleService.findById(id));
        } catch (ServiceException e) {
            throw new ServletException();
        } finally {
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/edit.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            String constructor = request.getParameter("manufacturer");
            String modele = request.getParameter("modele");
            int seats = Integer.parseInt(request.getParameter("seats"));

            VehicleService vehicleService = VehicleService.getInstance();
            Vehicle vehicle = new Vehicle(id,constructor, modele,seats);
            vehicleService.edit(vehicle);
            request.setAttribute("vehicles", vehicleService.findAll());

        } catch (ServiceException e) {
            throw new ServletException();
        }finally {
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/list.jsp").forward(request, response);
        }
    }
}
