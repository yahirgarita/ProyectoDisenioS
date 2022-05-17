/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorescli;

import cli.ConsultarEstatusCuentaCLI;
import java.io.IOException;
import java.time.LocalDate;
import logicadeaccesoadatos.CuentaBD;
import logicadeaccesoadatos.OperacionBD;
import logicadenegocios.CuentaBancaria;
import logicadenegocios.Operacion;
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
       /*Operacion oper = new Operacion("consultas", "No aplica", false,0, LocalDate.now());
        OperacionBD.realizarOperacionEnBD(oper,Encriptar.cifrar(numeroCuenta));*/
    }
    
}
