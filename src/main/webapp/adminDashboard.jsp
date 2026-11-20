
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="css/style.css">

    <style>
        .admin-container {
            width: 400px;
            margin: 70px auto;
            text-align: center;
        }

        .admin-container input[type=text],
        .admin-container input[type=number] {
            width: 100%;
            box-sizing: border-box;
            margin-top: 5px;
        }

        .admin-container .btn {
            display: inline-block;
            width: 100%;
            box-sizing: border-box;
            padding: 12px;
            text-decoration: none;
        }

        .admin-container .green {
            background-color: #2ecc71;
        }

        .admin-container .green:hover {
            background-color: #27ae60;
        }

        .admin-container .red {
            background-color: #e74c3c;
        }

        .admin-container .red:hover {
            background-color: #c0392b;
        }

        .welcome {
            color: #555;
        }
    </style>
</head>

<body>

<div class="container admin-container">

    <h1>Admin Dashboard</h1>

    <p class="welcome">
        Welcome,
        <c:out value="${sessionScope.loggedInUser.username}"/>!
    </p>

    <h2>Add New Medicine</h2>

    <form action="addMedicine" method="post">

        <label>Medicine Name:</label>

        <input type="text"
               name="name"
               required>

        <br><br>

        <label>Category:</label>

        <input type="text"
               name="category"
               required>

        <br><br>

        <label>Price:</label>

        <input type="number"
               step="0.01"
               min="0.01"
               name="price"
               required>

        <br><br>

        <input type="submit"
               value="Add Medicine"
               class="btn">

    </form>

    <br>

    <a href="medicines" class="btn green">
        View Medicines
    </a>

    <br><br>

    <a href="medicines?admin=true" class="btn">
        Manage Medicines
    </a>

    <br><br>

    <form action="user" method="post">

        <input type="hidden"
               name="action"
               value="logout">

        <input type="submit"
               value="Logout"
               class="btn red">

    </form>

</div>

</body>
</html>
