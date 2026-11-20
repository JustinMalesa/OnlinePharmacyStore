<%-- 
    Document   : admin
    Created on : 26 May 2026, 09:05:31
    Author     : justi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Login</title>
     <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f9;
        }

        .container {
            width: 500px;
            background: white;
            margin: 80px auto;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px #ccc;
            text-align: center;
        }

        h1 {
            color: #2c3e50;
        }

        .btn {
            background-color: #3498db;
            color: white;
            border: none;
            padding: 12px 20px;
            border-radius: 5px;
            cursor: pointer;
        }

        .btn:hover {
            background-color: #2980b9;
        }

        .link-btn {
            text-decoration: none;
            background-color: #2ecc71;
            color: white;
            padding: 12px 20px;
            border-radius: 5px;
        }
    </style>
</head>
<body>

    <h1>Admin Login</h1>

    <form action="adminDashboard.jsp" method="post">

        <label>Admin Username:</label>
        <br>

        <input type="text"
               name="username"
               required>

        <br><br>

        <label>Password:</label>
        <br>

        <input type="password"
               name="password"
               required>

        <br><br>

        <input type="submit"
               value="Login">

    </form>

</body>
</html>