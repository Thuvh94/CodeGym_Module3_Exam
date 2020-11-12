<%--
  Created by IntelliJ IDEA.
  User: thu
  Date: 11/12/2020
  Time: 10:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Result</title>
</head>
<body>
<table>
    <a href="/Product?action=create">Create</a>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Color</th>
        <th>Desc</th>
        <th>Category</th>
        <th></th>
        <th></th>
        <%--        //                productName, productPrice, productQuantity,productColor,productDesc,categoryId--%>
    </tr>
    <c:forEach items="${productList}" var="product">
        <tr>
            <td>${product.getProductId()}</td>
            <td>${product.getProductName()}</td>
            <td>${product.getProductPrice()}</td>
            <td>${product.getProductQuantity()}</td>
            <td>${product.getProductColor()}</td>
            <td>${product.getProductDesc()}</td>
            <td>${product.getCategory().getCategoryName()}</td>
            <td>
                <a href="/Product?action=edit&id=${product.getProductId()}">Edit</a>
            </td>
            <td>
                <a href="/Product?action=delete&id=${product.getProductId()}">Delete</a>
            </td>

        </tr>
    </c:forEach>
</table>
<form method="post" action="/Product?action=search">
    <input type="text" name="searchName">
    <input type="submit" value="Search">
</form>
</body>
</html>