<%--
  Created by IntelliJ IDEA.
  User: 66490
  Date: 2018/3/24
  Time: 21:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="UploadTestServlet" enctype="multipart/form-data" id="loginform" name="loginform" method="post">
        选择图片：<input type="file" name="filename"/>
        <input id="subid" name="subid" type="submit" value="提交">
    </form>
</body>
</html>
