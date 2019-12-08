<%@page import="com.curtisnewbie.Login"%>
<html>

<head>
    <title>ELDemo</title>
</head>

<body>
    <h2>Hello Mate! We Are Using EL Here</h2>
    <form action="login" method="POST">
        <p>Name: <input type=" text" name="name"></p>
        <p>
            Three Favourite Veggie: <br>
            <input type="text" name="veg1"><br>
            <input type="text" name="veg2"><br>
            <input type="text" name="veg3"><br>
        </p>
        <p>
            <input type="submit" id="submit" value="Submit">
        </p>
    </form>
</body>


</html>