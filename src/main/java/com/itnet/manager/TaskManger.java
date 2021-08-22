package com.itnet.manager;

import com.itnet.db.DBConnectionProvider;
import com.itnet.model.Task;
import com.itnet.model.TaskType;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TaskManger {
    private Connection connection = DBConnectionProvider.getProvider().getConnection();
    private PersonManger personManger = new PersonManger();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public void addTask(Task task) {
        try {
            String query = "INSERT INTO `task` (`title`,`description`,`admission_date`,`status`, `person_id`) " +
                    "VALUES(?,?,?,?,?);";
            PreparedStatement pStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pStatement.setString(1, task.getTitle());
            pStatement.setString(2, task.getDescription());
            pStatement.setString(3, sdf.format(task.getAdmissionDate()));
            pStatement.setString(4, task.getTaskType().name());
            pStatement.setInt(5, task.getPersonId());
            System.out.println(query);
            pStatement.executeUpdate();
            ResultSet generatedKeys = pStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                task.setId(id);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public List<Task> getAllTasks() {
        String sql = "SELECT * from task";
        List<Task> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Task task = Task.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .description(resultSet.getString(3))
                        .admissionDate(resultSet.getDate(4))
                        .taskType(TaskType.valueOf(resultSet.getString(5)))
                        .person(personManger.getPersonById(resultSet.getInt(6)))
                        .build();
                result.add(task);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public  void updateTaskStatus (int taskId, String newStatus){
        try {
            String query = "UPDATE task set status = ? where id= ?";
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setString(1,newStatus);
            pStatement.setInt(2,taskId);
            pStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    public List<Task> getAllTaskById(int personId) {
        List<Task> tasks = new ArrayList<>();

        try {
            String sql = "SELECT * FROM task WHERE person_id=?" ;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, personId);
            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                Task task = Task.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .description(resultSet.getString(3))
                        .admissionDate(resultSet.getDate(4))
                        .taskType(TaskType.valueOf(resultSet.getString(5)))
                        .person(personManger.getPersonById(resultSet.getInt(6)))
                        .build();
                tasks.add(task);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tasks;
    }

    public void deleteTask(int id) {
        String sql = "DELETE from task where id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
