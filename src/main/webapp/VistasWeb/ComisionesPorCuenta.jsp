<%-- 
    Document   : ComisionesPorCuenta
    Created on : 18 may. 2022, 03:40:26
    Author     : yahir
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="../main.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">

    <table class="table table-striped">
        <div class="card-body">
            <div class ="mb-4 text-center" >
                <h1> Ganancias totales de cada cuenta por cobro de comision </h1>
                <div>
                    <img src="../imagenesVista/Imagen25.png"/>
                </div>
                <br>
                <thead>
                    <tr>
                        <th>Cuenta</th>
                        <th>Total de depositos y retiros</th>
                        <th>Total depositos</th>
                        <th>Total retiros</th>
                    </tr>
                </thead>
                {{#each cuenta}}
                <tbody>
                    <tr>
                        <td>{{cuenta}}</td>
                        <td>{{depositosYRetiros}}</td>
                        <td>{{depositos}}</td>
                        <td>{{retiros}}</td>
                    </tr>
                </tbody>
                {{/each}}
            </div>
        </div>
    </table>
    <div class="form-group text-center">
        <button type="submit" class= "btn btn-primary">
            Consultar
        </button>
        <a href="../index.html">Cancelar</a>
    </div>
</html>