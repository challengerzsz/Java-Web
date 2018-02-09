<%--
  Created by IntelliJ IDEA.
  User: shuaizhi zeng
  Date: 2018/2/8
  Time: 0:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>登录界面</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <!--判断是否为空-->
    <script type="text/javascript">
        function login(form){
            if(form.username.value == ""){
                alert("用户不能为空！");
                return false;
            }
            if(form.password.value == ""){
                alert("密码不能为空！");
                return false;
            }
        }
    </script>
</head>

<body>
<center>
    <form method="post" action="LoginServlet" onSubmit="return login(this);">
        用户名：<input type="text" name="username" ><br>
        密码：  <input type="password" name="password" ><br>
        <br>
        <input type="submit" value="登录">
        <input type="reset" value="重置">
    </form>
</center>
</body>
</html>