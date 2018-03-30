<%--
  Created by IntelliJ IDEA.
  User: 66490
  Date: 2018/3/30
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>books</title>
</head>
<body>
<c:forEach items="${books}" var="book">
    ${book.bookname} ${book.author} ${book.price} <br>
</c:forEach>
<form action="deleteBook" method="post">
    删除的图书名：<input type="text" name="bookName">
    <input type="submit" value="提交">
</form>

<form action="queryBook" method="post">
    查询的书名: <input type="text" name="bookName"/>
    <input type="submit" value="查询"/>
</form>


</body>
</html>
