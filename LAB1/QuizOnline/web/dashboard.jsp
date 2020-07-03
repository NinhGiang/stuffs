<%-- 
    Document   : dashboard
    Created on : May 23, 2020, 2:21:44 PM
    Author     : Ninh Giang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <c:if test="${empty sessionScope.FULLNAME}">
        <c:redirect url="login.html"/>
    </c:if>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard Page</title>
        <style>
            tbody th td {
                word-wrap: break-word;
                width: 150px;
            }
        </style>
    </head>
    <body>
        <h1>Dashboard</h1>
        <c:if test="${empty sessionScope.FULLNAME}">
            <c:redirect url="login.html"/>
        </c:if>
        <font color="red">Welcome, ${sessionScope.FULLNAME}</font>
        <c:set var="isRole" value="${sessionScope.ROLE}"/>
        <c:if test="${isRole == "Admin"}">
            <form action="DispatcherController">
                Search Value <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" required placeholder="search question"/>
                <input type="submit" value="Search" name="btnAction" /><br/>
            </form>
            <c:set var="searchSize" value="${requestScope.SEARCHSIZE}"/>
            <c:forEach var="pagingButton" begin="1" end="${searchSize}">
                <c:url var="urlRewriting" value="DispatcherController">
                    <c:param name="btnAction" value="Search"/>
                    <c:param name="pageNum" value="${pagingButton - 1}"/>
                    <c:param name="txtSearchValue" value="${param.txtSearchValue}"/>
                </c:url>
                <a href="${urlRewriting}">${pagingButton}</a>
            </c:forEach>
            <c:set var="searchValue" value="${param.txtSearchValue}"/>
            <c:if test="${not empty searchValue}">
                <c:set var="result" value="${requestScope.PAGINGRESULT}"/>
                <c:if test="${not empty result}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>Code</th>
                                <th>Question</th>
                                <th>Option A</th>
                                <th>Option B</th>
                                <th>Option C</th>
                                <th>Option D</th>
                                <th>Answer</th>
                                <th>Subject</th>
                                <th>Delete</th>
                                <th>Update</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${result}" varStatus="counter">
                            <form action="DispatcherController">
                                <tr>
                                    <td>${dto.questionCode}
                                        <input type="hidden" name="questionCode" value="${dto.questionCode}" />
                                    </td>

                                    <td>
                                        <input type="text" name="questionContent" value="${dto.questionContent}" />
                                    </td>

                                    <td>
                                        <input type="text" name="optionA" value="${dto.optionA}" />
                                    </td>

                                    <td>
                                        <input type="text" name="optionB" value="${dto.optionB}" />
                                    </td>

                                    <td>
                                        <input type="text" name="optionC" value="${dto.optionC}" />
                                    </td>

                                    <td>
                                        <input type="text" name="optionD" value="${dto.optionD}" />
                                    </td>

                                    <td>
                                        <select name="answerchoice">
                                            <option
                                                <c:if test="${dto.answer == dto.optionA}">
                                                    selected="selected"
                                                </c:if>
                                                >Option A</option>
                                            <option
                                                <c:if test="${dto.answer == dto.optionB}">
                                                    selected="selected"
                                                </c:if>
                                                >Option B</option>
                                            <option
                                                <c:if test="${dto.answer == dto.optionC}">
                                                    selected="selected"
                                                </c:if>
                                                >Option C</option>
                                            <option
                                                <c:if test="${dto.answer == dto.optionD}">
                                                    selected="selected"
                                                </c:if>
                                                >Option D</option>
                                        </select>
                                    </td>
                                    <td>
                                        <c:set var="subjectList" value="${sessionScope.SUBJECTLIST}"/>
                                        <select name="subjectCode">
                                            <c:forEach var="sbj" items="${subjectList}" varStatus="counter">
                                                <option 
                                                    <c:if test="${dto.subjectCode == sbj}">
                                                        selected="selected"
                                                    </c:if>
                                                    >${sbj}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                                        <input type="submit" value="Update" name="btnAction" />
                                    </td>
                                    <td>
                                        <c:if test="${not empty param.pageNum}">
                                            <input type="hidden" name="pageNum" value="${param.pageNum}" />
                                        </c:if>
                                        <input type="submit" value="Delete" name="btnAction" />
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </c:if>
        <form action="DispatcherController">
            <a href="addquestion.jsp">Click here to add more questions</a><br/>
            <input type="submit" value="Logout" name="btnAcion" />
        </form>
    </c:if>
    <c:if test="${isRole == "Student"}">
        <form action="DispatcherController">
            Select Subject to take a quiz: <select name="cboSubjectCode">
                <c:set var="subjectList" value="${sessionScope.SUBJECTLIST}"/>
                <c:forEach var="sbj" items="${subjectList}" varStatus="counter">
                    <option>${sbj}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Start" name="btnAction" />
        </form>
        <a href="DispatcherController?btnAction=ViewHistory">Click here to view quiz history.</a>
        <form action="DispatcherController">
            <input type="submit" value="Logout" name="btnAction" />
        </form>
    </c:if>
</body>
</html>