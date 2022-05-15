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
import logicadenegocios.Operacion;
import util.Encriptar;
import validaciones.ValidarTipoDeDato;

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
         
        String monto = informacion[1];
        String numeroCuenta = informacion[0];
        String mensaje ="";
            
        if(OperacionBD.numOperacionEnCuenta(Encriptar.cifrar(numeroCuenta))>= 3){
            double montoConvDouble = Double.parseDouble(monto);
            double comision = montoConvDouble * 0.02;

            CuentaBD.agregarComision(Encriptar.cifrar(numeroCuenta),comision);
            montoConvDouble = Operacion.calcularComision(montoConvDouble);
            CuentaBD.actualizarEstatus(Encriptar.cifrar(String.valueOf(montoConvDouble)),Encriptar.cifrar(numeroCuenta));

            Operacion oper = new Operacion("depósitos","Colones", true, Double.parseDouble(monto), LocalDate.now());
            OperacionBD.realizarOperacionEnBD(oper, Encriptar.cifrar(numeroCuenta));
            mensaje = "Estimado usuario, se ha realizado correctamente el deposito " + monto + " colones\n" +
                "[El monto real deposito a su cuenta " + numeroCuenta + " es de" + montoConvDouble + " colones]\n" +
                "[El monto cobrado por concepto añadigo de comisión fue de " + comision + " colones, que fueron rebajados de forma automatica de su salgo actual]";
        }
        else{  
            CuentaBD.actualizarEstatus(Encriptar.cifrar(String.valueOf(monto)),Encriptar.cifrar(numeroCuenta));
            Operacion oper = new Operacion("depósitos", "colones", false, Double.parseDouble(monto), LocalDate.now());
            OperacionBD.realizarOperacionEnBD(oper,Encriptar.cifrar(numeroCuenta));
            mensaje = "Estimado usuario, se ha realizado correctamente el deposito" + monto + "colones\n" +                   
                "[El monto real deposito a su cuenta" + numeroCuenta + " es de" + monto + " colones]\n" +
                "[El monto cobrado por concepto añadigo de comisión fue de 0 colones, que fueron rebajados de forma automatica de su salgo actual]";
            }
        this.vista.depositoRealizado(mensaje);
    }
  
    
    public void realizarDepositoDolares() throws IOException{
        
        String[] informacion = this.vista.realizarDepositoDolares();
        CuentaBancaria cuenta = CuentaBD.recuperarCuentaXNumCLI(informacion[0]);
        String mensajeExito = CuentaBD.depositarDolares(informacion[1], cuenta);
        this.vista.depositoRealizado(mensajeExito);
    }
    
    public static void main(String arg[]) throws IOException{
        ControladorRealizarDepositoCLI nuevo = new ControladorRealizarDepositoCLI();
        nuevo.realizarDepositoColones();
        /*int cont = OperacionBD.numOperacionEnCuenta(Encriptar.cifrar("583542"));
        System.out.println(cont);*/
        
    }
}
