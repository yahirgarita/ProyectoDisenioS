<%-- 
    Document   : MostrarCuenta
    Created on : 6 jun. 2022, 00:25:32
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
                <h1> Informacion de la cuenta </h1>
                <div>
                    <img src="../imagenesVista/Imagen3.png"/>
                </div>
                <div class="jumbotron-mt-4 mb-3 d-grid">
                    <h3>Numero de Cuenta: ${cuenta}</h3>
                    <h3>Saldo Actual: ${saldo}</h3>
                    <h3>Estatus: ${estatus}</h3>
                    <h3>Fecha de Creacion: ${fecha}</h3>
                </div>  
    </table>
    <div class="form-group text-center">
        <a href="index.html">Cancelar</a>
    </div>
</html>

