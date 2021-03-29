<%-- 
    Document   : request
    Created on : Jul 13, 2020, 9:53:14 AM
    Author     : Ninh Giang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Request Page</title>
    </head>
    <body>
        <h1>Request Page</h1>
        <s:if test="%{#session['FULLNAME'] != null}">
            <font color="red">
            Welcome, <s:property value="%{#session['FULLNAME']}"/>
            </font>
            <s:form action="logout">
                <s:submit value="Logout"/>
            </s:form>
            <%--THIS IS FOR EMPLOYEE ROLE--%>
            <%--Search by name or request dates--%>
            <s:if test="%{#session['ROLE'].equals('Manager')}">
                <s:form action="searchreq">
                    <s:hidden name="pageNum" value="1"/>
                    <s:textfield name="txtUserName" label="User"/>
                    <s:textfield name="txtResName" label="Resource Name"/>
                    From date: <input type="date" name="dateFrom" value="${param.dateFrom}"/><br/>
                    To date: <input type="date" name="dateTo" value="${param.dateTo}" /><br/>
                    Status: <select name="txtStatus">
                        <option
                            <s:if test="%{txtStatus.equals('')}">
                                selected="selected"
                            </s:if>
                            >All</option>
                        <option
                            <s:if test="%{txtStatus.equals('New')}">
                                selected="selected"
                            </s:if>
                            >New</option>
                        <option
                            <s:if test="%{txtStatus.equals('Accept')}">
                                selected="selected"
                            </s:if>
                            >Accept</option>
                        <option
                            <s:if test="%{txtStatus.equals('Decline')}">
                                selected="selected"
                            </s:if>
                            >Decline</option>
                    </select>
                    <s:submit value="Search"/>
                </s:form>
                <s:if test="%{PAGEQUANTITY != null}">
                    <s:iterator var="pagingBtn" begin="1" end="%{PAGEQUANTITY}">
                        <s:url action="searchreq" id="searchURL">
                            <s:param name="pageNum" value="%{pagingBtn}"/>
                            <s:param name="txtUserName" value="%{txtUserName}"/>
                            <s:param name="txtResName" value="%{txtResName}"/>
                            <s:param name="dateFrom" value="%{dateFrom}"/>
                            <s:param name="dateTo" value="%{dateTo}"/>
                            <s:param name="txtStatus" value="%{txtStatus}"/>
                        </s:url>
                        <s:a href="%{searchURL}"><s:property value="%{#pagingBtn}"/></s:a>
                    </s:iterator>
                </s:if>
                <%--<s:if test="%{!(txtCategory.isEmpty() && txtName.isEmpty() && dateFrom.isEmpty() && dateTo.isEmpty())}">--%>
                <s:if test="%{list != null}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>RequestID</th>
                                <th>UserID</th>
                                <th>Username</th>
                                <th>Date</th>
                                <th>Resources</th>
                                <th>Status</th>
                                <th>Update</th>
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="list" status="counter">
                                <s:form action="handlereq" theme="simple">
                                    <tr>
                                        <td>
                                            <s:property value="%{#counter.count}"/>
                                        </td>
                                        <td>
                                            <s:property value="%{requestID}"/>
                                            <s:set var="r" value="%{requestID}"/>
                                        </td>
                                        <td>
                                            <s:property value="%{userID}"/>
                                        </td>
                                        <td>
                                            <s:property value="%{fullname}"/>
                                        </td>
                                        <td>
                                            <s:property value="%{date}"/>
                                        </td>
                                        <td>
                                            <s:iterator value="reslist">
                                                <s:if test="%{requestID == #r}">
                                                    Res: <s:property value="%{resourceName}"/>
                                                    Quan: <s:property value="%{quantity}"/><br/>
                                                </s:if>
                                            </s:iterator>
                                        </td>
                                        <td>
                                            <%--<s:property value="%{status}"/>--%>
                                            <select name="status">
                                                <option
                                                    <s:if test="%{status.equals('New')}">
                                                        selected="selected"
                                                    </s:if>
                                                    >New</option>
                                                <option
                                                    <s:if test="%{status.equals('Accept')}">
                                                        selected="selected"
                                                    </s:if>
                                                    >Accept</option>
                                                <option
                                                    <s:if test="%{status.equals('Decline')}">
                                                        selected="selected"
                                                    </s:if>
                                                    >Decline</option>
                                            </select>

                                        </td>
                                        <td>
                                            <s:hidden name="requestID" value="%{requestID}"/>
                                            <s:hidden name="pageNum" value="%{pageNum}"/>
                                            <s:hidden name="txtUserName" value="%{txtUserName}"/>
                                            <s:hidden name="txtResName" value="%{txtResName}"/>
                                            <s:hidden name="dateFrom" value="%{dateFrom}"/>
                                            <s:hidden name="dateTo" value="%{dateTo}"/>
                                            <s:hidden name="txtStatus" value="%{txtStatus}"/>
                                            <s:submit value="Update Status"/>
                                        </td>
                                    </tr>
                                </s:form>
                            </s:iterator>
                        </tbody>
                    </table>
                </s:if>
                <s:else>
                    <p>No record is matched!!!</p>
                </s:else>
            </s:if>
            <%--</s:if>--%>
            <s:else>
                <p>Only Manager Can Handle Requests.</p>
                <a href="dashboard.jsp">Back to Dashboard Here</a>
            </s:else>
        </s:if>
        <s:else>
            <a href="login.html">Please login to continue!</a>
        </s:else>
    </body>
</html>
