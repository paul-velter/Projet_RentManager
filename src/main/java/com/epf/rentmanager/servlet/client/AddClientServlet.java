package com.epf.rentmanager.servlet.client;

import com.epf.rentmanager.exception.AgeException;
import com.epf.rentmanager.exception.EmailAlreadyExist;
import com.epf.rentmanager.exception.NameLenghtException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

@WebServlet("/users/create")
public class AddClientServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Autowired
    ClientService clientService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            String first_name = request.getParameter("first_name");
            String last_name = request.getParameter("last_name");
            if (first_name.length()<3 || last_name.length()<3){
                throw new NameLenghtException();
            }
            String email = request.getParameter("email");
            if (clientService.findAllEmails().contains(email)){
                throw new EmailAlreadyExist();
            }
            LocalDate birth_date = LocalDate.parse(request.getParameter("birth_date"));
            if (Period.between(birth_date, LocalDate.now()).getYears() < 18) {
                throw new AgeException();
            }
            Client client = new Client(first_name, last_name, email, birth_date);
            clientService.create(client);
            request.setAttribute("clients", clientService.findAll());
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/list.jsp").forward(request, response);

        } catch (ServiceException e) {
            throw new ServletException();
        } catch (AgeException e) {
            request.setAttribute("errorMessage", e.getMessage());
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request, response);
        }catch (EmailAlreadyExist e) {
            request.setAttribute("errorMessage", e.getMessage());
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request, response);
        } catch (NameLenghtException e) {
            request.setAttribute("errorMessage", e.getMessage());
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request, response);
        }
    }
}