<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: damia
  Date: 27/10/2022
  Time: 12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Choix d'un genre</title>
    <link href="${pageContext.request.contextPath}/webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@include file="header.jsp"%>

<div class="container col-4 offset-4 mt-5">
    <div class="card">
        <div class="card-header bg-primary text-white text-center"> Choisir un genre</div>
        <div class="card-body">

            <form method="post" action="process?action=filmsDuGenre">

                <label>Genre :</label>
                <select name="genre">
                    <option selected disabled>Choisir un genre</option>

                    <c:forEach var="genre" items="${sessionScope.ensGenres}">
                        <option value="${genre.ngenre}">${genre.nature}</option>

                    </c:forEach>

                </select>
                <br/><br/>
                <button type="submit" class="btn btn-primary">Soumettre la requête</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>

