package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/users/edit")
public class EditClientServlet  extends HttpServlet{
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            ClientService clientService = ClientService.getInstance();
            request.setAttribute("client", clientService.findById(id));
        } catch (ServiceException e) {
            throw new ServletException();
        } finally {
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/edit.jsp").forward(request, response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            long id = Long.parseLong(request.getParameter("id"));
            String first_name = request.getParameter("first_name");
            String last_name = request.getParameter("last_name");
            String email = request.getParameter("email");
            LocalDate birth_date = LocalDate.parse(request.getParameter("birth_date"));

            ClientService clientService = ClientService.getInstance();
            Client client = new Client(id, first_name, last_name, email, birth_date);
            clientService.edit(client);
            request.setAttribute("clients", clientService.findAll());

        } catch (ServiceException e) {
            throw new ServletException();
        }finally {
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/list.jsp").forward(request, response);
        }
    }
}
