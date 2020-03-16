<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Event List</title>
    <link href="<c:url value='/resources/css/bootstrap.css' />" rel="stylesheet">
    <link href="<c:url value='/resources/css/app.css' />" rel="stylesheet">
</head>

<body>
<h2>List of Events</h2>
    <table class="table table-hover">
        <thead>
    <tr>
        <th width="100" >id</th>
        <th width="100" >Date</th>
        <th width="100">Time</th>
        <th width="100">Patient</th>
        <th width="100">Treatment</th>
        <th width="100">Status</th>
        <th width="100">Action</th>
    </tr>
    <c:forEach var="event" items="${eventsList}">
        <tr>
            <td>${event.id}</td>
            <td>date</td>
            <td>time</td>
            <td>last name</td>
            <td>treatment</td>
            <td>${event.status}</td>
            <td>
                <a href="/edit/${event.id}">edit</a>

            </td>
        </tr>
    </c:forEach>
        </thead>
</table>
<script src="${contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>
