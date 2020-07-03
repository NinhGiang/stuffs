<%-- 
    Document   : history
    Created on : Jun 2, 2020, 3:42:09 PM
    Author     : Ninh Giang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Page</title>
    </head>
    <body>
        <h1>Your Quiz History</h1>
        <c:set var="isRole" value="${sessionScope.ROLE}"/>
        <c:if test="${empty sessionScope.FULLNAME}">
            <c:redirect url="login.html"/>
        </c:if>
        <c:if test="${isRole == "Student"}">
            <c:set var="searchSize" value="${requestScope.SEARCHSIZE}"/>
            <c:forEach var="pagingButton" begin="1" end="${searchSize}">
                <c:url var="urlRewriting" value="DispatcherController">
                    <c:param name="btnAction" value="ViewHistory"/>
                    <c:param name="pageNum" value="${pagingButton - 1}"/>
                </c:url>
                <a href="${urlRewriting}">${pagingButton}</a>
            </c:forEach>
            <c:set var="result" value="${requestScope.PAGINGRESULT}"/>
            <c:if test="${empty result}">
                <h3>You haven't taken any quiz yet!</h3>
            </c:if>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>Code</th>
                            <th>Quantity</th>
                            <th>Correct Answers</th>
                            <th>Points</th>
                            <th>Subject Code</th>
                            <th>Taken Time</th>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                            <tr>
                                <td>${dto.quizCode}</td>
                                <td>${dto.quantity}</td>
                                <td>${dto.correctAnswers}</td>
                                <td>${dto.point}</td>
                                <td>${dto.subjectCode}</td>
                                <td>${dto.takenTime}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </c:if>
        <c:if test="${isRole == "Admin"}">
            <h3>Only Student can have access to the quiz!</h3>
        </c:if>
        <a href="dashboard.jsp">Return to dashboard page</a>
    </body>
</html>
