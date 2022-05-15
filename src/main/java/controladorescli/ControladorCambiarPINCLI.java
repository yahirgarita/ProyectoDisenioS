/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorescli;

import cli.CambiarPinCLI;
import java.io.IOException;
import logicadeaccesoadatos.CuentaBD;
import logicadenegocios.CuentaBancaria;
import util.Encriptar;

/**
 *
 * @author Jimmy
 */
public class ControladorCambiarPINCLI {
    
    private CambiarPinCLI vista;
    
    public ControladorCambiarPINCLI(){
        this.vista = new CambiarPinCLI();
    }
    public void cambiarPinPedirCuenta() throws IOException{
        String numeroCuenta = this.vista.cambiarPinPedirCuenta();
        CuentaBancaria cuenta = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(numeroCuenta));
        String pinActual = this.vista.cambiarPinPedirPinActual();
        String pinNuevo ;
        //System.out.println("el pin es " + pinActual + " y el otro es " + cuenta.getPin());
        if(pinActual.equals(cuenta.getPin())){
            pinNuevo = this.vista.cambiarPinPedirPinNuevo();
            CuentaBD.cambiarPinCuenta(Encriptar.cifrar(numeroCuenta), Encriptar.cifrar(pinNuevo));
            this.vista.cambiarPinCompletado(numeroCuenta);
        }
        
    }
    
    public static void main(String[] args) throws IOException{
        ControladorCambiarPINCLI nuevo = new ControladorCambiarPINCLI();
        nuevo.cambiarPinPedirCuenta();
    }
}
