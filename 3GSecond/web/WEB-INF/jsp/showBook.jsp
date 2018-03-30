<%--
  Created by IntelliJ IDEA.
  User: 66490
  Date: 2018/3/30
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>change book</title>
</head>
<body>
<form action="changeBooks" method="post">
    书名：<input name="bookName" type="text" value="${book.bookname}"/><br>
    作者: <input name="author" type="text" value="${book.author}"/><br>
    价格: <input name="price" type="text" value="${book.price}"/>
    <input type="submit" value="提交">
</form>
</body>
</html>
