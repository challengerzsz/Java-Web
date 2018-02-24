<%@ page import="java.text.DateFormat" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: 66490
  Date: 2018/2/24
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Today's date</title>
</head>
<body>
    <%
        DateFormat dateformat = DateFormat.getDateInstance(DateFormat.LONG);
        String date = dateformat.format(new Date());
        System.out.println("Today is " + date);
    %>
</body>
</html>
