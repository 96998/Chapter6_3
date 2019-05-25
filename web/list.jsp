<%@ page import="person.Person" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 2019/5/25
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <td>name</td>
        <td>age</td>
    </tr>
    <tr>
            <%
            List<Person> all = (List<Person>) application.getAttribute("person");
            if (all != null) {
                for (Person person : all) {
        %>
    <tr>
        <td><%=person.getName()%>
        </td>
        <td><%=person.getAge()%>
        </td>
        <br>
    <tr>
        <% }
        }
        %>
    </tr>
</table>
<a href="../index.jsp">继续添加</a>
<a href="/Chapter6_3_war_exploded/index.jsp">继续添加</a>
<a href="/">啦啦啦</a>
</body>
</html>
