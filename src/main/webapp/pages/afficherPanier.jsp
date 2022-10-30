<%--
  Created by IntelliJ IDEA.
  User: damia
  Date: 27/10/2022
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Afficher Panier</title>
    <link href="${pageContext.request.contextPath}/webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet" type="text/css">

</head>
<body>

<%@include file="header.jsp"%>

<h2>Contenu du panier</h2>


<table class="table table-striped">
    <thead>
    <tr>
        <th>nfilm</th>
        <th>titre</th>
        <th>realisateur</th>
        <th>acteur principal</th>

    </tr>
    </thead>
    <tbody>

    <c:forEach var="map" items="${ensChoix}">
        <tr>
            <td>${map.value.id}</td>
            <td>${map.value.titre}</td>
            <td>${map.value.nomRealisateur}</td>
            <td>
                <a href="process?action=infoActeur&id=${map.value.nacteur_principal}" class="btn btn-success">
                        ${map.value.nomActeur}
                </a>
            </td>

        </tr>
    </c:forEach>
    </tbody>

</table>


</body>
</html>
