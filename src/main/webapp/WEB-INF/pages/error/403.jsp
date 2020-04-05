<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>403</title>
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet"/>
<body>

<section id="container" >
    <div class="row margin-top-10">
        <div class="col-lg-6 col-lg-offset-3">
            <div class="lock-screen">
                <h1><span class="color">Access denied</span><br><span class="color">Sorry, but you don't have enough rights :(</span><br/></h1>
                    <sec:authorize access="hasRole('ROLE_DOCTOR')">
                        <h3><a href="<c:url value="${contextPath}/patient/list"/>">Back to ----> the list of patients</a></h3>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_NURSE')">
                        <h3><a href="<c:url value="${contextPath}/event/list/all"/>">Back to ----> the list of events</a></h3>
                    </sec:authorize>
            </div>
        </div>
    </div>
</section>

<script  src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script  src="${contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>