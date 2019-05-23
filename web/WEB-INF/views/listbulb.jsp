<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>List of discovered bulbs</title>
</head>
<body>
<a href="add">Add Bulb</a><br/>
<br/>
<c:forEach var="bulb" items="${Bulbs}">
    <c:out value="${bulb.name}"/>
</c:forEach>

<br/>
<hr/>
Prymitywny html do poprawienia<br/>
Może się przydać <a href="http://getbootstrap.com" target="_blank">Bootstrap</a>.
</body>
</html>