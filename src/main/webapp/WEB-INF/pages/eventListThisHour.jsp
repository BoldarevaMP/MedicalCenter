<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<html>

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Events List</title>
    <link href="<c:url value='/resources/css/bootstrap.css' />" rel="stylesheet">
    <link href="<c:url value='/resources/css/app.css' />" rel="stylesheet">
</head>

<body>
<div class="generic-container">
    <div class="well">
        <button class="btn"><a href="<c:url value="/event/list/all"/>">All Events</button>
        <button class="btn"><a href="<c:url value="/event/list/today"/>">Today Events</a></button>
        <button class="btn"><a href="<c:url value="/event/list/thishour"/>">This Hour Events</a></button>
    </div>
    <div class="panel panel-default">
        <form method="GET" action="${contextPath}/event/patientName" class="form--horizontal ">
            <div class="well">
                <table>
                    <tr>
                        <td width="200">
                            <input name="lastName" class="form-control " placeHolder="Patient Last Name"/>
                        </td>
                        <td width="10"></td>
                        <td width="200">
                            <button id="button" class="btn btn-primary" type="submit">Search</button>
                        </td>
                    </tr>
                </table>
            </div>
        </form>




        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Events </span></div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Date and Time</th>
                <th>Patient</th>
                <th>Treatment</th>
                <th>Dosage</th>
                <th>Status</th>
                <th >Comment</th>
                <th width="100"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${events}" var="event">
                <tr>
                    <td>${event.date}</td>
                    <td>${event.appointmentDTO.patientDTO.lastName} ${event.appointmentDTO.patientDTO.firstName}</td>
                    <td>${event.appointmentDTO.treatmentDTO.name}</td>
                    <td>${event.appointmentDTO.dosage} ${event.appointmentDTO.treatmentDTO.dosageForm}</td>
                    <td>${event.status}</td>
                    <td>${event.comment}</td>
                    <td>
                        <c:choose>
                            <c:when test="${event.status =='PLANNED'}">
                                <a href="<c:url value="/event/edit-event-${event.id}" />" class=" btn btn-success custom-width">edit</a>
                            </c:when>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>
</body>
</html>