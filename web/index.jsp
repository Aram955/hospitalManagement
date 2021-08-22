<%--
  Created by IntelliJ IDEA.
  User: Aram
  Date: 8/14/2021
  Time: 2:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>$Title$</title>
</head>
<body>

<div style="text-align: center; height:150px; background-color: cornflowerblue" >
  <h1>Welcome to Hospital Vardenis </h1>
  <form action="/login" method="post">
    <input type="text" name="email" placeholder="Input your email">
    <input type="password" name="password" placeholder="Input your password">
    <input type="submit" value="Login">
  </form>
</div>
____________________________________________________________
<div style="text-align: center; background-color: cornflowerblue; height: 50px">
  <% String answer = (String) session.getAttribute("answer");
    if(answer !=null && !"".equals(answer)){%>
  <span style="color:yellow; border: red"> <%=answer%></span>
  <%
      session.removeAttribute("answer");
    }%>
</div>
</body>
</html>

