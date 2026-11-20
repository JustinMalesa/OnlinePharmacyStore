
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<!DOCTYPE html>
<html>
<head>
    <title>Shopping Cart</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

    <h1>Shopping Cart</h1>

    <c:choose>

        <c:when test="${empty cart}">

            <div class="empty-message">
                <h3>Your shopping cart is empty.</h3>
                <a href="medicines" class="link-btn">Continue Shopping</a>
            </div>

        </c:when>

        <c:otherwise>

            <table>

                <tr>
                    <th>ID</th>
                    <th>Medicine Name</th>
                    <th>Category</th>
                    <th>Unit Price</th>
                    <th>Quantity</th>
                    <th>Subtotal</th>
                    <th>Action</th>
                </tr>

                <c:set var="grandTotal" value="0"/>

                <c:forEach var="item" items="${cart}">

                    <c:set var="grandTotal"
                           value="${grandTotal + item.subtotal}"/>

                    <tr>

                        <td>${item.medicine.id}</td>

                        <td>
                            <c:out value="${item.medicine.name}"/>
                        </td>

                        <td>
                            <c:out value="${item.medicine.category}"/>
                        </td>

                        <td>
                            R <fmt:formatNumber
                                value="${item.medicine.price}"
                                minFractionDigits="2"
                                maxFractionDigits="2"/>
                        </td>

                        <td>

                            <form action="cart" method="post"
                                  style="display:inline;">

                                <input type="hidden"
                                       name="action"
                                       value="decrease">

                                <input type="hidden"
                                       name="medicineId"
                                       value="${item.medicine.id}">

                                <button type="submit">-</button>

                            </form>

                            <strong>${item.quantity}</strong>

                            <form action="cart" method="post"
                                  style="display:inline;">

                                <input type="hidden"
                                       name="action"
                                       value="increase">

                                <input type="hidden"
                                       name="medicineId"
                                       value="${item.medicine.id}">

                                <button type="submit">+</button>

                            </form>

                        </td>

                        <td>
                            R <fmt:formatNumber
                                value="${item.subtotal}"
                                minFractionDigits="2"
                                maxFractionDigits="2"/>
                        </td>

                        <td>

                            <form action="cart" method="post">

                                <input type="hidden"
                                       name="action"
                                       value="remove">

                                <input type="hidden"
                                       name="medicineId"
                                       value="${item.medicine.id}">

                                <input type="submit"
                                       value="Remove"
                                       class="btn">

                            </form>

                        </td>

                    </tr>

                </c:forEach>

                <tr>
                    <td colspan="5" style="text-align:right;">
                        <strong>Grand Total:</strong>
                    </td>

                    <td colspan="2">
                        <strong>
                            R <fmt:formatNumber
                                value="${grandTotal}"
                                minFractionDigits="2"
                                maxFractionDigits="2"/>
                        </strong>
                    </td>
                </tr>

            </table>

            <br>

            <a href="medicines" class="link-btn">
                Continue Shopping
            </a>

            <a href="checkout.jsp" class="link-btn">
                Checkout
            </a>

        </c:otherwise>

    </c:choose>

</body>
</html>
