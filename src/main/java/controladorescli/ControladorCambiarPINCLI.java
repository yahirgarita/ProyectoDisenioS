/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorescli;

import cli.CambiarPinCLI;
import java.io.IOException;
import javax.mail.MessagingException;
import logicadeaccesoadatos.CuentaBD;
import logicadenegocios.CuentaBancaria;
import logicadenegocios.Persona;
import util.Email;
import util.Encriptar;
import validaciones.Validar;

/**
 *
 * @author Jimmy
 */
public class ControladorCambiarPINCLI {
    
    private CambiarPinCLI vista;
    
    public ControladorCambiarPINCLI(CambiarPinCLI vista){
        this.vista = vista;
    }
    public void cambiarPinPedirCuenta() throws IOException, MessagingException{
        String numeroCuenta = this.vista.cambiarPinPedirCuenta();
        if(Validar.validarEstatusInactivo(Encriptar.cifrar(numeroCuenta))){
            this.vista.cuentaInactiva();
            return;
        }
        CuentaBancaria cuenta = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(numeroCuenta));
        String pinActual;
        String pinNuevo ;
        int intentos = 0;
        
        while(intentos < 2){
            pinActual = this.vista.cambiarPinPedirPinActual();
            if(pinActual.equals(cuenta.getPin())){
                pinNuevo = this.vista.cambiarPinPedirPinNuevo();
                CuentaBD.cambiarPinCuenta(Encriptar.cifrar(numeroCuenta), Encriptar.cifrar(pinNuevo));
                this.vista.cambiarPinCompletado(numeroCuenta);
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
