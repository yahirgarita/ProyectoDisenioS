package logicadeaccesoadatos;

import java.time.LocalDate;

import logicadenegocios.Operacion;

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

public class OperacionBD {
    private static ConexionBD conexionBD = new ConexionBD();
    
    public static void realizarOperacionEnBD(Operacion operacion, String pNumCuenta){
        conexionBD.conexionDataBase();
        conexionBD.ejecutarSentSQL("insert into Operacion values ('" + operacion.getTipo() + "','" + operacion.getCargo() + "','" + operacion.getMonto() 
            + "','" + operacion.getMoneda() + "','" + operacion.getFechaOperacion().toString() + "'");
    }
    
    public static int numOperacionEnCuenta(String pNumCuenta){
        conexionBD.conexionDataBase();
        int cont = 0;
        ResultSet resultado = conexionBD.inquiry("select * from Operacion where cuenta = '" + pNumCuenta + "'and tipo = 'depósitos' or tipo = 'retiros'");
        try{
            while(resultado.next()){
                cont ++;
            }
        }
        catch(SQLException e){
            return 0;
        }
        return cont;
    }
    
}
