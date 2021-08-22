package com.itnet.manager;


import com.itnet.db.DBConnectionProvider;
import com.itnet.model.Person;
import com.itnet.model.PersonType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonManger {

    private Connection connection = DBConnectionProvider.getProvider().getConnection();

    public void addPerson(Person person) {
        try {
            String query = "INSERT INTO `person` (`name`,`surname`,`email`,`password`,`type`) " +
                    "VALUES(?,?,?,?,?);";
            PreparedStatement pStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pStatement.setString(1, person.getName());
            pStatement.setString(2, person.getSurname());
            pStatement.setString(3, person.getEmail());
            pStatement.setString(4, person.getPassword());
            pStatement.setString(5,person.getPersonType().name());
            System.out.println(query);
            pStatement.executeUpdate();
            ResultSet generatedKeys = pStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                person.setId(id);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Person> getAllPerson() {
        String sql = "SELECT * from person";
        List<Person> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Person person = Person.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .surname(resultSet.getString(3))
                        .email(resultSet.getString(4))
                        .password(resultSet.getString(5))
                        .personType(PersonType.valueOf(resultSet.getString(6)))
                        .build();
                result.add(person);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public Person getPersonById(int id) {
        String sql = "SELECT * FROM person WHERE id=" + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return Person.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .surname(resultSet.getString(3))
                        .email(resultSet.getString(4))
                        .password(resultSet.getString(5))
                        .personType(PersonType.valueOf(resultSet.getString(6)))
                        .build();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public Person getPersonByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM person WHERE email='" + email + "' and password = '" + password+"'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return Person.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .surname(resultSet.getString(3))
                        .email(resultSet.getString(4))
                        .password(resultSet.getString(5))
                        .personType(PersonType.valueOf(resultSet.getString(6)))
                        .build();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public void deletePerson(int id) {
        String sql = "DELETE from person where id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
