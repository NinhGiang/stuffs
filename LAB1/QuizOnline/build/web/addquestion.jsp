<%-- 
    Document   : addquestion
    Created on : May 27, 2020, 8:34:46 PM
    Author     : Ninh Giang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Question Page</title>
    </head>
    <body>
        <h1>Add Question Page</h1>
        <c:set var="isRole" value="${sessionScope.ROLE}"/>
        <c:if test="${empty sessionScope.FULLNAME}">
            <c:redirect url="login.html"/>
        </c:if>
        <c:if test="${isRole == "Admin"}">
            <c:set var="subjectList" value="${sessionScope.SUBJECTLIST}"/>
            <form action="DispatcherController">
                Question Content: <input type="text" name="txtQuestionContent" value="" required placeholder="input question"/><br/>
                Option A: <input type="text" name="txtOptionA" value="" required placeholder="option A" /><br/>
                Option B: <input type="text" name="txtOptionB" value="" required placeholder="option B" /><br/>
                Option C: <input type="text" name="txtOptionC" value="" required placeholder="option C" /><br/>
                Option D: <input type="text" name="txtOptionD" value="" required placeholder="option D" /><br/>
                Answer: <select name="cboAnswer">
                    <option>Option A</option>
                    <option>Option B</option>
                    <option>Option C</option>
                    <option>Option D</option>
                </select><br/>
                Subject Code: <select name="cboSubject">
                    <c:forEach var="sbj" items="${subjectList}" varStatus="counter">
                        <option>${sbj}</option>
                    </c:forEach>
                </select><br/>
                <input type="submit" value="Add" name="btnAction" />
                <input type="reset" value="Reset" />
            </form>
        </c:if>
        <c:if test="${isRole == "Student"}">
            <h3>Only Admin can add question!</h3>
        </c:if>
    </body>
</html>
