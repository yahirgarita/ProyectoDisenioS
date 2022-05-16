/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorescli;

import cli.ConsultarEstatusCuentaCLI;
import java.io.IOException;
import logicadeaccesoadatos.CuentaBD;
import logicadenegocios.CuentaBancaria;
import util.Encriptar;

/**
 *
 * @author Jimmy
 */
public class ControladorConsultarEstatusCuentaCLI {
    
    private ConsultarEstatusCuentaCLI vista;
    
    public ControladorConsultarEstatusCuentaCLI(ConsultarEstatusCuentaCLI vista){
        this.vista = vista;
    }
    
    public void consultarEstatus() throws IOException{
        String numeroCuenta = this.vista.verEstatusPedirCuenta();
        CuentaBancaria cuentaBanc = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(numeroCuenta));
        String mensaje = "La cuenta número " + numeroCuenta +" tiene estatus de " + cuentaBanc.getEstatus();
        this.vista.verEstatusMostrar(mensaje);
    }
    
}
