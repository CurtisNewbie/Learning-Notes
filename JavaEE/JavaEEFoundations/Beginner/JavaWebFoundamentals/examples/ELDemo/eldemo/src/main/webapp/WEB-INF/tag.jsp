<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.curtisnewbie.Account" %>

<html>
<head>
    <title>Tag Demo</title>
</head>
<body>

<p>The details that you gave us include:</p>
<p>Name: ${account.name}</p>
<p>Money: ${account.money}</p>
<p>Age: ${account.age}</p>


<%-- Conditional tag --%>
<c:if test="${empty account.name}">
    Error:Name is empty or null!!! <br>
</c:if>

<c:if test="${account.money < 0}">
    Error:You didn't tell me how much money you have!! <br>
</c:if>

<c:if test="${account.age < 0}">
    Error:You didn't tell me how old are you!!<br>
</c:if>

<%-- iteration tag --%>
<ul>
    <c:forEach items="${account.randomStrings}" var="arr">
        <li>${arr}</li>
    </c:forEach>
</ul>
</body>
</html>
