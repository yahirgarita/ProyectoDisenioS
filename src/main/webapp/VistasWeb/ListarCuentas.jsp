<%-- 
    Document   : ListarCuenta
    Created on : 18 may. 2022, 03:20:37
    Author     : yahir
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
                <h1> Lista de cuentas </h1>
                <div>
                    <img src="../imagenesVista/Imagen3.png"/>
                </div>
                <thead>
                    <tr>
                        <th>Numero de cuenta</th>
                        <th>Estatus</th>
                        <th>Saldo</th>
                        <th>Propietario</th>
                        <th>Identificacion</th>
                    </tr>
                </thead>
                
                <tbody>
                    <c:forEach var="cuenta" items="${cuentas}">
                    <tr>
                        <td><c:out value="${cuenta.get('numeroCuenta')}"/></td>
                        <td><c:out value="${cuenta.get('estatus')}"/></td>
                        <td><c:out value="${cuenta.get('saldo')}"/></td>
                        <td><c:out value="${cuenta.get('nombre')}"/></td>
                        <td><c:out value="${cuenta.get('identificacion')}"/></td>
                    </tr>
                    </c:forEach>
                </tbody>
            </div>
        </div>
    </table>
    <div class="form-group text-center">
        <form METHOD="GET" action="MostrarCuentaWeb">
               
              <label>Numero de cuenta :</label>
                <input type="Number"  name="numeroCuenta" required>
                <br><br>
                <button type="submit" class= "btn btn-primary">
                     Consultar
                 </button>
        </form>
        <a href="index.html">Cancelar</a>
    </div>
</html>
