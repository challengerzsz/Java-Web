<%--
  Created by IntelliJ IDEA.
  User: 66490
  Date: 2018/3/4
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Country List</title>
</head>
<body>
    <c:forEach items="${countries}" var="country">
        <li>${country.value}</li>
    </c:forEach>
</body>
</html>
