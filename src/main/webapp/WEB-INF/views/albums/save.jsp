<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add Album</title>
</head>
<body>
<form:form method="post" autocomplete="false" modelAttribute="album">
    <div>
        <label for="title">Album Title</label>
        <form:input path="title" id="title" type="text"/>
        <form:errors path="title"/>
    </div>
        <div>
            <label>Artist</label>
            <form:select itemValue="id" itemLabel="name" path="artist.id" items="${artist}"/>
        </div>
<%--    <div>--%>
<%--        <label>Artist</label>--%>
<%--        <select name="artist">--%>
<%--            <c:forEach items="${artist}"--%>
<%--                       var="artist">--%>
<%--                <option value="${artist.id}">--%>
<%--                        ${artist.name}</option>--%>
<%--            </c:forEach>--%>
<%--        </select>--%>
<%--    </div>--%>

    <div>
        <label for="releaseDate">Release date</label>
        <form:input path="releaseDate" id="releaseDate" type="date"/>
        <form:errors path="releaseDate"/>
    </div>
    <div>
        <label for="comments">Comments</label>
        <form:input path="comments" id="comments" type="text"/>
        <form:errors path="comments"/>
    </div>
    <div>
        <label for="numberOfTracks">Number of Tracks</label>
        <form:input path="numberOfTracks" id="numberOfTracks" type="number"/>
        <form:errors path="numberOfTracks"/>
    </div>
    <div>
        <input type="submit">
    </div>
    <form:errors path="*"/>
</form:form><br>
</body>
</html>