<%-- 
    Document   : register
    Created on : Jul 20, 2020, 2:27:38 PM
    Author     : Ninh Giang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <h1>Register Account</h1>
        <s:form action="register" method="POST">
            <s:textfield name="userID" label="Email" pattern="^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$"
                         placeholder="input email" required="required" requiredLabel="true" title="You must input an email address"/>
            <s:if test="%{#request['ERRORS'].dupEmail != null}">
                <font color="red"><s:property value="%{#request['ERRORS'].dupEmail}"/></font><br/>
            </s:if>
            <s:textfield type="password" name="password" pattern=".{6,20}" label="Password" placeholder="input password (6-20 chars)" 
                         required="required" requiredLabel="true" title="Password must be from 6 to 20 chars"/>
            <s:textfield type= "password" name="confirm" pattern=".{6,20}" label="Confirm Password" required="required" 
                         requiredLabel="true"/>
            <s:if test="%{#request['ERRORS'].confirmUnmatched != null}">
                <font color="red"><s:property value="%{#request['ERRORS'].confirmUnmatched}"/></font><br/>
            </s:if>
            <s:textfield name="fullname" pattern="{2,50}$" label="Full Name" placeholder="input fullname (2-50 chars)" required="required" 
                         requiredLabel="true" title="You must input from 2 to 50 chars"/>
            <s:textfield name="phone" pattern="[0-9]{7,14}$" label="Phone Number" placeholder="input phone number (7-14 digits)" title="You must input a phone number"/>
            <s:textfield name="address" label="Address" placeholder="input address"/>
            <s:submit name="Submit"/>
        </s:form>
    </body>
    <!--
    ^         # Assert position at the beginning of the string.
    \+        # Match a literal "+" character.
    (?:       # Group but don't capture:
      [0-9]   #   Match a digit.
      \x20    #   Match a space character
        ?     #     between zero and one time.
    )         # End the noncapturing group.
      {6,14}  #   Repeat the group between 6 and 14 times.
    [0-9]     # Match a digit.
    $         # Assert position at the end of the string.
    -->
</html>

