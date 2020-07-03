<%-- 
    Document   : signup
    Created on : May 25, 2020, 11:41:15 AM
    Author     : Ninh Giang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Signup Page</title>
    </head>
    <body>
        <h1>Sign Up</h1>
        <c:set var="errors" value="${requestScope.ERRORS}"/>
        <form action="DispatcherController" method="POST">
            Username* <input type="text" name="txtEmail" value="${param.txtEmail}" required placeholder="username as email"/>(6 - 30 chars)<br/>
            <c:if test="${not empty errors.emailOutofRange}">
                <font color="red">
                ${errors.emailOutofRange}
                </font><br/>
            </c:if>
                <c:if test="${not empty errors.invalidEmailAddress}">
                <font color="red">
                ${errors.invalidEmailAddress}
                </font><br/>
            </c:if>
                Password* <input type="password" name="txtPassword" value="" required placeholder="password" />(6 - 20 chars)<br/>
            <c:if test="${not empty errors.passwordOutofRange}">
                <font color="red">
                ${errors.passwordOutofRange}
                </font><br/>
            </c:if>
            Confirm Password* <input type="password" name="txtConfirm" value="" /><br/>
            <c:if test="${not empty errors.confirmUnmatched}">
                <font color="red">
                ${errors.confirmUnmatched}
                </font><br/>
            </c:if>
                Full Name* <input type="text" name="txtFullname" value="${param.txtFullname}" required placeholder="full name" />(2 - 50 chars)<br/>
            <c:if test="${not empty errors.fullnameOutofRange}">
                <font color="red">
                ${errors.fullnameOutofRange}
                </font><br/>
            </c:if>
            <input type="submit" value="Signup" name="btnAction" />
            <input type="reset" value="Reset" />
        </form>
        <c:if test="${not empty errors.dupUsername}">
            <font color="red">
            ${errors.dupUsername}
            </font><br/>
        </c:if>
    </body>
</html>
