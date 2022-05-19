<%-- 
    Document   : RegistroUsuario
    Created on : 17 may. 2022, 23:31:57
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

    <body>
        <div class="row">
            <div class="col-md-8 mx-auto">
                <div class="card mt-8 text-center">
                    <div class="card-header">
                        <h1>Registro Usuario</h1>
                    </div>
                    <br>
                    <form method="post" action="<c:url value="/VistasWeb/RegistroUsuario"/>" >
                        <div class="form-group">
                            <label>Nombre</label>
                            <input type="text" class "form-control" name = "nombre" placeholder="Nombre" required>
                        </div>
                        <div class="form-group">
                            <label>Primer apellido</label>
                            <input type="text" class "form-control" name = "primerApellido" placeholder="Primer apellido" required>
                        </div>
                        <div class="form-group">
                            <label>Segundo apellido</label>
                            <input type="text" class "form-control" name = "segundoApellido" placeholder="Segundo apellido" required>
                        </div>
                        <div class="form-group">
                            <label>Identificacion</label>
                            <input type="number" class "form-control" name = "identificacion" placeholder="Identificacion" required>
                        </div>
                        <div class="form-group">
                            <label>Numero de telefono</label>
                            <input type="number" class "form-control" name = "numeroTelefono" placeholder="Numero de telefono" required>
                        </div>
                        <div class="form-group">
                            <label>Correo</label>
                            <input type="email" class "form-control" name = "correoElectronico" placeholder="Correo electronico" required>
                        </div>
                        <div class="form-group">
                            <label>Fecha de nacimiento</label>
                            <input type="datetime-local" class "form-control" name = "fechaNacimiento" required>
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
    </body>      
</html>
