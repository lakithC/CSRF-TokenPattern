<%-- 
    Document   : main
    Created on : Sep 18, 2018, 11:15:59 PM
    Author     : hp
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<html>
<head>
    <title>Main View</title>
</head>
<body>
    <h1>Login Details</h1>
    You are logged in as <%=session.getAttribute("username")%>
    <form action="validate" method="POST">
        
        <label>Identification Number :</label>
        <input type="text" name="identity" placeholder="ID"><br/>
        
        <label>Password</label>
        <input type="password" name="values" placeholder="password"><br/>
        <input type="hidden" name="receiver" id="receiver"/>
        <input type="submit" value="Submit"/>

    </form>
    <script src="./jquery.min.js"></script>
    <script>
        $.ajax({
            type: 'GET',
            dataType: 'json',
            url: '/Tokens/validate',
            success: function (data) {
                alert(data);
                $("#receiver").val(data.tokens);
            },
            error: function (status) {
                alert(status);
            }
        });
    </script>

</body>
</html>
