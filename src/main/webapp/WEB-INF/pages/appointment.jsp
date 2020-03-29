<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Edit Event</title>
    <link href="<c:url value='/resources/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>

</head>
<body>

<div class="generic-container ">
    <div class="well lead">Appointment Form</div>
    <form:form method="POST" modelAttribute="appointment" class="form--horizontal ">
        <div class="row">
            <div class="form-group col-md-12">

                <div class="col-md-6">
                    <span>Start Date</span>
                    <form:input type="date" id="startDate" path="startDate" class="form-control"/>
                    <div class="has-error">
                        <form:errors path="startDate" class="help-inline"/>
                    </div>
                </div>
                <div class="col-md-6">
                    <span>End Date</span>
                    <form:input type="date" id="endDate" path="endDate" class="form-control"/>
                    <div class="has-error">
                        <form:errors path="endDate" class="help-inline"/>
                    </div>
                </div>
            </div>
            <div class="form-group col-md-12">
                <div class="col-md-6">
                    <form:select type="text" id="days" multiple="true" path="days" class="form-control ">
                        <option value="MON">MONDAY</option>
                        <option value="TUE">TUESDAY</option>
                        <option value="WED">WEDNESDAY</option>
                        <option value="THU">THURSDAY</option>
                        <option value="FRI">FRIDAY</option>
                        <option value="SAT">SATURDAY</option>
                        <option value="SUN">SUNDAY</option>
                    </form:select>
                    <div class="has-error">
                        <form:errors path="days" class="help-inline"/>
                    </div>
                </div>
                <div class="col-md-6">
                    <form:select type="text" id="days" multiple="true" path="time" class="form-control ">
                        <option value="AM_12">00:00</option>
                        <option value="AM_1">01:00</option>
                        <option value="AM_2">02:00</option>
                        <option value="AM_3">03:00</option>
                        <option value="AM_4">04:00</option>
                        <option value="AM_5">05:00</option>
                        <option value="AM_6">06:00</option>
                        <option value="AM_7">07:00</option>
                        <option value="AM_8">08:00</option>
                        <option value="AM_9">09:00</option>
                        <option value="AM_10">10:00</option>
                        <option value="AM_11">11:00</option>
                        <option value="PM_12">12:00</option>
                        <option value="PM_1">13:00</option>
                        <option value="PM_2">14:00</option>
                        <option value="PM_3">15:00</option>
                        <option value="PM_4">16:00</option>
                        <option value="PM_5">17:00</option>
                        <option value="PM_6">18:00</option>
                        <option value="PM_7">19:00</option>
                        <option value="PM_8">20:00</option>
                        <option value="PM_9">21:00</option>
                        <option value="PM_10">22:00</option>
                        <option value="PM_11">23:00</option>
                    </form:select>
                    <div class="has-error">
                        <form:errors path="days" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-actions floatLeft">
                <button id="button" class="btn btn-success" type="submit">Update Event</button>
                <button class="btn btn-warning"><a href="<c:url value='/addAppointment' />">Cancel</a></button>
            </div>
        </div>
    </form:form>
</div>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript">

</script>
</body>
</html>
