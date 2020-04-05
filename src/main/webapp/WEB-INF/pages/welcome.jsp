<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html lang="en">
<head>
    <title>Welcome</title>
</head>
<body>


Hello!
<sec:authorize access="hasRole('ROLE_NURSE') or hasRole('ROLE_DOCTOR')">
    <p>Hi, ${pageContext.request.userPrincipal.name}  <a href="${contextPath}/logout">Log Out </a></p>
</sec:authorize>
</body>
</html>