package logicadeaccesoadatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import logicadenegocios.*;
import util.Encriptar;
import static validaciones.Validar.coneccion;
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
    
    public static CuentaBancaria recuperarCuentaXNumCLI(String pNumCuenta){
        conexionBD.conexionDataBase();
        ResultSet averiguar = conexionBD.inquiry("select * from Cuenta where numeroCuenta = '" + Encriptar.cifrar(pNumCuenta) + "'");
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
    
    public static void cambiarPinCuentaCLI(String pNumCuenta, String pNewPin){
        conexionBD.conexionDataBase();
        conexionBD.ejecutarSentSQL("update Cuenta set pin = '" + Encriptar.cifrar(pNewPin)+ "' where numeroCuenta = '" + Encriptar.cifrar(pNumCuenta) + "'");
        conexionBD.salirBD();
    }
    
    public static void modificarEstado(String pNumCuenta, String pEstatus){
        conexionBD.conexionDataBase();
        conexionBD.ejecutarSentSQL("update Cuenta set estatus = '" + pEstatus + "'where numeroCuenta = '" + pNumCuenta + "'");
        conexionBD.salirBD();
    }
    
    public static ArrayList<CuentaBancaria> recuperarCuentas(){
        ArrayList<CuentaBancaria> cuentaBancariaCadena = new ArrayList<>();
        try{
            conexionBD.conexionDataBase();
            ResultSet buscar = conexionBD.inquiry("select * from Cuenta");
            while(buscar.next()){
                CuentaBancaria cuentaBanc = new CuentaBancaria(Integer.parseInt(Encriptar.descifrar(buscar.getString("numeroCuenta"))),
                      LocalDate.parse(buscar.getString("fecha")),
                      Double.parseDouble(Encriptar.descifrar(buscar.getString("saldo"))),
                      Encriptar.descifrar(buscar.getString("pin")), buscar.getString("estatus"));
                cuentaBancariaCadena.add(cuentaBanc);
            }
        }
        catch(SQLException e){
            return new ArrayList<>();
        }
        return cuentaBancariaCadena;
    
    }
    
    
    public static Persona compararPersonaConCuenta(String pNumCuenta){
        conexionBD.conexionDataBase();
        ResultSet resultado = conexionBD.inquiry("select * from PersonaCuenta where numeroCuenta = '" + pNumCuenta + "'");
        try{
            
            while(resultado.next()){
                ResultSet resuPersona = conexionBD.inquiry("select * from Persona where codigo = '" + resultado.getString("codigoPersona") + "'");
                while(resuPersona.next()){
                    Persona cliente = new Persona(resuPersona.getString("primerApellido"), resuPersona.getString("segundoApellido"),resuPersona.getString("nombre"),
                        Integer.parseInt(resuPersona.getString("identificacion")),LocalDate.parse(resuPersona.getString("fechaNacimiento"), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        Integer.parseInt(resuPersona.getString("telefono")), resuPersona.getString("correo"));
                       cliente.setCodigo(resuPersona.getString("codigo"));
                    return cliente;
                }
            }
        }
        catch(SQLException e){
            return null;
        }
        return null;
    }
    
    public static Persona compararPersonaConCuentaCLI(String pNumCuenta){
        conexionBD.conexionDataBase();
        ResultSet resultado = conexionBD.inquiry("select * from PersonaCuenta where numeroCuenta = '" + Encriptar.cifrar(pNumCuenta) + "'");
        try{
            
            while(resultado.next()){
                ResultSet resuPersona = conexionBD.inquiry("select * from Persona where codigo = '" + resultado.getString("codigoPersona") + "'");
                while(resuPersona.next()){
                    Persona cliente = new Persona(resuPersona.getString("primerApellido"), resuPersona.getString("segundoApellido"),resuPersona.getString("nombre"),
                        Integer.parseInt(resuPersona.getString("identificacion")),LocalDate.parse(resuPersona.getString("fechaNacimiento"), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        Integer.parseInt(resuPersona.getString("telefono")), resuPersona.getString("correo"));
                       cliente.setCodigo(resuPersona.getString("codigo"));
                    return cliente;
                }
            }
        }
        catch(SQLException e){
            return null;
        }
        return null;
    }
    
    public static void depositarColones(String monto,CuentaBancaria cuenta){
        
        double montoTotal = Double.parseDouble(monto);
        double cargo = 0;
        double saldoTotal = 0;
        boolean huboCargo = false;
        
        if(OperacionBD.numOperacionEnCuenta(Encriptar.cifrar(String.valueOf(cuenta.getNumCuenta()))) >= 3){
            cargo = montoTotal * 0.02;
            saldoTotal = (montoTotal+cuenta.getSaldo())  - cargo;
            huboCargo = true;
        }
        Operacion nuevo = new Operacion("depositos","colones",huboCargo,montoTotal,LocalDate.now());
        OperacionBD.realizarOperacionEnBD(nuevo, Encriptar.cifrar(String.valueOf(cuenta.getNumCuenta())));
        conexionBD.conexionDataBase();
        conexionBD.ejecutarSentSQL("update Cuenta set saldo = " + String.valueOf(saldoTotal) + 
                " where numeroCuenta = '" + Encriptar.cifrar(String.valueOf(cuenta.getNumCuenta())) + "'");
        conexionBD.salirBD();
    }
    
    public static void retirarColones(String monto, CuentaBancaria cuenta){
        int saldoTotal = (int) (cuenta.getSaldo() - Integer.parseInt(monto));
        conexionBD.conexionDataBase();
        conexionBD.ejecutarSentSQL("update Cuenta set saldo = " + String.valueOf(saldoTotal) + 
                " where numeroCuenta = '" + Encriptar.cifrar(String.valueOf(cuenta.getNumCuenta())) + "'");
        conexionBD.salirBD();
    }

             
}

