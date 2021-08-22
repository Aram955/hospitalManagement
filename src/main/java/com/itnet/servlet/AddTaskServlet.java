package com.itnet.servlet;

import com.itnet.manager.TaskManger;
import com.itnet.model.Task;
import com.itnet.model.TaskType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = "/taskRegister")

public class AddTaskServlet  extends HttpServlet {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    TaskManger taskManger = new TaskManger();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String admissionDate = req.getParameter("date");
        String status = req.getParameter("status");
        int personId = Integer.parseInt(req.getParameter("person_id"));

        try { taskManger.addTask(Task.builder()
                .title(title)
                .description(description)
                .admissionDate(sdf.parse(admissionDate))
                .taskType(TaskType.valueOf(status))
                .personId(personId)
                .build());
            resp.sendRedirect("/doctor");
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
