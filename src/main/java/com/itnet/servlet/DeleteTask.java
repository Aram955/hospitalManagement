package com.itnet.servlet;

import com.itnet.manager.TaskManger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteTask")
public class DeleteTask extends HttpServlet {
    TaskManger taskManger = new TaskManger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       // String taskId = req.getParameter("id");
        int taskId = Integer.parseInt(req.getParameter("id"));
        taskManger.deleteTask(taskId);
        resp.sendRedirect("/doctor");
    }
}
