/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorescli;

import cli.RegistrarCuentaCLI;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import logicadeaccesoadatos.CuentaBD;
import logicadeaccesoadatos.PersonaBD;
import logicadenegocios.CuentaBancaria;
import logicadenegocios.Persona;
import util.Encriptar;
import validaciones.ValidarTipoDeDato;

/**
 *
 * @author Jimmy
 */
public class ControladorRegistrarCuentaCLI {
    
    private RegistrarCuentaCLI ventana;
    
    public ControladorRegistrarCuentaCLI(RegistrarCuentaCLI ventana){
        this.ventana = ventana;
    }
    
    public void registrarCuenta() throws IOException{
        String [] informacion = ventana.registrarCuenta();
        Persona usuario = PersonaBD.recuperarClientePorID(Integer.parseInt(informacion[0]));
        CuentaBancaria cuenta = new CuentaBancaria(CuentaBancaria.generarNumCuenta(),Double.parseDouble(informacion[2]),informacion[1],LocalDate.now());
        CuentaBD.registrarCuentaEnBD(cuenta, usuario.getCodigo());
        cuenta.msgCreacion();
        
    }
    
    public static void main(String[] args) throws IOException, SQLException{

        RegistrarCuentaCLI ventana = new RegistrarCuentaCLI();
        RegistrarCuentaCLI vista = new RegistrarCuentaCLI();
        ControladorRegistrarCuentaCLI nuevo = new ControladorRegistrarCuentaCLI(ventana);
        nuevo.registrarCuenta();
    }
}
