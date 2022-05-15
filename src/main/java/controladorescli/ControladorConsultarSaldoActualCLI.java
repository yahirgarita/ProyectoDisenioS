/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorescli;

import cli.ConsultarSaldoActualCLI;
import java.io.IOException;
import logicadeaccesoadatos.CuentaBD;
import logicadenegocios.CuentaBancaria;
import util.TipoCambio;

/**
 *
 * @author Jimmy
 */
public class ControladorConsultarSaldoActualCLI {
    
    private ConsultarSaldoActualCLI vista;
    
    public ControladorConsultarSaldoActualCLI(){
        vista = new ConsultarSaldoActualCLI();
    }
    
    public void consultarSaldoActual() throws IOException{
        String numeroCuenta = this.vista.consultarSaldoActual();
        CuentaBancaria cuenta = CuentaBD.recuperarCuentaXNumCLI(numeroCuenta);
        this.vista.mostrarSaldoActualColones(cuenta.getSaldo());
    }
    
    public void consultarSaldoActualDolares() throws IOException{
        String numeroCuenta = this.vista.consultarSaldoActual();
        CuentaBancaria cuenta = CuentaBD.recuperarCuentaXNumCLI(numeroCuenta);
        double precioDolar = new TipoCambio().getCompra();
        double saldoDolar = cuenta.getSaldo() * precioDolar;
        this.vista.mostrarSaldoActualDolares(saldoDolar, precioDolar);
    }
    
    public static void main (String [] args) throws IOException{
        ControladorConsultarSaldoActualCLI nuevo = new ControladorConsultarSaldoActualCLI();
        nuevo.consultarSaldoActual();
    }
}
