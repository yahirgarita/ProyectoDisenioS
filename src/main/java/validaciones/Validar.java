package validaciones;

import logicadeaccesoadatos.ConexionBD;
import logicadenegocios.Persona;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.*;

import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import logicadeaccesoadatos.*;

/**
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 * 
 * @version 1.0
 */
public class Validar{
    public static ConexionBD coneccion = new ConexionBD();
    /**
     * Método para verificar si existe un usuario dentro de la base de datos
     * 
     * @param pPersona      Objeto que contiene la persona a la que se quiere busca
     * @return              True si existe un usuario, false si no exite un usuario
     * 
     */
    
    public static boolean existeCliente(Persona pPersona){
        coneccion.conexionDataBase();
        ResultSet resultado = coneccion.inquiry("SELECT * FROM Persona WHERE codigo = '" + pPersona.getCodigo() + 
                "' or identificacion =" + pPersona.getIdPersona());
        try{
            while(resultado.next()){
                return true;
            }
        }catch (SQLException e){
            return true;
        }
        coneccion.salirBD();
        return false;        
    }
    
    public static boolean espacioVacio(String texto){
        return texto.length() > 0;
    }
    
    public static boolean espaciosVacios(ArrayList<String> mensajes){
        for(String mensaje:mensajes){
            if(mensaje.length()<= 0){
                return false;
            }
        }
        return true;
    }
    public static boolean fechaVacia(Date fecha){
        return fecha != null;

    }
    public static boolean numCuentasPresentesEnBD(String pNumero){
        coneccion.conexionDataBase();
        ResultSet resultado = coneccion.inquiry("select * from Cuenta where numeroCuenta = '" + pNumero + "'");
        try{
            while(resultado.next()){
                return false;
            }
        }
        catch(SQLException e){
            return false;
        }
        coneccion.salirBD();
        return true;
        
    }

}

