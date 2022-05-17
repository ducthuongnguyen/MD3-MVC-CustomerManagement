<%--
  Created by IntelliJ IDEA.
  User: Duc Thuong Nguyen
  Date: 5/17/2022
  Time: 4:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show Product List</title>
</head>
<body>
<h1>Show Product List</h1>
<c:forEach var="i" begin="0" end="${productList.size()-1}">
    ${productList.get(i).id}
   | ${productList.get(i).name}
   | ${productList.get(i).price}
   | ${categories.get(i).name}
    <br>
</c:forEach>
</body>
</html>
