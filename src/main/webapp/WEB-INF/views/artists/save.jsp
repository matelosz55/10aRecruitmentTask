<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add Artist</title>
</head>
<body>
<form:form method="post" modelAttribute="artist">
    <div>
        <label for="name">Artist Name</label>
        <form:input path="name" id="name" type="text"/>
        <form:errors path="name"/>
    </div>
    <div>
        <input type="submit">
    </div>
    <form:errors path="*"/>
</form:form><br>
</body>
</html>