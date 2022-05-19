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
    public static ArrayList<Operacion> obtenerOperacionesPorNumCuenta(String numeroCuenta){
        ArrayList<Operacion> operaciones = new ArrayList<>();
        conexionBD.conexionDataBase();
         ResultSet resultado = conexionBD.inquiry("select * from Operacion where cuenta = '" +numeroCuenta+ "'");
        try{
            while(resultado.next()){
                boolean cargo;
                if(resultado.getString("cargo").equals("Si")){
                    cargo = true;
                }
                else{
                    cargo = false;
                }
                Operacion operacion = new Operacion(resultado.getString("tipo"),resultado.getString("moneda"), cargo
                        , Double.parseDouble(resultado.getString("monto")),LocalDate.parse(resultado.getString("fecha"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                operaciones.add(operacion);
            }
        }
        catch(SQLException e){
            return operaciones;
        }
        return operaciones;
    }
    
    
}
