<%-- 
    Document   : login
    Created on : 25 May 2026, 00:22:45
    Author     : justi
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <title>User Login</title>
    <link rel="stylesheet" href="css/style.css">
    
    <style>
        .login-container {
            width: 350px;
            margin: 80px auto;
        }

        .login-container input[type=text],
        .login-container input[type=password] {
            width: 100%;
            margin-top: 5px;
            margin-bottom: 15px;
        }

        .login-container .btn {
            width: 100%;
            padding: 12px;
        }

        .center {
            text-align: center;
        }

        .center a {
            text-decoration: none;
            color: #3498db;
        }
    </style>
</head>

<body>

<div class="container login-container">

    <h1>User Login</h1>

    <p class="success-message">
        <c:out value="${message}"/>
    </p>

    <form action="user" method="post">

        <input type="hidden"
               name="action"
               value="login">

        <label>Username</label>

        <input type="text"
               name="username"
               required>

        <label>Password</label>

        <input type="password"
               name="password"
               required>

        <input type="submit"
               value="Login"
               class="btn">

    </form>

    <br>

    <div class="center">
        <a href="register.jsp">
            Create an account
        </a>
    </div>

</div>

</body>
</html>