
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Affichage des infos d'un acteur</title>
    <link href="${pageContext.request.contextPath}/webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet" type="text/css">

</head>
<body>

<%@include file="header.jsp"%>


<h2>Information de l'acteur</h2>

<div class="mb-3">
    <label >Numéro d'acteur :</label>
    <label >${requestScope.acteur.NActeur}</label>
</div>

<div class="mb-3">
    <label >nom :</label>
    <label >${requestScope.acteur.nom}</label>
</div>

<div class="mb-3">
    <label >Prénom :</label>
    <label >${requestScope.acteur.prenom}</label>
</div>

<div class="mb-3">
    <label >Nationalité :</label>
    <label >${requestScope.acteur.nomPays}</label>
</div>

</body>
</html>

