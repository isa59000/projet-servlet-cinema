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
    <title>Afficher film d'un genre</title>
    <link href="${pageContext.request.contextPath}/webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet" type="text/css">

</head>
<body>

<%@include file="header.jsp"%>


<h2>Films du genre :${requestScope.genre.nature}</h2>

<form action="process?action=filmsSelectionnes" method="post">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>nfilm</th>
            <th>titre</th>
            <th>realisateur</th>
            <th>acteur principal</th>
            <th>Choix</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="film" items="${ensFilms}">
            <tr>
                <td>${film.id}</td>
                <td>${film.titre}</td>
                <td>${film.nomRealisateur}</td>
                <td>
                    <a href="process?action=infoActeur&id=${film.nacteur_principal}" class="btn btn-success">
                            ${film.nomActeur}
                    </a>
                </td>
                <td>
                    <input type="checkbox" name="choix" value="${film.id}">
                </td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
    <input type="submit" value="Votre panier">
</form>
</body>
</html>
