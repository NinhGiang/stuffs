<%-- 
    Document   : dashboard
    Created on : Jun 17, 2020, 9:47:36 AM
    Author     : Ninh Giang
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
    img {
        border: 1px solid #ddd;
        border-radius: 4px;
        padding: 5px;
        width: 150px;
    }
</style>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard Page</title>
    </head>
    <body>
        <h1>Dashboard Page</h1>
        <c:set var="isRole" value="${sessionScope.ROLE}"/>
        <c:if test="${isRole == 'Admin'}">
            <c:if test="${empty sessionScope.FULLNAME}">
                <c:redirect url="login.html"/>
            </c:if>
            <a href="addtour.jsp">Insert New Tour Here</a>
        </c:if>
        <c:if test="${not empty sessionScope.FULLNAME}">
            <font color="red">Welcome, ${sessionScope.FULLNAME}</font>
            <form action="logout">
                <input type="submit" value="Logout" />
            </form>
            <a href="cart.jsp">View Your Cart</a>
        </c:if>
        <form action="search">
            <input type="hidden" name="txtPageNum" value="1" />
            From date: <input type="date" name="txtFromDate" value="${param.txtFromDate}" /><br/>
            To date: <input type="date" name="txtToDate" value="${param.txtToDate}" /><br/>
            Prom price: <input type="text" name="txtFromPrice" value="${param.txtFromPrice}" /><br/>
            To price: <input type="text" name="txtToPrice" value="${param.txtToPrice}" /><br/>
            Destination: <input type="text" name="txtPlace" value="${param.txtPlace}" />
            <input type="submit" value="Search" name="btnAction" />
        </form>
        <c:if test="${not empty requestScope.PAGEQUANTITY}">
            <c:forEach var="pageNum" begin="1" end="${requestScope.PAGEQUANTITY}">
                <c:url var="urlRewriting" value="search">
                    <c:param name="txtPageNum" value="${pageNum}"/>
                    <c:param name="txtFromDate" value="${param.txtFromDate}" /><br/>
                    <c:param name="txtToDate" value="${param.txtToDate}" /><br/>
                    <c:param name="txtFromPrice" value="${param.txtFromPrice}" /><br/>
                    <c:param name="txtToPrice" value="${param.txtToPrice}" /><br/>
                    <c:param name="txtPlace" value="${param.txtPlace}" />
                    <c:param value="Search" name="btnAction" />
                </c:url>
                <a href="${urlRewriting}">${pageNum}</a>
            </c:forEach>
        </c:if>
        <c:if test="${not empty param.btnAction}">
            <c:set var="result" value="${requestScope.SEARCHRESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>Tour ID</th>
                            <th>Tour Name</th>
                            <th>From Place</th>
                            <th>To Place</th>
                            <th>From Date</th>
                            <th>To Date</th>
                            <th>Price</th>
                            <th>Quota</th>
                            <th>Image</th>
                            <th>Import Date</th>
                                <c:if test="${not empty sessionScope.FULLNAME}">
                                <th>Action</th>
                                </c:if>
                        </tr>
                    </thead>
                    <tbody>
                    <form action="addtour">
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                            <tr>
                                <td>${dto.tourID}</td>
                                <td>${dto.tourName}</td>
                                <td>${dto.fromPlace}</td>
                                <td>${dto.toPlace}</td>
                                <td>${dto.fromDate}</td>
                                <td>${dto.toDate}</td>
                                <td>${dto.price}</td>
                                <td>${dto.quota}</td>
                                <td><img src="${dto.image}"/></td>
                                <td>${dto.dateImport}</td>
                                <c:if test="${not empty sessionScope.FULLNAME}">
                                    <td><input type="checkbox" name="chkAddTour" value="${dto.tourID}" /></td>
                                    </c:if>
                            </tr>
                        </c:forEach>
                        <c:if test="${not empty sessionScope.FULLNAME}">
                            <c:url var="url" value="search">
                                <c:param name="txtPageNum" value="${param.txtPageNum}"/>
                                <c:param name="txtFromDate" value="${param.txtFromDate}" /><br/>
                                <c:param name="txtToDate" value="${param.txtToDate}" /><br/>
                                <c:param name="txtFromPrice" value="${param.txtFromPrice}" /><br/>
                                <c:param name="txtToPrice" value="${param.txtToPrice}" /><br/>
                                <c:param name="txtPlace" value="${param.txtPlace}" />
                                <c:param value="Search" name="btnAction" />
                            </c:url>
                            <input type="hidden" name="txtSearchAgain" value="${url}" />
                            <input type="submit" value="Add To Cart" />
                        </c:if>
                    </form>
                </tbody>
            </table>
        </c:if>
    </c:if>
    <c:if test="${empty sessionScope.FULLNAME}">
        <p>Please Login <a href="login.html">here</a> to purchase travel!</p>
    </c:if>


</body>
</html>