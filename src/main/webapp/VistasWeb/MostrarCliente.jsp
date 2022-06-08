<%-- 
    Document   : MostrarCliente
    Created on : 5 jun. 2022, 17:24:05
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
                <h1> Informacion del cliente </h1>
                <div>
                    <img src="../imagenesVista/Imagen3.png"/>
                </div>
                <div class="jumbotron-mt-4 mb-3 d-grid">
                    <h3>Nombre Completo: ${nombre}</h3>
                    <h3>Identificacion: ${identificacion}</h3>
                    <h3>Fecha de Nacimiento: ${fecha}</h3>
                    <h3>Telefono: ${telefono}</h3>
                    <h3>Correo Electronico: ${correo}</h3>
                    <h3>Codigo de usuario: ${codigo}></h3>
                    
                </div>
                <thead>
                    <tr>
                        <th>Cuentas</th>
                    </tr>
                </thead>
                <c:forEach var="cuenta" items="${cuentas}">
                <tbody>
                    <tr>
                        <td>Numero de Cuenta: <c:out value="${cuenta.getNumCuenta()}"/></td>
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
