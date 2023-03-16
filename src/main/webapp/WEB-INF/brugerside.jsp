<%--
  Created by IntelliJ IDEA.
  User: nbh
  Date: 14/03/2023
  Time: 12.03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
velkommen ${sessionScope.person.navn}

<br>
<br>


<c:forEach var="emne" items="${sessionScope.person.stringSet}">

    <tr>
        <td>
                ${emne};
        </td>
        <td>
            <form action>
            <button formaction="ServletSlet" value="${emne}" name="navn"> Slet</button>
            </form>
        </td>

    </tr>


</c:forEach>


<br>
<br>


<form action="ServletEditListe" method="get">

    <label>angiv tilføj emne til liste</label><br>
    <input type="text" name="emne" value="oel bong"><br><br>
    <input type="submit" value="tilføj">
</form>


<br>
<br>

<h2> ${requestScope.msg}</h2>
<br>

<form action="ServletSave">

    <label></label><br>

    <input type="submit" value="Save">
</form>

<br>
<br>

<form action="ServletSave" method="post">

    <label></label><br>

    <input type="submit" value="logout">
</form>


</body>
</html>


