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
    <title>Events List</title>
    <link href="<c:url value='/resources/css/bootstrap.css' />" rel="stylesheet">
    <link href="<c:url value='/resources/css/app.css' />" rel="stylesheet">
</head>

<body>
<div class="well-sm">
    <sec:authorize access="hasRole('ROLE_NURSE') or hasRole('ROLE_DOCTOR')">
        <h4 style="display: inline-block; padding-left: 950px">Hi ${pageContext.request.userPrincipal.name}  <a href="<c:url value="/logout"/>" class="btn btn-danger custom-width">Sign Out</a></h4>
    </sec:authorize>
</div>
<div class="generic-container">
    <div class="well">
        <button class="btn"><a href="<c:url value="/event/list/all"/>">All Events</a></button>
        <button class="btn"><a href="<c:url value="/event/list/today"/>">Today Events</a></button>
        <button class="btn"><a href="<c:url value="/event/list/hour"/>">This Hour Events</a></button>
    </div>
    <div class="panel panel-default">
            <form method="GET" action="${contextPath}/event/patient-name" class="form--horizontal ">
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
        <jsp:useBean id="events" scope="request" type="org.springframework.beans.support.PagedListHolder" />
        <c:url value="/event/list/today" var="pagedLink">
            <c:param name="p" value="ptag"/>
        </c:url>
        <div style="margin-left: 20px">
            <tg:paging pagedListHolder="${events}" pagedLink="${pagedLink}" />
        </div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Date and Time</th>
                <th>Patient</th>
                <th>Treatment</th>
                <th>Dosage</th>
                <th>Status</th>
                <th>Comment</th>
                <th width="100"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${events.pageList}" var="event">
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