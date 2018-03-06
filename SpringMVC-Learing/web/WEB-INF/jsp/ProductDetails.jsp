<%--
  Created by IntelliJ IDEA.
  User: 66490
  Date: 2018/3/6
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Save Product</title>
</head>
<body>
<div id="global">
    <h4>The Product has been saved</h4>
    <p>
    <h5>Details</h5>
    Product Name : ${product.name}<br>
    Description : ${product.desc}
    Price: $${product.price}
    </p>
</div>
</body>
</html>