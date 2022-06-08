<%-- 
    Document   : ListarClientes
    Created on : 18 may. 2022, 03:20:22
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
                <h1> Lista de clientes </h1>
                <div>
                    <img src="../imagenesVista/Imagen3.png"/>
                </div>
                <thead>
                    <tr>
                        <th>Primer Apellido</th>
                        <th>Segundo Apellido</th>
                        <th>Nombre</th>
                        <th>Identificacion</th>
                    </tr>
                </thead>
                <c:forEach var="cliente" items="${clientes}">
                <tbody>
                    <tr>
                        <td><c:out value="${cliente.getPrimerApellido()}"/></td>
                        <td><c:out value="${cliente.getSegundoApellido()}"/></td>
                        <td><c:out value="${cliente.getNombre()}"/></td>
                        <td><c:out value="${cliente.getIdPersona()}"/></td>
                    </tr>
                </tbody>
                </c:forEach>
            </div>
        </div>
    </table>
    <div class="form-group text-center">
        <form METHOD="GET" action="MostrarClienteWeb">
               
              <label>Numero de identificacion :</label>
                <input type="Number"  name="identificacion" required>
                <br><br>
                <button type="submit" class= "btn btn-primary">
                     Consultar
                 </button>
        </form>
        <a href="index.html">Cancelar</a>
    </div>
</html>