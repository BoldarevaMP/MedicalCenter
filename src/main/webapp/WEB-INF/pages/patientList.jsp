<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
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
<div class="generic-container">
    <div class="well">
        <button class="btn"><a href="<c:url value="/patient/addPatient"/>">Add New Patient</a></button>
    </div>
    <div class="panel panel-default">

        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Patients </span></div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Name</th>
                <th>Start Date</th>
                <th>Diagnosis</th>
                <th>Status</th>
                <th>Doctor</th>
                <th width="100"></th>

            </tr>
            </thead>
            <tbody>
            <c:forEach items="${patients}" var="patient">
                <tr>
                    <td>${patient.lastName} ${patient.firstName}</td>
                    <td>${patient.startDate}</td>
                    <td>${patient.diagnosis}</td>
                    <td>${patient.status}</td>
                    <td>${patient.doctorDTO.lastName}</td>
                    <td><a href="<c:url value="/patient/edit-patient-${patient.id}" />" class="btn btn-success custom-width">edit</a></td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>
</body>
</html>