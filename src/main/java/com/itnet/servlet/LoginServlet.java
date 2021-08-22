package com.itnet.servlet;
import com.itnet.manager.PersonManger;
import com.itnet.model.Person;
import com.itnet.model.PersonType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import  javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    PersonManger personManger = new PersonManger();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Person person = personManger.getPersonByEmailAndPassword(email,password);

        if (person == null){
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
            req.getSession().setAttribute("answer", "Your email or password wrong");
        }else {
            HttpSession session = req.getSession();
            session.setAttribute("person", person);
            if (person.getPersonType() == PersonType.DOCTOR){
                resp.sendRedirect("/doctor");
            }else {
                if(person.getPersonType() == PersonType.PATIENT) {
                    resp.sendRedirect("/patient");
                }else {
                    resp.sendRedirect("/nurse");
                }
            }
        }


    }
}
