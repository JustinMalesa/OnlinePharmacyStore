
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <title>Available Medicines</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

    <h1>Online Pharmacy Store</h1>

    <div class="top-section">

        <form action="medicines" method="get">

            <label><b>Select Category:</b></label>

            <select name="category">
                <option value="All">All</option>
                <option value="Pain Relief">Pain Relief</option>
                <option value="Vitamins">Vitamins</option>
                <option value="Cold & Flu">Cold & Flu</option>
                <option value="Allergy">Allergy</option>
                <option value="Digestive Health">Digestive Health</option>
            </select>

            <input type="submit" value="Filter" class="btn">

        </form>

    </div>

    <table>

        <tr>
            <th>ID</th>
            <th>Medicine Name</th>
            <th>Category</th>
            <th>Price</th>
            <th>Quantity</th>
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

                    <form action="cart" method="post">

                        <input type="hidden"
                               name="action"
                               value="add">

                        <input type="hidden"
                               name="medicineId"
                               value="${medicine.id}">

                        <input type="number"
                               name="quantity"
                               value="1"
                               min="1"
                               max="100"
                               required
                               style="width:70px;">

                </td>

                <td>

                        <input type="submit"
                               value="Add to Cart"
                               class="btn">

                    </form>

                </td>
            </tr>

        </c:forEach>

    </table>

    <br>

    <a href="cart" class="link-btn">View Cart</a>

</body>
</html>
