package logicadeaccesoadatos;


import logicadenegocios.Persona;
import validaciones.Validar;


import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 * 
 * @version 1.0
 */
public class PersonaBD {
    private static final ConexionBD conexionBD = new ConexionBD();
    
    public static boolean registrarClientesEnBD(Persona pPersona){
        if(!Validar.existeCliente(pPersona)){
            conexionBD.conexionDataBase();
            conexionBD.ejecutarSentSQL("INSERT INTO Persona VALUES (" + "'" +pPersona.getPrimerApellido()+ "','"+ pPersona.getSegundoApellido()
                    +"','" + pPersona.getNombre()+ "'," + pPersona.getIdPersona() + ",'" + pPersona.getFechaNacimiento()
                    + "'," + pPersona.getNumTelefonico() +",'" +pPersona.getCorreoPersona() +
                    "',' "+ pPersona.getCodigo() + "' , 'default')");
                  
            conexionBD.salirBD();
            return true;
        }
        else{
            return false;
        }
    }
}
