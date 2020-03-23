
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Add Event</title>
    <link href="<c:url value='/resources/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>

</head>
<body>

<div class="generic-container ">
    <div class="well lead">Event Form</div>
    <div class="well">

        <table>
            <tr>
                <td width="200">${event.date}</td>
                <td width="200">${event.appointmentDTO.patientDTO.lastName} ${event.appointmentDTO.patientDTO.firstName}</td>
                <td width="200">${event.appointmentDTO.treatmentDTO.name}</td>
                <td width="200">${event.appointmentDTO.dosage} ${event.appointmentDTO.treatmentDTO.dosageForm}</td>
            </tr>
        </table>
    </div>
    <form:form method="POST" modelAttribute="event" class="form--horizontal ">
        <form:input type="hidden" path="id" id="id"/>

        <div class="row">
            <div class="form-group col-md-12">
                <div class="col-md-7">
                    <form:select type="text" id="status" path="status" class="form-control ">
                                <option selected value="PLANNED">PLANNED</option>
                                <option value="DONE">DONE</option>
                                <option value="CANCELLED">CANCELLED</option>
                    </form:select>
                    <div class="has-error">
                        <form:errors path="status" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-12">
                <div class="col-md-7">

                    <form:input type="textarea" id="comment" path="comment" class="form-control " placeHolder="Comment"/>

                        <div class="has-error">
                        <form:errors path="comment" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-actions floatLeft">
                        <button id="button" class="btn btn-success" type="submit">Update Event</button><button class="btn btn-warning"><a href="<c:url value='/event/list' />">Cancel</a></button>

            </div>
        </div>
    </form:form>
</div>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript">

</script>
</body>
</html>
