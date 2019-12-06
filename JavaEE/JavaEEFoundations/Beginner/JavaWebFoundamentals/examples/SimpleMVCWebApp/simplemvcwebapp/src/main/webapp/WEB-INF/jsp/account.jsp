<%@page import="com.curtisnewbie.app.Account" %>

<html>

<% 
    Account account = (Account)request.getAttribute("account");
%>

<head>
    <!-- with an equal symbol, it only output the returned value -->
    <title>Mate: <%= account.getName() %></title>

</head>

<body>
    <section>
        <h1>Account Info</h1>
        <p>You name is: <%=account.getName() %></p>
        <p>Your acount has: <%= account.getDeposit() %></p>
    </section>
</body>

</html>