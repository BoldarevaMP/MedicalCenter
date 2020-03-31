<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Confirmation Page</title>
    <link href="<c:url value='/resources/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
</head>
<body>
<div class="well">
    <sec:authorize access="hasRole('ROLE_NURSE') or hasRole('ROLE_DOCTOR')">
        <h4 style="display: inline-block; padding-left: 950px">Hi ${pageContext.request.userPrincipal.name}  <a href="<c:url value="${contextPath}/logout"/>" class="btn btn-danger custom-width">Sign Out</a></h4>
    </sec:authorize>
</div>
<div class="generic-container">
    <div class="alert alert-success lead">
        ${success}
    </div>
    <button class="btn"> <a href="<c:url value='/event/list/all' />">Go to Events List</a></button>
</div>
</body>

</html>