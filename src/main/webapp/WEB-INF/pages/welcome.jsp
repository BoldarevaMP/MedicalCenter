<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Welcome</title>
</head>
<body>


Hello,Maria!
<sec:authorize access="hasRole('ROLE_NURSE')">
    <p>Hi, ${pageContext.request.userPrincipal.name}  <a href="${contextPath}/logout">Log Out </a></p>
</sec:authorize>
</body>
</html>