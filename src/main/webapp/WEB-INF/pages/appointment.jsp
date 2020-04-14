<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Edit Appointment</title>
    <link href="<c:url value='/resources/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
    <link href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet">
</head>
<body>
<div class="well">
    <sec:authorize access="hasRole('ROLE_NURSE') or hasRole('ROLE_DOCTOR')">
        <h4 style="display: inline-block; padding-left: 950px">Hi ${pageContext.request.userPrincipal.name}  <a href="<c:url value="/logout"/>" class="btn btn-danger custom-width">Sign Out</a></h4>
    </sec:authorize>
</div>
<div class="generic-container ">
    <div class="well lead">Appointment Form</div>
    <form:form method="POST" modelAttribute="appointment" class="form--horizontal" id="UpdateForm">
        <div class="row">
            <div class="form-group col-md-12">

                <form:input type="hidden" path="patientDTO.id"/>
                <form:input type="hidden" path="status"/>
                <div class="col-md-6">
                    <span>Type of Treatment</span>
                    <form:select id="treatmentDTO.type" path="treatmentDTO.type" class="form-control">
                        <form:option value="MEDICAL_PROCEDURE">MEDICAL PROCEDURE</form:option>
                        <form:option value="MEDICINE">MEDICINE</form:option>
                    </form:select>
                    <div class="has-error">
                        <form:errors path="treatmentDTO.type" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <div class="col-md-6">
                    <span>Treatment Name</span>
                    <form:input  type="text" id="treatmentDTO.name" path="treatmentDTO.name" class="TreatmentName form-control"/>
                    <div class="has-error">
                        <form:errors path="treatmentDTO.name" class="help-inline"/>
                    </div>
                </div>

                <div class="col-md-6">
                    <span>Dosage</span>
                    <form:input  type="number" id="dosage" path="dosage" class="form-control"/>
                    <div class="has-error">
                        <form:errors path="dosage" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

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
                <div class="col-md-3">
                    <span>Day of Week</span>
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
                <div class="col-md-3">
                    <c:choose>
                    <c:when test="${edit}">
                        <h4>Current Days Pattern:
                            </br>
                            <c:forEach items="${appointment.days}" var="day">
                                ${day}
                            </c:forEach>
                        </h4>
                    </c:when>
                    </c:choose>
                </div>
                <div class="col-md-3">
                    <span>Time of Day</span>
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
                        <form:errors path="time" class="help-inline"/>
                    </div>
                </div>
                <div class="col-md-3">
                    <c:choose>
                        <c:when test="${edit}">
                            <h4>Current Times Pattern:
                                </br>
                                <c:forEach items="${appointment.time}" var="time">
                                    ${time.toString()}
                                </c:forEach>
                            </h4>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-actions floatLeft">
                <c:choose>
                    <c:when test="${edit}">
                        <button id="button" class="btn btn-success" type="submit" onclick="checkConfirmForm(event)">Update Appointment</button>
                    </c:when>
                    <c:otherwise>
                        <button id="button" class="btn btn-success" type="submit">Add Appointment</button>
                    </c:otherwise>
                </c:choose>

                <button class="btn btn-warning"><a href="<c:url value='/patient/edit-patient-${appointment.patientDTO.id}'/>">Cancel</a></button>
            </div>
        </div>
    </form:form>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="${contextPath}/resources/js/sweetalert.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function () {
        $('.TreatmentName').autocomplete({
            source: function (req, resp) {
                $.getJSON({
                    url: "/unicorn/getTreatmentByName",
                    data: {name: $('.TreatmentName').val()},
                    success: function (data) {
                        resp($.map(data, function(v,i){
                            return v.name;
                        }));
                    }
                });
            }
        })
    })
</script>
<script type="text/javascript">
    function checkConfirmForm (e) {
        e.preventDefault();
        swal({
            title: "Are you sure?",
            text: "All Previous Events will be deleted!",
            icon: "warning",
            buttons: [
                'No, cancel it!',
                'Yes, I am sure!'
            ],
            dangerMode: true,
        }).then(function (isConfirm) {
            if (isConfirm) {
                $('#UpdateForm').submit();
            } else {
                swal("Cancelled", "", "error");
            }
        })
    }
</script>
</body>
</html>
