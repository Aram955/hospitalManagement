package com.itnet.servlet;

import com.itnet.manager.PersonManger;
import com.itnet.manager.TaskManger;
import com.itnet.model.Person;
import com.itnet.model.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/deletePerson")
public class DeletePerson extends HttpServlet {
    PersonManger personManger = new PersonManger();
    TaskManger taskManger =new TaskManger();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int personId = Integer.parseInt(req.getParameter("personId"));
        List<Task> tasks = taskManger.getAllTaskById(personId);
        if(tasks.size()<1) {
            personManger.deletePerson(personId);
            resp.sendRedirect("/doctor");
        } else{

            req.getSession().setAttribute("answerDel", "There are task");
            resp.sendRedirect("/doctor");
        }
    }

}
