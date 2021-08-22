package com.itnet.servlet;

import com.itnet.manager.PersonManger;
import com.itnet.model.Person;
import com.itnet.model.PersonType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/personRegister")
public class PersonRegisterServlet extends HttpServlet {

    PersonManger personManger = new PersonManger();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Person currentPerson = (Person) session.getAttribute("person");

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String type = req.getParameter("type");

        Person person = Person.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .password(password)
                .personType(PersonType.valueOf(type))
                .build();
        personManger.addPerson(person);
       // req.getSession().getAttribute("person");
        //resp.sendRedirect("/");

        if (currentPerson.getPersonType() == PersonType.DOCTOR){
            resp.sendRedirect("/doctor");
        }else {
            if(currentPerson.getPersonType() == PersonType.PATIENT) {
                resp.sendRedirect("/patient");
            }else {
                resp.sendRedirect("/nurse");
            }
        }




    }

}
