/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorescli;

import cli.RealizarDepositoCLI;
import java.io.IOException;
import java.time.LocalDate;
import logicadeaccesoadatos.CuentaBD;
import logicadeaccesoadatos.OperacionBD;
import logicadenegocios.CuentaBancaria;
import util.Encriptar;

/**
 *
 * @author Jimmy
 */
public class ControladorRealizarDepositoCLI {
    
    private RealizarDepositoCLI vista;
    
    public ControladorRealizarDepositoCLI(){
        
        vista = new RealizarDepositoCLI();
    }
    
    public void realizarDepositoColones() throws IOException{
        
        String[] informacion = this.vista.realizarDepositoColones();
        CuentaBancaria cuenta = CuentaBD.recuperarCuentaXNumCLI(informacion[0]);
        String mensajeExito = CuentaBD.depositarColones(informacion[1], cuenta);
        this.vista.depositoRealizado(mensajeExito);
    }
    
    public void realizarDepositoDolares() throws IOException{
        
        String[] informacion = this.vista.realizarDepositoDolares();
        CuentaBancaria cuenta = CuentaBD.recuperarCuentaXNumCLI(informacion[0]);
        String mensajeExito = CuentaBD.depositarDolares(informacion[1], cuenta);
        this.vista.depositoRealizado(mensajeExito);
    }
    
    public static void main(String arg[]) throws IOException{
        ControladorRealizarDepositoCLI nuevo = new ControladorRealizarDepositoCLI();
        nuevo.realizarDepositoDolares();
        /*int cont = OperacionBD.numOperacionEnCuenta(Encriptar.cifrar("583542"));
        System.out.println(cont);*/
        
    }
}
