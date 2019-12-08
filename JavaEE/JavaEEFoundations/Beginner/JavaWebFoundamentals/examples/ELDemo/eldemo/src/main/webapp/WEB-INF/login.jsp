<%@page language="java" contentType="text/html; ISO-8859-1" %>
<%@page import="com.curtisnewbie.Login"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Welcome ${ user.name }</title>
</head>

<body>
    <p>You are: ${ user.name }</p>
    <p>Your three favourite veggie:</p>
    <ul>
        <li>${ user.veggie[0] }</li>
        <li>${ user.veggie[1] }</li>
        <li>${ user.veggie[2] }</li>
    </ul>

    1 + 1 = ${ 1 + 1}

    <p>Your Host: ${ header.host }</p>
</body>

</html>