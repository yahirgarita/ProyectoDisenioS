<%-- 
    Document   : CrearCuenta
    Created on : 18 may. 2022, 03:24:03
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
                    <h1>Registro de cuenta</h1>
                    <div>
                        <img src="../imagenesVista/Imagen19.png"/>
                    </div>
                </div>
                <br>
                <form action="/" method="GET" >
                    <div class="form-group">
                        <label>Pin</label>
                        <input type="password" class "form-control" name = "pin" placeholder="Pin" required>
                    </div>
                    <div class="form-group">
                        <label>Monto inicial</label>
                        <input type="number" class "form-control" name = "montoDepositoInicial" placeholder="Monto inicial" required>
                    </div>
                    <div class="form-group">
                        <label>Identificacion cliente</label>
                        <input type="number" class "form-control" name = "identificacionCliente" placeholder="Identificacion cliente" required>
                    </div>
                    <div class="form-group">
                        <button type="submit" class= "btn btn-primary">
                            Registrar
                        </button>
                        <a href="../index.html">Cancelar</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</html>
