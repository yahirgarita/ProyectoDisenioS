<%-- 
    Document   : EstadoDeCuenta
    Created on : 7 jun. 2022, 14:10:39
    Author     : Jimmy
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="../main.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>

    <table class="table table-striped">
        <div class="card-body">
            <div class ="mb-4 text-center" >
                <h1> Estado de Cuenta </h1>
                <div>
                    <img src="../imagenesVista/Imagen3.png"/>
                </div>
                <div class="jumbotron-mt-4 mb-3 d-grid">
                    <h3>Numero de cuenta: ${cuenta}</h3>
                </div>
               <thead>
                    <tr>
                        <th>FECHA</th>
                        <th>TIPO</th>
                        <th>MONTO</th>
                        <th>CARGO</th>
                    </tr>
                </thead>
                <c:forEach var="operacion" items="${operaciones}">
                <tbody>
                    <tr>
                        <td>${operacion.get('fecha')}</td>
                        <td>${operacion.get('tipo')}</td>
                        <td>${operacion.get('monto')}</td>
                        <td>${operacion.get('cargo')}</td>
                    </tr>
                </tbody>
                </c:forEach>
            </div>
        </div>
    </table>
    <div class="form-group text-center">
        <a href="index.html">Cancelar</a>
    </div>
</html>
