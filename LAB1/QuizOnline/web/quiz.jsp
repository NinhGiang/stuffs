<%-- 
    Document   : quiz
    Created on : May 28, 2020, 1:49:42 PM
    Author     : Ninh Giang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz Page</title>
    </head>
    <body>
        <h1>Quiz Page</h1>
        <c:set var="isRole" value="${sessionScope.ROLE}"/>
        <c:if test="${empty sessionScope.FULLNAME}">
            <c:redirect url="login.html"/>
        </c:if>
        <c:if test="${isRole == "Student"}">
            <script>
                var varTimerInMiliseconds = ${requestScope.TIMENOW};
                setTimeout(function () {
                    let el = document.createElement("input");
                    el.type = "hidden";
                    el.name = "btnAction";
                    el.value = "Finish";
                    document.getElementById("myForm").appendChild(el);
                    document.getElementById("myForm").submit();
                }, varTimerInMiliseconds);
                var time = ${requestScope.TIMENOW};
                setInterval(function () {
                    time -= 10;
                    document.getElementById("countdown").innerHTML = time;
                    var minutes = Math.floor(time / 60000);
                    var seconds = Math.floor(time / 1000) - (minutes * 60);
                    document.getElementById("minute").innerHTML = minutes;
                    document.getElementById("second").innerHTML = seconds;

                }, 10);
                function getTime() {
                    document.getElementById("timenow").value = time;
                }
            </script>
            <font color="red">Welcome, ${sessionScope.FULLNAME}</font><br/>
            <p id="countdown">${requestScope.TIMENOW}</p>
            Time left: <p id="minute" style="margin: 0; display: inline;"></p> minutes
            <p id="second" style="margin: 0; display: inline;"></p> seconds
            <form id="myForm" action="DispatcherController">
                <c:set var="question" value="${requestScope.PAGINGRESULT}"/>
                <c:forEach var="dto" items="${question}" varStatus="counter">
                    <p>${dto.questionContent}</p>
                    <label for="answerA"><input id="answerA" type="radio" name="txtAnswer" value="${dto.optionA}" 
                                                <c:if test="${requestScope.CURRENTOPTION == dto.optionA}">
                                                    checked="checked"
                                                </c:if>
                                                >${dto.optionA}</label><br/>
                    <label for="answerB"><input id="answerB" type="radio" name="txtAnswer" value="${dto.optionB}" 
                                                <c:if test="${requestScope.CURRENTOPTION == dto.optionB}">
                                                    checked="checked"
                                                </c:if>
                                                >${dto.optionB}</label><br/>
                    <label for="answerC"><input id="answerC" type="radio" name="txtAnswer" value="${dto.optionC}" 
                                                <c:if test="${requestScope.CURRENTOPTION == dto.optionC}">
                                                    checked="checked"
                                                </c:if>
                                                >${dto.optionC}</label><br/>
                    <label for="answerD"><input id="answerD" type="radio" name="txtAnswer" value="${dto.optionD}" 
                                                <c:if test="${requestScope.CURRENTOPTION == dto.optionD}">
                                                    checked="checked"
                                                </c:if>
                                                >${dto.optionD}</label><br/>
                    <input type="hidden" name="questionCode" value="${dto.questionCode}"/>
                </c:forEach>
                <c:set var="size" value="${sessionScope.QUIZSIZE}"/>
                <input type="hidden" name="pageNum" value="${requestScope.CURRENTPAGE}" />
                <input type="hidden" name="cboSubjectCode" value="${param.cboSubjectCode}" />

                <c:if test="${requestScope.CURRENTPAGE != 1}">
                    <input type="submit" value="Previous" name="btnAction" onclick="getTime()"/>
                </c:if>
                <c:if test="${requestScope.CURRENTPAGE < size}">
                    <input type="submit" value="Next" name="btnAction" onclick="getTime()"/>
                </c:if>
                <br/>
                <input id="timenow" type="hidden" name="txtTimeNow" value="" />
                <input type="submit" value="Finish" name="btnAction" onclick="getTime()"/>
            </form>
        </c:if>
        <c:if test="${isRole == "Admin"}">
            <h3>Only Student can have access to the quiz!</h3>
        </c:if>
    </body>
</html>