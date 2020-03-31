<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Patients List</title>
    <link href="<c:url value='/resources/css/bootstrap.css' />" rel="stylesheet">
    <link href="<c:url value='/resources/css/app.css' />" rel="stylesheet">
</head>

<body>
<div class="well">
    <sec:authorize access="hasRole('ROLE_NURSE') or hasRole('ROLE_DOCTOR')">
        <h4 style="display: inline-block; padding-left: 950px">Hi ${pageContext.request.userPrincipal.name}  <a href="<c:url value="${contextPath}/logout"/>" class="btn btn-danger custom-width">Sign Out</a></h4>
    </sec:authorize>
</div>
<div class="generic-container">
    <!-- Default panel contents -->
    <div class="panel-heading"><span class="lead">List of Patients </span></div>
    <div class="well">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Name</th>
                <th>Health Insurance</th>
                <th>Start Date</th>
                <th>Diagnosis</th>
                <th>Status</th>
                <th></th>
                <th width="100"></th>

            </tr>
            </thead>
            <tbody>
            <c:forEach items="${patients}" var="patient">
                <tr>
                    <td>${patient.lastName} ${patient.firstName}</td>
                    <td>${patient.healthInsurance}</td>
                    <td>${patient.startDate}</td>
                    <td>${patient.diagnosis}</td>
                    <td>${patient.status}</td>
                    <td><a href="<c:url value="/event/patient-${patient.id}" />" class="btn btn-success custom-width">Events</a>
                    </td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</div>
</body>
</html>