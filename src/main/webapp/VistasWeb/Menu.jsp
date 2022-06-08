<%-- 
    Document   : Menu
    Created on : 18 may. 2022, 01:49:40
    Author     : yahir
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="../main.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"/>
    </head>
    <br>
        <h1>Sistema Bancario</h1>
        <br>
        <div class="row center-text">
            <center>
                <h1>
                    <div class="col">
                        <a class="btn btn-secondary" href="RegistroUsuario.jsp"> Registro cliente</a>
                    </div>
                    <div class="col">
                        <a class="btn btn-secondary" href="CrearCuenta.jsp"> Crear cuenta bancaria</a>
                    </div>
                    <div class="col">
                        <a class="btn btn-secondary" href="../ConsultarClientesWeb"> Listar clientes</a>
                    </div>
                    <div class="col">
                        <a class="btn btn-secondary" href="../ConsultarCuentasWeb"> Listar cuentas</a>
                    </div>
                    <div class="col">
                        <a class="btn btn-secondary" href="CambiarPinPaso1.jsp"> Cambiar PIN</a>
                    </div>
                    <div class="col">
                        <a class="btn btn-secondary" href="ConsultarSaldoActual.jsp"> Consultar saldo actual</a>
                    </div>
                    <div class="col">
                        <a class="btn btn-secondary"  href="DepositoColones.jsp"> Depositar en colones</a>
                    </div>
                    <div class="col">
                        <a class="btn btn-secondary" href="vistaWeb/TransferenciasEntreCuentas"> Depositar en dólares</a>
                    </div>
                    <div class="col">
                        <a class="btn btn-secondary" href="RetiroColones.jsp"> Retiro en colones</a>
                    </div>
                    <div class="col">
                        <a class="btn btn-secondary" href="RetiroDolares.jsp"> Retiro en dólares</a>
                    </div>
                    <div class="col">
                        <a class="btn btn-secondary" href="../ConsultarTipoCambioVentaWeb"> Tipo de cambio de venta</a>
                    </div>
                    <div class="col">
                        <a class="btn btn-secondary" href="../ConsultarTipoCambioWeb"> Tipo de cambio compra </a>
                    </div>
                    <div class="col">
                        <a class="btn btn-secondary" href="Transferencia.jsp""> Realizar transferencia</a>
                    </div>
                    <div class="col">
                        <a class="btn btn-secondary" href="ConsultarEstadoCuenta.jsp"> Consultar Estado de Cuenta</a>
                    </div>
                    <div class="col">
                        <a class="btn btn-secondary" href="ComisionesPorCuenta.jsp"> Ganancias de comisiones por cuenta</a>
                    </div>
                    
                    <div class="col">
                        <a class="btn btn-secondary" href="ComisionesDelBanco.jsp"> Ganancias de comisiones del banco</a>
                    </div>
                    
                    <div class="col">
                        <a class="btn btn-secondary" href="EstatusCuenta.jsp"> Ver estatus de cuenta</a>
                    </div>
                <h1>    
            </center>
        </div>
        <br>
</html>
