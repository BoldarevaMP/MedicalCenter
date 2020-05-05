<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Add Patient</title>
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
    <div class="well lead">Patient Form</div>

    <form:form method="POST" modelAttribute="patient" class="form--horizontal ">
        <form:input type="hidden" path="id" id="id"/>

        <div class="row">
            <div class="form-group col-md-12">
                <div class="col-md-7">
                    <form:input type="text" id="firstName" path="firstName" class="form-control"
                                placeHolder="First Name"/>
                    <div class="has-error">
                        <form:errors path="firstName" class="help-inline"/>
                    </div>
                </div>

            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-12">
                <div class="col-md-7">
                    <form:input type="text" id="lastName" path="lastName" class="form-control "
                                placeHolder="Last Name"/>
                    <div class="has-error">
                        <form:errors path="lastName" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-12">
                <div class="col-md-7">
                    <form:input type="number" id="healthInsurance" path="healthInsurance" class="form-control"
                                placeHolder="Health Insurance"/>
                    <div class="has-error">
                        <form:errors path="healthInsurance" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
        <%--        <div class="row">--%>
        <%--            <div class="form-group col-md-12">--%>
        <%--                <div class="col-md-7">--%>
        <%--                    <form:input type="text" id="status" path="status" class="form-control" placeHolder="Status"/>--%>
        <%--                    <div class="has-error">--%>
        <%--                        <form:errors path="status" class="help-inline"/>--%>
        <%--                    </div>--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--        </div>--%>
        <div class="row">
            <div class="form-group col-md-12">
                <div class="col-md-7">
                    <form:input type="text" id="diagnosis" path="diagnosis" class="form-control"
                                placeHolder="Diagnosis"/>
                    <div class="has-error">
                        <form:errors path="diagnosis" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
        <%--        <div class="row">--%>
        <%--            <div class="form-group col-md-12">--%>
        <%--                <div class="col-md-7">--%>
        <%--                    <form:input type="text" id="doctor" path="doctorDTO.lastName" class="form-control" placeHolder="Doctor"/>--%>
        <%--                    <div class="has-error">--%>
        <%--                        <form:errors path="doctorDTO.lastName" class="help-inline"/>--%>
        <%--                    </div>--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--        </div>--%>

        <div class="row">
            <div class="form-actions floatLeft">
                <c:choose>
                    <c:when test="${edit}">
                        <button id="button" class="btn btn-success" type="submit">Update Patient</button>
                        <button class="btn btn-warning"><a href="<c:url value='/patient/list' />">Cancel</a></button>
                    </c:when>
                    <c:otherwise>
                        <button id="button" class="btn btn-success" type="submit">Save Patient</button>
                        <button class="btn btn-warning"><a href="<c:url value='/patient/list' />">Cancel</a></button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</div>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript">

</script>
</body>
</html>