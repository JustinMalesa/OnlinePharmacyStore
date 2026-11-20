
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <title>User Registration</title>
    <link rel="stylesheet" href="css/style.css">

    <style>
        .register-container {
            width: 350px;
            margin: 80px auto;
        }

        .register-container input[type=text],
        .register-container input[type=password] {
            width: 100%;
            box-sizing: border-box;
            margin-top: 5px;
            margin-bottom: 15px;
        }

        .register-container .btn {
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

        .hint {
            color: #666;
            font-size: 12px;
        }
    </style>

    <script>
        function validateForm() {
            const username = document.getElementById("username").value.trim();
            const password = document.getElementById("password").value;
            const message = document.getElementById("validationMessage");

            message.textContent = "";

            if (username === "" || password === "") {
                message.textContent = "Please enter a username and password.";
                return false;
            }

            if (username.length < 3) {
                message.textContent = "Username must be at least 3 characters.";
                return false;
            }

            if (username.length > 20) {
                message.textContent = "Username must not exceed 20 characters.";
                return false;
            }

            if (!/^[A-Za-z0-9_]+$/.test(username)) {
                message.textContent =
                    "Username may only contain letters, numbers and underscores.";
                return false;
            }

            if (password.length < 8) {
                message.textContent = "Password must be at least 8 characters.";
                return false;
            }

            return true;
        }
    </script>
</head>

<body>

<div class="container register-container">

    <h1>Create Account</h1>

    <p class="message">
        <c:out value="${message}"/>
    </p>

    <form action="user" method="post" onsubmit="return validateForm()">

        <input type="hidden"
               name="action"
               value="register">

        <p id="validationMessage" class="message"></p>

        <label>Username</label>

        <input type="text"
               id="username"
               name="username"
               maxlength="20">

        <p class="hint">
            3–20 characters. Letters, numbers and underscores only.
        </p>

        <label>Password</label>

        <input type="password"
               id="password"
               name="password">

        <p class="hint">
            Password must contain at least 8 characters.
        </p>

        <input type="submit"
               value="Register"
               class="btn">

    </form>

    <br>

    <div class="center">
        <a href="login.jsp">
            Already have an account? Login
        </a>
    </div>

</div>

</body>
</html>
