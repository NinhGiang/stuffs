<%-- 
    Document   : cart
    Created on : Jun 19, 2020, 9:42:32 PM
    Author     : Ninh Giang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>
    </head>
    <body>
        <h1>Your Cart</h1>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:set var="errors" value="${requestScope.ERRORS}"/>
        <c:if test="${not empty requestScope.CONFIRM}">
            <font color="red">${requestScope.CONFIRM}</font>
        </c:if>
        <c:if test="${not empty cart}">
            <c:set var="tourInCart" value="${cart.tourInCart}"/>
            <c:if test="${not empty tourInCart}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Tour ID</th>
                            <th>Tour Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    <form action="updateitem">
                        <c:forEach var="tour" items="${tourInCart}" varStatus="counter">

                            <tr>
                                <td>${counter.count}</td>
                                <td>${tour.tourID}
                                    <input type="hidden" name="txtTourID" value="${tour.tourID}" />
                                </td>
                                <td>${tour.tourName}
                                    <input type="hidden" name="txtTourName" value="${tour.tourName}" />
                                </td>
                                <td>${tour.price}
                                    <input type="hidden" name="txtPrice" value="${tour.price}" />
                                </td>
                                <td>
                                    <select name="txtQuantity">
                                        <c:forEach var="num" begin="1" end="30">
                                            <option
                                                <c:if test="${tour.quantity == num}">
                                                    selected="selected"
                                                </c:if>
                                                >${num}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <input type="checkbox" name="chkRemove" value="${tour.tourID}" />
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <th colspan="4">
                                <a href="dashboard.jsp">Click here to go to dashboard!</a>
                            </th>
                            <th>
                                <input type="submit" value="Update" name="btnAction" />
                            </th>
                            <th>
                                <input type="submit" value="Remove" name="btnAction" />
                            </th>
                        </tr>
                    </form>
                </tbody>
            </table><br/>
            <font color="red">Total: ${sessionScope.TOTAL}</font><br/>
            
            <form action="applydiscount">
                <c:if test="${empty sessionScope.DISCOUNTCODE}">
                    Discount Code: <input type="text" name="txtDiscount" value="${param.txtDiscount}" />
                    <input type="submit" value="Apply Discount Code" name="btnAction"/>
                    <c:if test="${not empty errors.discountCodeNotFound}">
                        <font color="red">
                        ${errors.discountCodeNotFound}
                        </font><br/>
                    </c:if>
                    <c:if test="${not empty errors.discountCodeIsExpired}">
                        <font color="red">
                        ${errors.discountCodeIsExpired}
                        </font><br/>
                    </c:if>
                    <c:if test="${not empty errors.discountCodeIsUsed}">
                        <font color="red">
                        ${errors.discountCodeIsUsed}
                        </font><br/>
                    </c:if>
                </c:if>
                <c:if test="${not empty sessionScope.DISCOUNTCODE}">
                    Discount code <font color="red">${sessionScope.DISCOUNTCODE}</font> is used.<br/>
                    Total price after discount: ${sessionScope.AFTERDISCOUNT}
                    <input type="submit" value="Remove Discount Code" name="btnAction"/>
                </c:if>
            </form>
            <c:if test="${not empty errors.itemOutOfStock}">
                <font color="red">
                ${errors.itemOutOfStock}
                </font><br/>
            </c:if>
            <form action="checkout">
                <input type="submit" value="Check Out" />
            </form>
        </c:if>
    </c:if>
    <c:if test="${empty tourInCart}">
        <h3>Empty cart!</h3>
        <a href="dashboard.jsp">Click here to go to dashboard!</a>
    </c:if>
</body>
</html>
