<%-- 
    Document   : ListarCuenta
    Created on : 18 may. 2022, 03:20:37
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
                {{#each cuenta}}
                <tbody>
                    <tr>
                        <td>{{cuenta}}</td>
                        <td>{{estatus}}</td>
                        <td>{{saldo}}</td>
                        <td>{{propietario}}</td>
                        <td>{{identificacion}}</td>
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
