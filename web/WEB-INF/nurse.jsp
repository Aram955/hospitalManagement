<%@ page import="com.itnet.model.Task" %>
<%@ page import="java.util.List" %>
<%@ page import="com.itnet.model.Person" %><%--
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

<h1>You are a hospital nurse</h1>
<%List<Person> persons = (List<Person>) request.getAttribute("person");  %>
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
            <option value="NURSE">NURSE</option>
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
            <Th>Update Status</th>
        </tr>
        <% for (Task task: tasks) { %>
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
        </tr>

        <%}%>

    </table>
</div>



</body>
</html>
