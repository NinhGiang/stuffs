<%-- 
    Document   : result
    Created on : May 31, 2020, 3:43:26 PM
    Author     : Ninh Giang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result Page</title>
    </head>
    <body>
        <h1>Quiz Result</h1>
        <c:set var="quizResult" value="${requestScope.QUIZRESULT}"/>
        <c:set var="numofCorrectAnswers" value="${requestScope.NUMOFCORRECTANSWER}"/>
        <c:set var="totalQuestions" value="${requestScope.QUANTITY}"/>
        <p>Quiz result is ${quizResult}</p>
        <p>Correct answers: ${numofCorrectAnswers}/${totalQuestions}</p>
        <a href="dashboard.jsp">Return to dashboard page</a>
    </body>
</html>
