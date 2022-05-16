package logicadeaccesoadatos;

import java.time.LocalDate;

import logicadenegocios.Operacion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import util.Encriptar;


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
        conexionBD.ejecutarSentSQL("insert into Operacion values ('" + operacion.getFechaOperacion().toString() + "','" + operacion.getTipo() + "','" + operacion.getCargo() 
                + "','" + operacion.getMonto() + "','" + operacion.getMoneda() + "','"+ pNumCuenta + "')");
        conexionBD.salirBD();
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
    
    public static Double obtenerComisionOperacionesDepositos(){
        
        conexionBD.conexionDataBase();
        double comision = 0;
        ResultSet resultado = conexionBD.inquiry("select * from Comision where tipoComision = 'depósitos'");
        try{
            while(resultado.next()){
                comision+= Double.parseDouble(resultado.getString("monto"));
            }
        }
        catch(SQLException e){
            return comision;
        }
        return comision;
    }
    
    public static Double obtenerComisionOperacionesRetiros(){
        
        conexionBD.conexionDataBase();
        double comision = 0;
        ResultSet resultado = conexionBD.inquiry("select * from Comision where tipoComision = 'retiros'");
        try{
            while(resultado.next()){
                comision+= Double.parseDouble(resultado.getString("monto"));
            }
        }
        catch(SQLException e){
            return comision;
        }
        return comision;
    }
    
    public static Double obtenerComisionOperacionesDepositosPorCuenta(String numeroCuenta){
        
        conexionBD.conexionDataBase();
        double comision = 0;
        ResultSet resultado = conexionBD.inquiry("select * from Comision where tipoComision = 'depósitos' AND cuenta = '"+ numeroCuenta + "'");
        try{
            while(resultado.next()){
                comision+= Double.parseDouble(resultado.getString("monto"));
            }
        }
        catch(SQLException e){
            return comision;
        }
        return comision;
    }
    
    public static Double obtenerComisionOperacionesRetirosPorCuenta(String numeroCuenta){
        
        conexionBD.conexionDataBase();
        double comision = 0;
        ResultSet resultado = conexionBD.inquiry("select * from Comision where tipoComision = 'retiros' AND cuenta = '"+ numeroCuenta + "'");
        try{
            while(resultado.next()){
                comision+= Double.parseDouble(resultado.getString("monto"));
            }
        }
        catch(SQLException e){
            return comision;
        }
        return comision;
    }
}
