/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorescli;

import cli.ConsultarSaldoActualCLI;
import java.io.IOException;
import javax.mail.MessagingException;
import logicadeaccesoadatos.CuentaBD;
import logicadenegocios.CuentaBancaria;
import logicadenegocios.Persona;
import util.Email;
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
    
    public void consultarSaldoActual() throws IOException, MessagingException{
        String numeroCuenta = this.vista.consultarSaldoActual();
        if(Validar.validarEstatusInactivo(Encriptar.cifrar(numeroCuenta))){
            this.vista.cuentaInactiva();
            return;
        }
        CuentaBancaria cuenta = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(numeroCuenta));
        int intentos = 0;
        String pinActual;
        while(intentos < 2){
            pinActual = this.vista.consultarSaldoActualPedirPin();
            if(pinActual.equals(cuenta.getPin())){
                this.vista.mostrarSaldoActualColones(cuenta.getSaldo());
                return;
            }
            intentos++;
        }
        System.out.println("Su cuenta se inactivo");
        CuentaBD.modificarEstado(Encriptar.cifrar(numeroCuenta), "Inactiva");
        Persona comparacionPersonaCuenta = CuentaBD.compararPersonaConCuenta(Encriptar.cifrar(numeroCuenta));
        Email.enviarEmail(comparacionPersonaCuenta.getCorreoPersona(), "Su cuenta a pasado a estar Inactiva por fallar el PIN");
    }
    
    public void consultarSaldoActualDolares() throws IOException, MessagingException{        
        
        String numeroCuenta = this.vista.consultarSaldoActual();
        if(Validar.validarEstatusInactivo(Encriptar.cifrar(numeroCuenta))){
            this.vista.cuentaInactiva();
            return;
        }
        CuentaBancaria cuenta = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(numeroCuenta));
        int intentos = 0;
        String pinActual;
        while(intentos < 2){
            pinActual = this.vista.consultarSaldoActualPedirPin();
            if(pinActual.equals(cuenta.getPin())){
                double precioDolar = new TipoCambio().getCompra();
                double saldoDolar = cuenta.getSaldo() / precioDolar;
                this.vista.mostrarSaldoActualDolares(saldoDolar, precioDolar);
                return;
            }
            intentos++;
        }
        System.out.println("Su cuenta se inactivo");
        CuentaBD.modificarEstado(Encriptar.cifrar(numeroCuenta), "Inactiva");
        Persona comparacionPersonaCuenta = CuentaBD.compararPersonaConCuenta(Encriptar.cifrar(numeroCuenta));
        Email.enviarEmail(comparacionPersonaCuenta.getCorreoPersona(), "Su cuenta a pasado a estar Inactiva por fallar el PIN");
    }

}
