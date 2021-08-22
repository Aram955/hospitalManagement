package com.itnet.servlet;

import com.itnet.manager.TaskManger;
import com.itnet.model.Person;
import com.itnet.model.PersonType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/changeTaskStatus")
public class ChangeTaskStatus extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int taskId = Integer.parseInt(req.getParameter("taskId"));
        String statusTask= req.getParameter("status");

        Person person = (Person) req.getSession().getAttribute("person");
        TaskManger taskManger = new TaskManger();
        taskManger.updateTaskStatus(taskId,statusTask);

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
