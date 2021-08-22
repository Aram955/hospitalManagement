package com.itnet.servlet;

import com.itnet.manager.TaskManger;
import com.itnet.model.Person;
import com.itnet.model.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/nurse")
public class NurseHomeServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Person person = (Person) session.getAttribute("person" );

        TaskManger taskManger = new TaskManger();
        List<Task> allTaskById = taskManger.getAllTaskById(person.getId());
        req.setAttribute("task", allTaskById);
        req.getRequestDispatcher("/WEB-INF/nurse.jsp").forward(req,resp);
    }
}
