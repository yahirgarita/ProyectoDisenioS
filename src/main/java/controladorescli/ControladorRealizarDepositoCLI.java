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
        CuentaBD.depositarColones(informacion[1], cuenta);
    }
    
    public static void main(String arg[]){
        double monto = 17.12 * 0.02;
        String montoS = String.valueOf(monto);
        System.out.println(LocalDate.now());
    }
}
