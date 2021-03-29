<%-- 
    Document   : confirmmail
    Created on : Jul 21, 2020, 12:16:09 AM
    Author     : Ninh Giang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Page</title>
    </head>
    <body>
        <h1>Confirm Page</h1>
        <p>We have send the verification code to your email </p>
        <s:form action="verifyacc" method="POST">
            <s:property value="%{userID}"/>
            <s:hidden name="code" value="%{code}"/>
            <s:hidden name="userID" value="%{userID}"/>
            <s:textfield name="txtConfirm" label="Verification Code" required="required"/>
            <s:submit value="Verify"/>
        </s:form>
        <s:if test="%{#request['CONFIRM'] != null}">
            <s:property value="%{#request['CONFIRM']}"/>
        </s:if>
        <br/>
        <a href="login.html">Back to login page here</a>
    </body>
</html>