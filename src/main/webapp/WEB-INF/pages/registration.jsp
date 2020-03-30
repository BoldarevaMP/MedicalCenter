<%@ page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Create an account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

</head>

<body>

<div class="container">

    <form:form method="POST" modelAttribute="userForm" class="form-signin" onsubmit="return(validate());">
        <h2 class="form-signin-heading">Create your account</h2>

        <spring:bind path="firstName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input id="FirstName" type="text" path="firstName" class="form-control" placeholder="First Name"
                            autofocus="true"></form:input>
                <form:errors id="FirstNameVal" path="firstName"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="lastName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input id="LastName" type="text" path="lastName" class="form-control"
                            placeholder="Last Name"></form:input>
                <form:errors path="lastName"></form:errors>
                <span id="LastNameVal"></span>
            </div>
        </spring:bind>
        <spring:bind path="email">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input id="Email" type="text" path="email" class="form-control" placeholder="Email"></form:input>
                <form:errors path="email"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" id="Password" path="password" class="form-control" placeholder="Password"></form:input>
                <form:errors path="password"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="confirmPassword">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" id="ConfirmPassword" path="confirmPassword" class="form-control"
                            placeholder="Confirm your password"></form:input>
                <form:errors path="confirmPassword"></form:errors>
            </div>
        </spring:bind>


        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>

</div>
<!-- /container -->
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript" src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript">
    function validate() {
        let firstName = document.getElementById("FirstName").value;
        let lastName = document.getElementById("LastName").value;
        let email = document.getElementById("Email").value;
        let password = document.getElementById("Password").value;
        let confirmPassword = document.getElementById("ConfirmPassword").value;

        var regexp = "^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$";

        if (firstName === "" || firstName.length < 1) {
            swal("Please enter your 'firstname', length must be over 1 symbol");
            return false;
        }
        if (lastName === "" || lastName.length < 1) {
            swal("Please enter your 'lastname', length must be over 1 symbol");
            return false;
        }
        if(email.length < 5 || regexp.test(String(email).toLowerCase())){
            swal("Invalid form email");
            return false;
        }
        if (password === "" || password.length < 8) {
            swal("Please enter your 'password', length must be over 4 symbol");
            return false;
        }
        if (confirmPassword === "" || confirmPassword.length < 8) {
            swal("Please enter your 'confirm password', length must be over 4 symbol");
            return false;
        }
        if (password !== confirmPassword) {
            swal("Password doesn't match");
            return false;
        }
    }
</script>

</body>
