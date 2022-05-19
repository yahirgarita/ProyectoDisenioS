<%-- 
    Document   : DepositoColones
    Created on : 18 may. 2022, 03:35:21
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

    <div class="row">
        <div class="col-md-8 mx-auto">
            <div class="card mt-8 text-center">
                <div class="card-header">
                    <h1>Deposito en colones</h1>
                    <div>
                        <img src="../imagenesVista/Imagen9.png"/>
                    </div>
                </div>
                <br>
                <form action="/" method="GET" >
                    <div class="form-group">
                        <label>Numero de cuenta</label>
                        <input type="text" class "form-control" name = "numeroCuenta" placeholder="Numero de cuenta" required>
                    </div>
                    <div class="form-group">
                        <label>Monto a depositar</label>
                        <input type="number" class "form-control" name = "montoDepositado" placeholder="Monto a depositar" required>
                    </div>
                    <div class="box" style="text-align: center">
                        <button type="submit" class= "btn btn-primary">
                            Aceptar
                        </button>
                        <a href="../index.html">Cancelar</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</html>
