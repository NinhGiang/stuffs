<%-- 
    Document   : dashboard
    Created on : Jul 8, 2020, 8:09:59 PM
    Author     : Ninh Giang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard Page</title>
    </head>
    <body>
        <h1>Dashboard Page</h1>
        <s:if test="%{#session['FULLNAME'] != null}">
            <font color="red">
            Welcome, <s:property value="%{#session['FULLNAME']}"/>
            </font>
            <s:form action="logout">
                <s:submit value="Logout"/>
            </s:form>
            <%--THIS IS FOR EMPLOYEE ROLE--%>
            <%--Search by category, name and using dates--%>
            <s:if test="%{#session['ROLE'].equals('Employee') || #session['ROLE'].equals('Administrator')}">
                <s:form action="search">
                    <s:hidden name="pageNum" value="1"/>
                    <s:textfield name="txtCategory" label="Category"/>
                    <s:textfield name="txtName" label="Name"/>
                    From date: <input type="date" name="dateFrom" value="${param.dateFrom}"/><br/>
                    To date: <input type="date" name="dateTo" value="${param.dateTo}" /><br/>
                    <s:submit value="Search"/>
                </s:form>
                <s:a href="bookrequest.jsp">View and Confirm Your Request</s:a><br/>
                <s:a href="loadhistory">View Your Request History</s:a><br/>
                <s:if test="%{PAGEQUANTITY != null}">
                    <s:iterator var="pagingBtn" begin="1" end="%{PAGEQUANTITY}">
                        <s:url action="search" id="searchURL">
                            <s:param name="pageNum" value="%{pagingBtn}"/>
                            <s:param name="txtCategory" value="%{txtCategory}"/>
                            <s:param name="txtName" value="%{txtName}"/>
                            <s:param name="dateFrom" value="%{dateFrom}"/>
                            <s:param name="dateTo" value="%{dateTo}"/>
                        </s:url>
                        <s:a href="%{searchURL}"><s:property value="%{#pagingBtn}"/></s:a>
                    </s:iterator>
                </s:if>
                <s:if test="%{!(txtCategory.isEmpty() && txtName.isEmpty() && dateFrom.isEmpty() && dateTo.isEmpty())}">
                    <s:if test="%{list != null}">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>ResourceID</th>
                                    <th>Resource Name</th>
                                    <th>Category</th>
                                    <th>From date</th>
                                    <th>To Date</th>
                                    <th>Color</th>
                                    <th>Quantity</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="list" status="counter">
                                    <s:form action="book" theme="simple">
                                        <tr>
                                            <td>
                                                <s:property value="%{#counter.count}"/>
                                            </td>
                                            <td>
                                                <s:property value="%{resourceID}"/>
                                            </td>
                                            <td>
                                                <s:property value="%{name}"/>
                                            </td>
                                            <td>
                                                <s:property value="%{category}"/>
                                            </td>
                                            <td>
                                                <s:property value="%{fromDate}"/>
                                            </td>
                                            <td>
                                                <s:property value="%{toDate}"/>
                                            </td>
                                            <td>
                                                <s:property value="%{color}"/>
                                            </td>
                                            <td>
                                                <s:property value="%{quantity}"/>
                                            </td>
                                            <td>
                                                <s:hidden name="txtResourceID" value="%{resourceID}"/>
                                                <s:hidden name="txtResourceName" value="%{name}"/>
                                                <s:hidden name="txtQuantity" value="1"/>
                                                <s:hidden name="pageNum" value="%{pageNum}"/>
                                                <s:hidden name="txtCategory" value="%{txtCategory}"/>
                                                <s:hidden name="txtName" value="%{txtName}"/>
                                                <s:hidden name="dateFrom" value="%{dateFrom}"/>
                                                <s:hidden name="dateTo" value="%{dateTo}"/>
                                                <s:submit value="Book"/>
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
            </s:if>
            <s:if test="%{#session['ROLE'].equals('Manager')}">
                <s:url action="loadreq" id="loadURL">
                    <s:param name="pageNum" value="1"/>
                </s:url>
                <s:a href="%{loadURL}">Handle Requests Here</s:a>
            </s:if>
        </s:if>
        <s:else>
            <a href="login.html">Please login to continue!</a>
        </s:else>
    </body>
</html>
