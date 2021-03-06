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
import util.Encriptar;

/**
 *
 * @author Jimmy
 */
public class ControladorConsultarCuentasCLI {
    
    private ArrayList<CuentaBancaria> cuentas;
    private ConsultarCuentasCLI vista;
    
    public ControladorConsultarCuentasCLI(ConsultarCuentasCLI vista){
        this.cuentas = CuentaBD.recuperarCuentas();
        this.vista = vista;
    }
    public void listarCuentas() throws IOException{
        organizarCuentas();
        for(int i = 0;i<this.cuentas.size();i++){
            Persona duenio = CuentaBD.compararPersonaConCuenta(Encriptar.cifrar(String.valueOf(cuentas.get(i).getNumCuenta())));
            this.vista.listarCuentas(cuentas.get(i).getNumCuenta(), cuentas.get(i).getEstatus(), cuentas.get(i).getSaldo(), 
                    duenio.getIdPersona(),duenio.getNombre()+ " " + duenio.getPrimerApellido() + " " + duenio.getSegundoApellido());
        } 
        if(this.vista.consultarCuenta().equals("1")){
            seleccionarCuenta();
            /*Operacion oper = new Operacion("consultas", "No aplica", false, 0 , LocalDate.now());
            OperacionBD.realizarOperacionEnBD(oper,Encriptar.cifrar(numeroCuenta));*/
        }
    }
    
    private void seleccionarCuenta() throws IOException{
        String numeroCuenta = this.vista.seleccionarCuenta();
        CuentaBancaria cuenta = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(numeroCuenta));
        this.vista.mostrarCuenta(cuenta.toString());
    }
    
    private void organizarCuentas(){
        cuentas.sort(Comparator.comparing(CuentaBancaria::getSaldo).reversed());
    }

}
