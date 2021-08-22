<%@ page import="com.itnet.model.Task" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Aram
  Date: 8/10/2021
  Time: 11:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>You are a hospital patient</h1>

<%List<Task> tasks = (List<Task>) request.getAttribute("task");  %>

<div style="width: 50%; float: left; background-color: cornflowerblue">
    <h1>Add Person</h1>
    <form action="/personRegister" method="post">
        <input type="text" name="name" placeholder="name"><br>
        <input type="text" name="surname" placeholder="surname"> <br>
        <input type="text" name="email" placeholder="email"> <br>
        <input type="password" name="password" placeholder="password"> <br>
        <select name="type">

            <option value="PATIENT">PATIENT</option>

        </select><br>
        <input type="submit" value="register">
    </form>
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

        </tr>
        <% for (Task task: tasks) { %>
        <tr>
            <td><%=task.getTitle()%></td>
            <td><%=task.getDescription()%></td>
            <td><%=task.getAdmissionDate()%></td>
            <td><%=task.getTaskType().name()%></td>
            <td><%=task.getPerson().getName()+" "+ task.getPerson().getSurname()%></td>

        </tr>

        <%}%>

    </table>
</div>



</body>
</html>
