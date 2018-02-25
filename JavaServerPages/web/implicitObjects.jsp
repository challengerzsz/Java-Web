<%@ page import="java.util.Enumeration" %><%--
  Created by IntelliJ IDEA.
  User: 66490
  Date: 2018/2/25
  Time: 22:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP Implicit Objects</title>
</head>
<body>
    <b>Http headers:</b><br>
    <%
        for (Enumeration<String> enumeration = request.getHeaderNames(); enumeration.hasMoreElements();) {
            String header = enumeration.nextElement();
            out.println(header + ":" + request.getHeader(header) + "<br/>");
        }
    %>
    <br>
    <%
        out.println("Buffer size: " + response.getBufferSize());
        out.println("Session id: " + session.getId() + "</br>");
        out.println("Servlet name: " + config.getServletName() + "</br>");
        out.println("Server info" + application.getServerInfo());
    %>
</body>
</html>
