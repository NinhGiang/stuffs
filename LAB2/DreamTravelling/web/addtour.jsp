<%-- 
    Document   : addtour
    Created on : Jun 21, 2020, 9:29:35 PM
    Author     : Ninh Giang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Tour Page</title>
    </head>
    <body>
        <h1>Insert Tour Page</h1>
        <c:set var="errors" value="${requestScope.ERRORS}"/>
        <form action="insert" method="POST">
            Tour name: <input type="text" name="txtTourName" value="${param.txtTourName}" required />(maximum 250 chars)<br/>
            <c:if test="${not empty errors.tourNameOutofRange}">
                <font color="red">
                ${errors.tourNameOutofRange}
                </font><br/>
            </c:if>
            Arrive from: <input type="text" name="txtFromPlace" value="" required />(maximum 50 chars)<br/>
            <c:if test="${not empty errors.fromPlaceOutofRange}">
                <font color="red">
                ${errors.fromPlaceOutofRange}
                </font><br/>
            </c:if>
            Destination: <input type="text" name="txtToPlace" value="" required />(maximum 50 chars)<br/>
            <c:if test="${not empty errors.toPlaceOutofRange}">
                <font color="red">
                ${errors.toPlaceOutofRange}
                </font><br/>
            </c:if>
            Start from: <input type="date" name="txtFromDate" value="${param.txtFromDate}" required /><br/>
            End on: <input type="date" name="txtToDate" value="${param.txtToDate}" required /><br/>
            <c:if test="${not empty errors.toBeforeFrom}">
                <font color="red">
                ${errors.toBeforeFrom}
                </font><br/>
            </c:if>
            Price: <input type="text" name="txtPrice" value="${param.txtPrice}" required /><br/>
            <c:if test="${not empty errors.invalidPriceType}">
                <font color="red">
                ${errors.invalidPriceType}
                </font><br/>
            </c:if>
            Quota: <select name="txtQuota" required>
                <c:forEach var="num" begin="1" end="30">
                    <option>${num}</option>
                </c:forEach>
            </select>
            Image Link: <input type="text" name="txtImage" value="${param.txtImage}" /><br/>
            <input type="submit" value="Insert" name="btnAction" />
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>
