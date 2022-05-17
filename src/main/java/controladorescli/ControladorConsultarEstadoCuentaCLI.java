/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorescli;

import cli.ConsultarEstadoCuentaCLI;
import java.io.IOException;
import java.util.ArrayList;
import javax.mail.MessagingException;
import logicadeaccesoadatos.CuentaBD;
import logicadeaccesoadatos.OperacionBD;
import logicadenegocios.CuentaBancaria;
import logicadenegocios.Operacion;
import logicadenegocios.Persona;
import util.Email;
import util.Encriptar;
import util.TipoCambio;
import validaciones.Validar;

/**
 *
 * @author Jimmy
 */
public class ControladorConsultarEstadoCuentaCLI {
    private ConsultarEstadoCuentaCLI vista;
    private TipoCambio tipoCambio;
    
    public ControladorConsultarEstadoCuentaCLI(ConsultarEstadoCuentaCLI vista){
        this.vista = vista;
        this.tipoCambio =  new TipoCambio();
    }
    
    public void consultarEstadoCuentaColones() throws IOException, MessagingException{
        String numeroCuenta = this.vista.consultarEstadoCuentaPedirCuenta();
        if(Validar.validarEstatusInactivo(Encriptar.cifrar(numeroCuenta))){
            this.vista.cuentaInactiva();
            return;
        }
        CuentaBancaria cuenta = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(numeroCuenta));
        int intentos = 0;
        String pinActual;
        while(intentos < 2){
            pinActual = this.vista.consultarEstadoCuentaPedirPin();
            
            if(pinActual.equals(cuenta.getPin())){
                ArrayList<Operacion> operaciones = OperacionBD.obtenerOperacionesPorNumCuenta(Encriptar.cifrar(numeroCuenta));
                this.vista.mostrarEstadoCuentaTitulo();
                for(int i = 0;i < operaciones.size(); i++){
                    double monto = operaciones.get(i).getMonto();
                    if(operaciones.get(i).getMoneda().equals("Dólares")){
                        monto = operaciones.get(i).getMonto() * this.tipoCambio.getCompra();
                    }
                    this.vista.mostrarEstadoCuenta(operaciones.get(i).getFechaOperacion().toString(),operaciones.get(i).getTipo(),Math.round(monto*100.0)/100.0,operaciones.get(i).getCargo());
                }
                
                /*Operacion oper = new Operacion("consultas", "No aplica", false, 0, LocalDate.now());
                OperacionBD.realizarOperacionEnBD(oper,Encriptar.cifrar(numeroCuenta));*/
                return;
            }
            intentos++;
        }
        System.out.println("Su cuenta se inactivo");
        CuentaBD.modificarEstado(Encriptar.cifrar(numeroCuenta), "Inactiva");
        Persona comparacionPersonaCuenta = CuentaBD.compararPersonaConCuenta(Encriptar.cifrar(numeroCuenta));
        Email.enviarEmail(comparacionPersonaCuenta.getCorreoPersona(), "Su cuenta a pasado a estar Inactiva por fallar el PIN");
    }
    
    public void consultarEstadoCuentaDolares() throws IOException, MessagingException{
        String numeroCuenta = this.vista.consultarEstadoCuentaPedirCuenta();
        if(Validar.validarEstatusInactivo(Encriptar.cifrar(numeroCuenta))){
            this.vista.cuentaInactiva();
            return;
        }
        CuentaBancaria cuenta = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(numeroCuenta));
        int intentos = 0;
        String pinActual;
        while(intentos < 2){
            pinActual = this.vista.consultarEstadoCuentaPedirPin();
            
            if(pinActual.equals(cuenta.getPin())){
                ArrayList<Operacion> operaciones = OperacionBD.obtenerOperacionesPorNumCuenta(Encriptar.cifrar(numeroCuenta));
                this.vista.mostrarEstadoCuentaTitulo();
                for(int i = 0;i < operaciones.size(); i++){
                    double monto = operaciones.get(i).getMonto();
                    if(operaciones.get(i).getMoneda().equals("Colones")){
                        monto = operaciones.get(i).getMonto() / this.tipoCambio.getVenta();
                    }
                    this.vista.mostrarEstadoCuenta(operaciones.get(i).getFechaOperacion().toString(),operaciones.get(i).getTipo(),Math.round(monto*100.0)/100.0,operaciones.get(i).getCargo());
                }
                
                /*Operacion oper = new Operacion("consultas", "No aplica", false, 0, LocalDate.now());
                OperacionBD.realizarOperacionEnBD(oper,Encriptar.cifrar(numeroCuenta));*/
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
