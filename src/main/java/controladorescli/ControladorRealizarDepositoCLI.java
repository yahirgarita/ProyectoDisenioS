/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorescli;

import cli.RealizarDepositoCLI;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import logicadeaccesoadatos.CuentaBD;
import logicadeaccesoadatos.OperacionBD;
import logicadenegocios.CuentaBancaria;
import logicadenegocios.Operacion;
import util.Encriptar;
import util.TipoCambio;

/**
 *
 * @author Jimmy
 */
public class ControladorRealizarDepositoCLI {
    
    private RealizarDepositoCLI vista;
    private TipoCambio tipoCambio;
    
    public ControladorRealizarDepositoCLI(RealizarDepositoCLI vista){
        
        tipoCambio = new TipoCambio();
        this.vista = vista;
    }
    
    public void realizarDepositoColones() throws IOException{
        
        String numeroCuenta = this.vista.realizarDepositoPedirCuenta();
        String mensaje ="";
        CuentaBancaria cuentaBanc = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(numeroCuenta));
        if(cuentaBanc != null && Objects.equals(cuentaBanc.getEstatus(), "Inactiva")){  
            mensaje = "Cuenta se encuentra inactiva";
            this.vista.depositoRealizado(mensaje);
            return;
        }  
        String monto = this.vista.realizarDepositoPedirMonto();
        
        if(OperacionBD.numOperacionEnCuenta(Encriptar.cifrar(numeroCuenta)) >= 3){
            System.out.println("cantidad " + OperacionBD.numOperacionEnCuenta(Encriptar.cifrar(numeroCuenta)));
            double montoConvDouble = Double.parseDouble(monto);
            double comision = montoConvDouble * 0.02;

            CuentaBD.agregarComision(Encriptar.cifrar(numeroCuenta),comision,"depósitos");
            montoConvDouble = Operacion.calcularComision(montoConvDouble);
            CuentaBD.actualizarSaldo(Encriptar.cifrar(String.valueOf(montoConvDouble)),Encriptar.cifrar(numeroCuenta));


            Operacion oper = new Operacion("depósitos","Colones", true, Double.parseDouble(monto), LocalDate.now());
            OperacionBD.realizarOperacionEnBD(oper, Encriptar.cifrar(numeroCuenta));
            mensaje = "Estimado usuario, se ha realizado correctamente el deposito " + monto + " colones\n" +
                "[El monto real deposito a su cuenta " + numeroCuenta + " es de" + montoConvDouble + " colones]\n" +
                "[El monto cobrado por concepto añadigo de comisión fue de " + comision + " colones, que fueron rebajados de forma automatica de su salgo actual]";
        }
        else{  
            CuentaBD.actualizarSaldo(Encriptar.cifrar(String.valueOf(monto)),Encriptar.cifrar(numeroCuenta));
            Operacion oper = new Operacion("depósitos", "Colones", false, Double.parseDouble(monto), LocalDate.now());
            OperacionBD.realizarOperacionEnBD(oper,Encriptar.cifrar(numeroCuenta));
            mensaje = "Estimado usuario, se ha realizado correctamente el deposito " + monto + " colones\n" +                   
                "[El monto real deposito a su cuenta " + numeroCuenta + " es de " + monto + " colones]\n" +
                "[El monto cobrado por concepto añadigo de comisión fue de 0 colones, que fueron rebajados de forma automatica de su salgo actual]";
            }
        this.vista.depositoRealizado(mensaje);
    }
  
    
    public void realizarDepositoDolares() throws IOException{
        
        String numeroCuenta = this.vista.realizarDepositoPedirCuenta();
        String mensaje ="";
        CuentaBancaria cuentaBanc = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(numeroCuenta));
        if(cuentaBanc != null && Objects.equals(cuentaBanc.getEstatus(), "Inactiva")){  
            mensaje = "Cuenta se encuentra inactiva";
            this.vista.depositoRealizado(mensaje);
            return;
        }  
        String montoEnDolar = this.vista.realizarDepositoPedirMonto();
        double dolar = tipoCambio.getCompra();
        double montoColones = Double.parseDouble(montoEnDolar) * dolar;

        if(OperacionBD.numOperacionEnCuenta(Encriptar.cifrar(numeroCuenta))>= 3){
            double comision = montoColones * 0.02;
            CuentaBD.agregarComision(Encriptar.cifrar(numeroCuenta),comision,"depósitos");
            montoColones = Operacion.calcularComision(montoColones);
            CuentaBD.actualizarSaldo(Encriptar.cifrar(String.valueOf(montoColones)),Encriptar.cifrar(numeroCuenta));
            Operacion oper = new Operacion("depósitos","Dólares", true, Double.parseDouble(montoEnDolar), LocalDate.now());
            OperacionBD.realizarOperacionEnBD(oper, Encriptar.cifrar(numeroCuenta));
             
            mensaje = "Estimado usuario, se ha realizado correctamente el deposito " + montoEnDolar + " dólares\n" +
                "[Según el BCCR, el tipo de cambio de compra del dólar de hoy " + LocalDate.now().toString() + " es de: " + dolar + "]\n" +
                "[El monto equivalente en colones es: " + Math.round(((Double.parseDouble(montoEnDolar) * dolar))*100.0)/100.0 + "] \n"+
                "[El monto real depositado a su cuenta " + numeroCuenta + " es de " + Math.round(montoColones *100.0)/100.0 + " colones]\n" +
                "[El monto cobrado por concepto añadigo de comisión fue de " + Math.round(comision*100.0)/100.0 + " colones, que fueron rebajados de forma automatica de su saldo actual]";
           
        }else{
            CuentaBD.actualizarSaldo(Encriptar.cifrar(String.valueOf(montoColones)),Encriptar.cifrar(numeroCuenta));
            Operacion oper = new Operacion("depósitos", "Dólares", false, Double.parseDouble(montoEnDolar), LocalDate.now());
            OperacionBD.realizarOperacionEnBD(oper,Encriptar.cifrar(numeroCuenta));
             
            mensaje = "Estimado usuario, se ha realizado correctamente el deposito " + montoEnDolar + " dólares\n" +
                "[Según el BCCR, el tipo de cambio de compra del dólar de hoy " + LocalDate.now().toString() + " es de: " + dolar + "]\n" +
                "[El monto equivalente en colones es: " + Double.parseDouble(montoEnDolar) * dolar + "] \n"+
                "[El monto real depositado a su cuenta " +numeroCuenta + " es de " + montoColones + " colones]\n" +
                "[El monto cobrado por concepto añadigo de comisión fue de 0 colones, que fueron rebajados de forma automatica de su saldo actual]";
            }
        this.vista.depositoRealizado(mensaje);
    }

}
