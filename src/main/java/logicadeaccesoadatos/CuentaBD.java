package logicadeaccesoadatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import logicadenegocios.CuentaBancaria;
import util.Encriptar;
/**
 *
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 * 
 * @version 1.0
 */
public class CuentaBD {
    private static final ConexionBD conexionBD = new ConexionBD();
    
    public static void registrarCuentaEnBD(CuentaBancaria pCuenta, String pCodigo){
        conexionBD.conexionDataBase();
        conexionBD.ejecutarSentSQL("INSERT INTO Cuenta values (" + "'" + Encriptar.cifrar(Integer.toString(pCuenta.getNumCuenta()))
            + "','"+ pCuenta.getFechaCreacion().toString() + "','" + Encriptar.cifrar(Double.toString(pCuenta.getSaldo())) + "','" 
            + pCuenta.getEstatus() + "','" + Encriptar.cifrar(pCuenta.getPin()) + "')" );
        conexionBD.ejecutarSentSQL("insert into PersonaCuenta values (" + "'" + pCodigo + "','" 
                + Encriptar.cifrar(Integer.toString(pCuenta.getNumCuenta())) + "')");
        conexionBD.salirBD();
    }
    public static CuentaBancaria recuperarCuentaXNum(String pNumCuenta){
        conexionBD.conexionDataBase();
        ResultSet averiguar = conexionBD.inquiry("select * from Cuenta where numeroCuenta = '" + pNumCuenta + "'");
        try{
            while(averiguar.next()){
                CuentaBancaria newCuenta = new CuentaBancaria(Integer.parseInt(Encriptar.descifrar(averiguar.getString("numeroCuenta"))),
                        Double.parseDouble(Encriptar.descifrar(averiguar.getString("saldo"))), Encriptar.descifrar(averiguar.getString("pin")), 
                        LocalDate.parse(averiguar.getString("fecha")));
                newCuenta.setEstatus(averiguar.getString("estatus"));
                return newCuenta;
            }
        }
        catch(SQLException e){
            return null;
        }
        return null;
    }
    public static CuentaBancaria recuperarCuentaPorNumPin(String numeroCuenta, String pin){
       conexionBD.conexionDataBase();
       ResultSet buscar = conexionBD.inquiry("SELECT * FROM Cuenta WHERE numeroCuenta = '" + numeroCuenta + "' and pin = '" + pin + "'");
       try{
          while (buscar.next()){
              return new CuentaBancaria(Integer.parseInt(Encriptar.descifrar(buscar.getString("numeroCuenta"))),
                      LocalDate.parse(buscar.getString("fecha")),
                      Double.parseDouble(Encriptar.descifrar(buscar.getString("saldo"))),
                      Encriptar.descifrar(buscar.getString("pin")), buscar.getString("estatus"));
          }
        }catch (SQLException e){
            return null;
        }
        return null;
    }

    public static void cambiarPinCuenta(String pNumCuenta, String pNewPin){
        conexionBD.conexionDataBase();
        conexionBD.ejecutarSentSQL("update Cuenta set pin = '" + pNewPin + "' where numeroCuenta = '" + pNumCuenta + "'");
        conexionBD.salirBD();
    }
    
    public static void modificarEstado(String pNumCuenta, String pEstatus){
        conexionBD.conexionDataBase();
        conexionBD.ejecutarSentSQL("update Cuenta set estatus = '" + pEstatus + "'where numeroCuenta = '" + pNumCuenta + "'");
        conexionBD.salirBD();
    }
    
    
        
        
}

