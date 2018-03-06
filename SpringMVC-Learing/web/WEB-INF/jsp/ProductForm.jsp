<%--
  Created by IntelliJ IDEA.
  User: 66490
  Date: 2018/3/6
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Product Form</title>
</head>
<body>
<div id="global">
    <c:if test="${requestScope.errors != null}">
        <p id="errors">
            Error(s)!
        <ul>
            <c:forEach var="error" items="${requestScope.errors}">
                <li>${error}</li>
            </c:forEach>
        </ul>
        </p>
    </c:if>
    <form action="product_save.action" method="post">
        <fieldset>
            <legend>Add a product</legend>
            <p>
                <label for="name">Product Name</label>
                <input type="text" id="name" name="name" tabindex="1">
            </p>
            <p>
                <label for="desc">Desc:</label>
                <input type="text" id="desc" name="desc" tabindex="2">
            </p>
            <p>
                <label for="price">Price:</label>
                <input type="text" id="price" name="price" tabindex="3">
            </p>
            <p id="buttons">
                <input type="reset" id="reset" tabindex="4">
                <input type="submit" id="submit" tabindex="5" value="Add Product">
            </p>
        </fieldset>
    </form>
</div>
</body>
</html>
