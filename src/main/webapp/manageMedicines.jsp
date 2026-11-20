<%-- 
    Document   : manageMedicines
    Created on : 27 May 2026, 17:37:39
    Author     : justi
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Medicines</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

    <h1>Manage Medicines</h1>

    <div class="container">

        <table>

            <tr>
                <th>ID</th>
                <th>Medicine Name</th>
                <th>Category</th>
                <th>Price</th>
                <th>Action</th>
            </tr>

            <c:forEach var="medicine" items="${medicineList}">

                <tr>
                    <td>
                        <c:out value="${medicine.id}"/>
                    </td>

                    <td>
                        <c:out value="${medicine.name}"/>
                    </td>

                    <td>
                        <c:out value="${medicine.category}"/>
                    </td>

                    <td>
                        R <c:out value="${medicine.price}"/>
                    </td>

                    <td>
                        <form action="deleteMedicine" method="post">

                            <input type="hidden"
                                   name="medicineId"
                                   value="${medicine.id}">

                            <input type="submit"
                                   value="Delete"
                                   class="btn">
                        </form>
                    </td>
                </tr>

            </c:forEach>

        </table>

        <br>

        <a href="adminDashboard.jsp" class="link-btn">
            Back to Admin Dashboard
        </a>

    </div>

</body>
</html>
