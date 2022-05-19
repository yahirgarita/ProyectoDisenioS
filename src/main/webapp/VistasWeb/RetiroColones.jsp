<%-- 
    Document   : RetiroColones
    Created on : 18 may. 2022, 04:41:40
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

    <div class="row">
        <div class="col-md-8 mx-auto">
            <div class="card mt-8 text-center">
                <div class="card-header">
                    <h1>Retiro en colones</h1>
                    <div>
                        <img src="../imagenesVista/Imagen10.png"/>
                    </div>
                </div>
                <br>
                <form action="/" method="GET" >
                    <div class="form-group">
                        <label>Monto</label>
                        <input type="number" class "form-control" name = "montoRetirado" placeholder="Monto a retirar" required>
                    </div>
                    <div class="box" style="text-align: center">
                        <button type="submit" class= "btn btn-primary">
                            Retirar
                        </button>  
                        <a href="../index.html">Cancelar</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</html>
