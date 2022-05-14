/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorescli;

import cli.ConsultarCuentasCLI;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import logicadeaccesoadatos.CuentaBD;
import logicadenegocios.CuentaBancaria;
import logicadenegocios.Persona;

/**
 *
 * @author Jimmy
 */
public class ControladorConsultarCuentasCLI {
    
    private ArrayList<CuentaBancaria> cuentas;
    private ConsultarCuentasCLI vista;
    
    public ControladorConsultarCuentasCLI(){
        this.cuentas = CuentaBD.recuperarCuentas();
        this.vista = new ConsultarCuentasCLI();
    }
    public void listarCuentas(){
        organizarCuentas();
        for(int i = 0;i<this.cuentas.size();i++){
            Persona duenio = CuentaBD.compararPersonaConCuentaCLI(String.valueOf(cuentas.get(i).getNumCuenta()));
            this.vista.listarCuentas(cuentas.get(i).getNumCuenta(), cuentas.get(i).getEstatus(), cuentas.get(i).getSaldo(), 
                    duenio.getIdPersona(),duenio.getNombre()+ " " + duenio.getPrimerApellido() + " " + duenio.getSegundoApellido());
        }      
    }
    
    public void seleccionarCuenta() throws IOException{
        String numeroCuenta = this.vista.seleccionarCuenta();
        CuentaBancaria cuenta = CuentaBD.recuperarCuentaXNumCLI(numeroCuenta);
        this.vista.mostrarCuenta(cuenta.toString());
    }
    
    private void organizarCuentas(){
        cuentas.sort(Comparator.comparing(CuentaBancaria::getSaldo).reversed());
    }
    
    public static void main (String args[]) throws IOException{
        ControladorConsultarCuentasCLI nuevo = new ControladorConsultarCuentasCLI();
        nuevo.listarCuentas();
        nuevo.seleccionarCuenta();
    }
}
