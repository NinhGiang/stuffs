<%-- 
    Document   : PendingRequest
    Created on : Jul 15, 2020, 2:14:17 PM
    Author     : Ninh Giang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pending Request Page</title>
    </head>
    <body>
        <h1>Your Pending Request</h1>


        <s:if test="%{#session['FULLNAME'] != null}">
            <font color="red">
            Welcome, <s:property value="%{#session['FULLNAME']}"/>
            </font>
            <s:form action="logout">
                <s:submit value="Logout"/>
            </s:form>
            <s:if test="%{#session['ROLE'].equals('Employee') || #session['ROLE'].equals('Administrator')}">
                <s:set var="errors" value="%{session['ERRORS']}"/>
                <s:if test="%{#session['CART'] != null}">
                    <s:if test="%{#session['CART'].resourceInCart != null}">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Resource ID</th>
                                    <th>Resource Name</th>
                                    <th>Quantity</th>
                                    <th>Remove</th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:form action="updatecart" theme="simple">
                                    <s:iterator value="%{#session['CART'].resourceInCart}" status="counter">
                                        <tr>
                                            <td><s:property value="%{#counter.count}"/></td>
                                            <td>
                                                <s:property value="%{resourceID}"/>
                                                <s:hidden name="resID" value="%{resourceID}"/>
                                            </td>
                                            <td><s:property value="%{resourceName}"/>
                                            </td>
                                            <td>
                                                <select name="newQuantity">
                                                    <s:iterator var="b" begin="1" end="%{currentquantity}">
                                                        <option
                                                            <s:if test="%{quantity == #b}">
                                                                selected="selected"
                                                            </s:if>
                                                            ><s:property value="%{#b}"/></option>
                                                    </s:iterator>
                                                </select>
                                            </td>
                                            <td>
                                                <s:checkbox name="checkDelete" fieldValue="%{resourceID}" value="" />
                                            </td>
                                        </tr>
                                    </s:iterator>
                                    <tr>
                                        <th colspan="3">
                                            <a href="dashboard.jsp">Click here to go to dashboard!</a>
                                        </th>
                                        <th colspan="2">
                                            <s:submit value="Update"/>
                                        </th>
                                    </tr>
                                </s:form>
                            </tbody>
                        </table>
                        <s:if test="%{#request['ERROR'] != null}">
                            <s:property value="%{#request['ERROR']}"/>
                        </s:if>
                        <s:form action="sendreq">
                            <s:submit value="Confirm and Send"/>
                        </s:form>
                        <br/>
                    </s:if>
                </s:if>
                <s:if test="%{#session['CART'] == null || cart.resourceInCart == null}">
                    <h3>Empty cart!</h3>
                    <a href="dashboard.jsp">Click here to go to dashboard!</a>
                </s:if>
            </s:if>
            <s:else>
                <s:url action="loadreq" id="loadURL">
                    <s:param name="pageNum" value="1"/>
                </s:url>
                <s:a href="%{loadURL}">Handle Requests Here</s:a>
            </s:else>
        </s:if>
        <s:else>
            <a href="login.html">Please login to continue!</a>
        </s:else>

    </body>
</html>
