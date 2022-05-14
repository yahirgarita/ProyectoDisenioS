package logicadeaccesoadatos;


import logicadenegocios.Persona;
import validaciones.Validar;


import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.sql.ResultSet;
import java.sql.SQLException;

import logicadenegocios.CuentaBancaria;


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
    public static ResultSet cargarTodosLosClientes(){
        conexionBD.conexionDataBase();
        return conexionBD.inquiry("select * from Persona");
    }
    
    
    public static Persona recuperarClientePorID(int id){
        conexionBD.conexionDataBase();
        ResultSet idClienteBuscar = conexionBD.inquiry("select * from Persona where identificacion = " + id);
        System.out.println(id); 
        try{
            while(idClienteBuscar.next()){
                Persona cliente = new Persona(idClienteBuscar.getString("primerApellido"), idClienteBuscar.getString("segundoApellido"),idClienteBuscar.getString("nombre"),
                        Integer.parseInt(idClienteBuscar.getString("identificacion")),LocalDate.parse(idClienteBuscar.getString("fechaNacimiento"), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        Integer.parseInt(idClienteBuscar.getString("telefono")), idClienteBuscar.getString("correo"));
                       cliente.setCodigo(idClienteBuscar.getString("codigo"));
                return cliente;
            }
            conexionBD.salirBD();
            return null;
        }
        catch(SQLException e){
            return null;
        }
    }
    
    
    public static ArrayList<CuentaBancaria> recuperarCuentasClientes(String codigo){
        ArrayList<CuentaBancaria> cuentaBancariaCadena = new ArrayList<>();
        try{
            conexionBD.conexionDataBase();
            ResultSet resultado = conexionBD.inquiry("select * from PersonaCuenta where codigoPersona = '" + codigo + "'");
            while(resultado.next()){
                CuentaBancaria cuentaBanc = CuentaBD.recuperarCuentaXNum(resultado.getString("numeroCuenta"));
                cuentaBancariaCadena.add(cuentaBanc);
            }
        }
        catch(SQLException e){
            return new ArrayList<>();
        }
        return cuentaBancariaCadena;
    }
    public static Persona saberClientePorCodigo(String pCodigo) throws SQLException{
        conexionBD.conexionDataBase();
        ResultSet resultado = conexionBD.inquiry("select * from Persona where codigo = '" + pCodigo + "'");
        while(resultado.next()){
            Persona person = new Persona(resultado.getString("primerApellido"), resultado.getString("segundoApellido"), resultado.getString("nombre"), 
                Integer.parseInt(resultado.getString("identificacion")), LocalDate.parse(resultado.getString("fechaNacimiento"), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                Integer.parseInt(resultado.getString("telefono")), resultado.getString("correo"));
            person.setCodigo(resultado.getString("codigo"));
            return person;
        }
        return null;
    }
}
