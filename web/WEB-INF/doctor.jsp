<%@ page import="java.util.List" %>
<%@ page import="com.itnet.model.Person" %>
<%@ page import="com.itnet.model.Task" %><%--
  Created by IntelliJ IDEA.
  User: Aram
  Date: 8/10/2021
  Time: 11:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%List<Person> persons = (List<Person>) request.getAttribute("persons");  %>
<%List<Task> tasks = (List<Task>) request.getAttribute("tasks");  %>

<div>
    <div style="width: 50%; float: left; background-color: cornflowerblue">
      <h1>Add Person</h1>
          <form action="/personRegister" method="post">
              <input type="text" name="name" placeholder="name"><br>
              <input type="text" name="surname" placeholder="surname"> <br>
              <input type="text" name="email" placeholder="email"> <br>
              <input type="password" name="password" placeholder="password"> <br>
              <select name="type">
                      <option value="DOCTOR">DOCTOR</option>
                      <option value="PATIENT">PATIENT</option>
                      <option value="NURSE">NURSE</option>
              </select><br>
              <input type="submit" value="register">
          </form>
    </div>

    <div style="width: 50%; float: left; background-color: greenyellow">
       <h3>Add task</h3>
        <form action="/taskRegister" method="post">
             <input type="text" name="title" placeholder="title"><br>
             <input type="text" name="description" placeholder="description"> <br>
             <input type="date" name="date" > <br>
             <select name="status">
                    <option value="NEW">NEW</option>
                    <option value="IN_PROGRESS">IN_PROGRESS</option>
                    <option value="FINISHED">FINISHED</option>
             </select> <br>
             <select name="person_id">
                     <% for (Person person: persons) { %>
                      <option value="<%=person.getId()%>"><%=person.getName()%><%=person.getSurname()%> </option>
                      <% } %>
             </select> <br>
             <input type="submit" value="Add task">
    </form>
</div>
</div><br>
<div>
    All Person:<br>
    <table border="2px">
        <tr>
            <th>name</th>
            <th>surname</th>
            <th>email</th>
            <th>password</th>
            <th>type</th>
            <th>action</th>
        </tr>
        <% for (Person person: persons) { %>
            <tr>
                <td><%=person.getName()%></td>
                <td><%=person.getSurname()%></td>
                <td><%=person.getEmail()%></td>
                <td><%=person.getPassword()%></td>
                <td><%=person.getPersonType()%></td>
                <td><a href="/deletePerson?personId=<%=person.getId()%>">Delete</a></td>
            </tr>

        <%}%>

    </table>
    <div style="text-align: center; background-color: cornflowerblue; height: 50px">
        <% String answerDel = (String) session.getAttribute("answerDel");
            if(answerDel !=null && !"".equals(answerDel)){%>
        <span style="color:yellow; border: red"> <%=answerDel%></span>
        <%
                session.removeAttribute("answerDel");
            }%>
    </div>


</div>

<div>
    All Task:<br>
    <table border="2px">
        <tr>
            <th>title</th>
            <th>description</th>
            <th>admission date</th>
            <th>status</th>
            <th>person</th>
            <th>action</th>

        </tr>
        <% if(tasks != null && !tasks.isEmpty()){
            for (Task task: tasks) { %>
        <tr>
            <td><%=task.getTitle()%></td>
            <td><%=task.getDescription()%></td>
            <td><%=task.getAdmissionDate()%></td>
            <td><%=task.getTaskType().name()%></td>
            <td><%=task.getPerson().getName()+" "+ task.getPerson().getSurname()%></td>

            <td>
                <form action="/changeTaskStatus">
                    <input type="hidden" name="taskId" value="<%=task.getId()%>">
                    <select name="status">
                        <option value="NEW">NEW</option>
                        <option value="IN_PROGRESS">IN_PROGRESS</option>
                        <option value="FINISHED">FINISHED</option>
                    </select> <br> <input type="submit" value="OK">

                </form>
            </td>
            <td><a href="/deleteTask?id=<%=task.getId()%>">Delete</a></td>
        </tr>

        <%} }%>

    </table>
</div>

</body>
</html>
