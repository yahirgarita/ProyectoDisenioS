<%-- 
    Document   : ComisionesDelBanco
    Created on : 18 may. 2022, 03:40:39
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
            <div class ="mb-4">
                <div class = " mb-4 text-center">
                    <h1> Ganancias totales por cobro de comision </h1>
                    <div>
                        <img src="../imagenesVista/Imagen23.png"/>
                    </div>
                    <br>
                    
                    <div class="row center-text">
                        <div class="col">
                            <p class = "lead"> Descripcion</p>
                            <p class = "lead"> Monto total por concepto de operaciones de depositos: </p>
                            <p class = "lead"> Monto total por concepto de operaciones de retiros:</p>
                            <p class = "lead"> Monto total por concepto de depositos y retiros:</p>
                                
                        </div>
                        <div class="col">
                            <p class = "lead"> Colones</p>
                            <p class = "lead"> ₡ ${montoDepositos}</p>
                            <p class = "lead"> ₡ ${montoRetiros}</p>
                            <p class = "lead"> ₡ ${montoDepositosYRetiros}</p> 
                        </div>
                        <div class="col">
                            <p class = "lead"> Dolares</p>
                            <p class = "lead"> $ ${montoDepositosDolares} </p>
                            <p class = "lead"> $ ${montoRetirosDolares}</p>
                            <p class = "lead"> $ ${montoDepositosYRetirosDolares}</p>
                        </div>      
                    </div>
                </div>
            </div>
        </div>
    </table>
    <div class="form-group text-center">
        <a href="../index.html">Cancelar</a>
    </div>
</html>