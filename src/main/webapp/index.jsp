<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Velkommen" %>
</h1>
<br/>

<h2>Udskriver listen af brugere</h2>
<br/>
<br/>

<c:forEach var="person" items="${applicationScope.personMap}">

    <br>
    ${person.value.navn}


</c:forEach>


<br/>
<br/>
<a href="LoginServlet">Login</a>



</body>
</html>