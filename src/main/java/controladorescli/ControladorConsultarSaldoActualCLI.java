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
import util.Encriptar;
import util.TipoCambio;
import validaciones.Validar;

/**
 *
 * @author Jimmy
 */
public class ControladorConsultarSaldoActualCLI {
    
    private ConsultarSaldoActualCLI vista;
    
    public ControladorConsultarSaldoActualCLI(ConsultarSaldoActualCLI vista){
        this.vista = vista;
    }
    
    public void consultarSaldoActual() throws IOException{
        String numeroCuenta = this.vista.consultarSaldoActual();
        if(Validar.validarEstatusInactivo(Encriptar.cifrar(numeroCuenta))){
            this.vista.cuentaInactiva();
            return;
        }
        CuentaBancaria cuenta = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(numeroCuenta));
        this.vista.mostrarSaldoActualColones(cuenta.getSaldo());
    }
    
    public void consultarSaldoActualDolares() throws IOException{
        String numeroCuenta = this.vista.consultarSaldoActual();
        if(Validar.validarEstatusInactivo(Encriptar.cifrar(numeroCuenta))){
            this.vista.cuentaInactiva();
            return;
        }
        CuentaBancaria cuenta = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(numeroCuenta));
        double precioDolar = new TipoCambio().getCompra();
        double saldoDolar = cuenta.getSaldo() * precioDolar;
        this.vista.mostrarSaldoActualDolares(saldoDolar, precioDolar);
    }
    
}
