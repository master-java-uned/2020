<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Countries</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
</head>

<body class="m-3">

<div class="row col-md-6">
    <table class="table table-striped table-bordered table-sm">
        <tr>
            <th>Fecha</th>
            <th>dia</th>
            <th>mes</th>
            <th>casos</th>
            <th>muertes</th>
            <th>pais</th>
            <th>geoId</th>
            <th>paiscod</th>
            <th>popdata</th>
            <th>
        </tr>

        <c:forEach items="${covits}" var="covits">
            <tr>
                <td>${covits.getFecha()}</td>
                <td>${covits.getDia()}</td>    
                <td>${covits.getMes()}</td>
                <td>${covits.getCasos()}</td> 
                <td>${covits.getMuertes()}</td>
                <td>${covits.getPais()}</td> 
                <td>${covits.getCodpais()}</td>
                <td>${covits.getCodpais2()}</td> 
                <td>${covits.getPop()}</td> 
            </tr>
        </c:forEach>
    </table>
</div>

<nav aria-label="Navigation for countries">
    <ul class="pagination">
        <c:if test="${currentPage != 1}">
            <li class="page-item"><a class="page-link" 
                href="leercovid?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">Previous</a>
            </li>
        </c:if>

        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <li class="page-item active"><a class="page-link">
                            ${i} <span class="sr-only">(current)</span></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" 
                        href="leercovid?recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage lt noOfPages}">
            <li class="page-item"><a class="page-link" 
                href="leercovid?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">Next</a>
            </li>
        </c:if>              
    </ul>
</nav>

<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>

</body>
</html>