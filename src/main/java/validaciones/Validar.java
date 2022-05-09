package validaciones;

import logicadeaccesoadatos.ConexionBD;
import logicadenegocios.Persona;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.*;

import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;

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
    
    public static boolean existeUsuario(Persona pPersona){
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
    
    public static boolean validarFormatoPIN(String pPin){
        String simbolos = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])([A-Za-z\\d$@$!%*?&]|[^ ]){6}$";
        Pattern patron = Pattern.compile(simbolos);
        Matcher matcher = patron.matcher(pPin);
        return matcher.matches();
    }
    
    public static boolean validarEsEntero(String numero){
        try{
            int num = Integer.parseInt(numero);
            return true;
            
        }catch(Exception e){
            return false;
        }
    }
}

