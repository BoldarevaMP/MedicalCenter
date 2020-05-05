<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Edit Patient</title>
    <style>
        body {
            background-image: url("${contextPath}/resources/images/11.jpg");
        }
    </style>
    <link href="<c:url value='/resources/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>

</head>
<body>
<div class="generic-container">
    <sec:authorize access="hasRole('ROLE_DOCTOR')">
        <h4 style="text-align: right;">Doctor ${pageContext.request.userPrincipal.name}
            <a href="<c:url value="/logout"/>" class="btn btn-danger custom-width">Sign Out</a></h4>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_NURSE')">
        <h4 style="text-align: right;">Nurse ${pageContext.request.userPrincipal.name}
            <a href="<c:url value="/logout"/>" class="btn btn-danger custom-width">Sign Out</a></h4>
    </sec:authorize>
</div>
<div class="generic-container ">
    <div class="well">
        <button class="btn"><a href="<c:url value="/patient/list"/>">All Patients</a></button>
    </div>

    <div class="well">

        <table>
            <tr>
                <td width="200">${patient.lastName} ${patient.firstName}</td>
                <td width="200">${patient.startDate}</td>
                <td width="300">${patient.healthInsurance}</td>
                <td width="200">${patient.diagnosis}</td>
                <td width="200">${patient.doctorDTO.lastName}</td>
            </tr>
        </table>
    </div>
    <!-- Default panel contents -->
    <div class="panel-heading"><span class="lead">List of Appointments </span></div>
    <div class="well">
        <a href="<c:url value="/patient/add-appointment" />" class="btn btn-primary">Add New Appointment</a>
    </div>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Treatment</th>
            <th>Dosage</th>
            <th>Day</th>
            <th>Time</th>
            <th width="100"></th>
            <th width="100"></th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${appointments}" var="appointment">
            <tr>
                <td>${appointment.startDate}</td>
                <td>${appointment.endDate}</td>
                <td>${appointment.treatmentDTO.name}</td>
                <td>${appointment.dosage} ${appointment.treatmentDTO.dosageForm}</td>
                <td>
                    <c:forEach items="${appointment.days}" var="day">
                        ${day}
                    </c:forEach>
                </td>
                <td>
                    <c:forEach items="${appointment.time}" var="time">
                        ${time.toString()}
                    </c:forEach>
                </td>
                <td><a href="<c:url value="/patient/edit-appointment-${appointment.id}" />"
                       class="btn btn-success custom-width">edit</a></td>
                <td>
                    <form id="DeleteForm${appointment.id}" onclick="checkConfirmForm(${appointment.id},event)"
                          action="${contextPath}/patient/delete-appointment-${appointment.id}">
                        <a href="<c:url value="/patient/delete-appointment-${appointment.id}"/>" type="submit"
                           class="btn btn-danger custom-width">delete</a>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/sweetalert.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript">
    function checkConfirmForm(id, e) {
        e.preventDefault();
        swal({
            title: "Are you sure?",
            text: "All Events will be cancelled!",
            icon: "warning",
            buttons: [
                'No, cancel it!',
                'Yes, I am sure!'
            ],
            dangerMode: true,
        }).then(function (isConfirm) {
            if (isConfirm) {
                swal({
                    title: 'Deleted!',
                    icon: 'success'
                });
                $('#DeleteForm' + id).submit();
            } else {
                swal("Cancelled", "", "error");
            }
        })
    }
</script>
</body>
</html>
