<%-- 
    Document   : profile
    Created on : 25 May 2026, 00:23:49
    Author     : justi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title>
    <link rel="stylesheet" href="css/style.css">

    <style>
        .profile-container {
            width: 500px;
            margin: 80px auto;
            text-align: center;
        }

        .profile-container h2 {
            color: #3498db;
        }

        .profile-container .btn,
        .profile-container .link-btn {
            padding: 12px 20px;
        }
    </style>
</head>

<body>

<div class="container profile-container">

    <h1>User Profile</h1>

    <h2>
        Welcome,
        <c:out value="${sessionScope.loggedInUser.username}"/>
    </h2>

    <p>You are successfully logged in.</p>

    <br>

    <a href="medicines" class="link-btn">
        Browse Medicines
    </a>

    <br><br>

    <form action="user" method="post">

        <input type="hidden"
               name="action"
               value="logout">

        <input type="submit"
               value="Logout"
               class="btn">

    </form>

</div>

</body>
</html>
