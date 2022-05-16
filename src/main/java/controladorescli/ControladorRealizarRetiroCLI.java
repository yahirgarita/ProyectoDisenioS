/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorescli;

import cli.RealizarRetiroCLI;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import javax.mail.MessagingException;
import logicadeaccesoadatos.CuentaBD;
import logicadeaccesoadatos.OperacionBD;
import logicadenegocios.CuentaBancaria;
import logicadenegocios.Operacion;
import logicadenegocios.Persona;
import util.Email;
import util.Encriptar;
import util.SMS;
import util.TipoCambio;

/**
 *
 * @author Jimmy
 */
public class ControladorRealizarRetiroCLI {
    
    private RealizarRetiroCLI vista;
    private TipoCambio tipoCambio;
    
    public ControladorRealizarRetiroCLI(RealizarRetiroCLI vista){
        this.vista = vista;
        this.tipoCambio = new TipoCambio();
    }
    
    public void realizarRetiroColones() throws IOException, MessagingException{
        String numeroCuenta = this.vista.realizarRetiroPedirCuenta();
        String mensaje ="";
        CuentaBancaria cuentaBanc = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(numeroCuenta));
        if(cuentaBanc != null && Objects.equals(cuentaBanc.getEstatus(), "Inactiva")){  
            mensaje = "Cuenta se encuentra inactiva";
            this.vista.retiroRealizado(mensaje);
            return;
        }
        String pinActual;
        int intentos = 0;
        while(intentos < 2){
            pinActual = this.vista.realizarRetiroPedirPin();
            if(pinActual.equals(cuentaBanc.getPin())){
                if(verificarPalabraCorrectaRetiro(numeroCuenta)){
                    String monto = this.vista.realizarRetiroPedirMonto(Encriptar.cifrar(numeroCuenta));
                    
                    if(OperacionBD.numOperacionEnCuenta(Encriptar.cifrar(numeroCuenta)) >= 3){  
                   
                        double comision = Double.parseDouble(monto) * 0.02;
                        while(Double.parseDouble(monto) + comision > cuentaBanc.getSaldo() ){
                            this.vista.retiroError("Saldo no alcanza para pagar comisiones");
                            monto = this.vista.realizarRetiroPedirMonto(Encriptar.cifrar(numeroCuenta));
                        }
                        CuentaBD.agregarComision(Encriptar.cifrar(numeroCuenta), comision, "retiros");
                        Double montoTotal = Operacion.calcularComision(Double.parseDouble(monto));

                        CuentaBD.quitarSaldoCuenta(Encriptar.cifrar(String.valueOf(montoTotal)), Encriptar.cifrar(numeroCuenta));
                        Operacion oper = new Operacion("retiros", "Colones", true,Double.parseDouble(monto),LocalDate.now());
                        OperacionBD.realizarOperacionEnBD(oper, Encriptar.cifrar(numeroCuenta));

                        CuentaBancaria clienteActual = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(numeroCuenta));
                        mensaje = "Estimado usario, el monto de este retiro es "+ monto + " colones\n" +
                                "[El monto cobrado por concepto de comisión fue de " + comision + " colones, que\n" + "fueron rebajados automáticament de su saldo actual] \n" +
                                "[Su saldo actual es de: '" + Math.round(clienteActual.getSaldo()*100)/100  + "']";
                        this.vista.retiroRealizado(mensaje);
                        return;
                    }
                    else{
                        CuentaBD.quitarSaldoCuenta(Encriptar.cifrar(monto), Encriptar.cifrar(numeroCuenta));
                        Operacion oper = new Operacion("retiros", "Colones", false,Double.parseDouble(monto),LocalDate.now());
                        OperacionBD.realizarOperacionEnBD(oper,Encriptar.cifrar(numeroCuenta));
                        CuentaBancaria clienteActual = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(numeroCuenta));
                        mensaje = "Estimado usuario, el monto de este retiro es " + monto + " colones \n" +
                                "[El monto cobrado por concepto de comisión fue de 0 colones, que fueron rebajados automáticamente de su saldo actual\n" +
                                "[Su saldo actual es de: '" + Math.round(clienteActual.getSaldo()*100)/100  + "']";
                        this.vista.retiroRealizado(mensaje);
                        return;
                    }
                }
                else{
                    break;
                }
            }
            
            intentos++;
        }
        System.out.println("Su cuenta se inactivo");
        //CuentaBD.modificarEstado(Encriptar.cifrar(numeroCuenta), "Inactiva");
        Persona comparacionPersonaCuenta = CuentaBD.compararPersonaConCuenta(Encriptar.cifrar(numeroCuenta));
        Email.enviarEmail(comparacionPersonaCuenta.getCorreoPersona(), "Su cuenta a pasado a estar Inactiva por fallar el PIN");
    }
    
    public void realizarRetiroDolares() throws IOException, MessagingException{
        String numeroCuenta = this.vista.realizarRetiroPedirCuenta();
        String mensaje ="";
        CuentaBancaria cuentaBanc = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(numeroCuenta));
        if(cuentaBanc != null && Objects.equals(cuentaBanc.getEstatus(), "Inactiva")){  
            mensaje = "Cuenta se encuentra inactiva";
            this.vista.retiroRealizado(mensaje);
            return;
        }
        String pinActual;
        int intentos = 0;
        while(intentos < 2){
            pinActual = this.vista.realizarRetiroPedirPin();
            if(pinActual.equals(cuentaBanc.getPin())){
                if(verificarPalabraCorrectaRetiro(numeroCuenta)){
                    String monto = this.vista.realizarRetiroPedirMonto(Encriptar.cifrar(numeroCuenta));
                    double montoEnColones = Double.parseDouble(monto) * tipoCambio.getVenta();
                    if(OperacionBD.numOperacionEnCuenta(Encriptar.cifrar(numeroCuenta)) >= 3){
                   
                        double comision = montoEnColones * 0.02;
                        while(montoEnColones + comision > cuentaBanc.getSaldo() ){
                            this.vista.retiroError("Saldo no alcanza para pagar comisiones");
                            monto = this.vista.realizarRetiroPedirMontoDolares(Encriptar.cifrar(numeroCuenta));
                            montoEnColones = Double.parseDouble(monto) * tipoCambio.getVenta();
                        }
                        CuentaBD.agregarComision(Encriptar.cifrar(numeroCuenta), comision, "retiros");
                        Double montoTotal = Operacion.calcularComision(montoEnColones);

                        CuentaBD.quitarSaldoCuenta(Encriptar.cifrar(String.valueOf(montoTotal)), Encriptar.cifrar(numeroCuenta));
                        Operacion oper = new Operacion("retiros", "Dólares", true,Double.parseDouble(monto),LocalDate.now());
                        OperacionBD.realizarOperacionEnBD(oper, Encriptar.cifrar(numeroCuenta));

                        CuentaBancaria clienteActual = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(numeroCuenta));
                        mensaje = "Estimado usuario, el monto de este retiro es " + monto + " colones \n" +
                                "[Según el BCCR, el tipo de cambio de venta del dólar de hoy es: " + tipoCambio.getVenta() + "]\n"+
                                "[El monto equivalente de su retiro es "+ Math.round(montoEnColones*100)/100 + " colones]\n"+
                                "[El monto cobrado por concepto de comisión fue de " + comision + "colones, que fueron rebajados automáticamente de su saldo actual\n";
                        this.vista.retiroRealizado(mensaje);
                        return;
                    }
                    else{
                        CuentaBD.quitarSaldoCuenta(Encriptar.cifrar(monto), Encriptar.cifrar(numeroCuenta));
                        Operacion oper = new Operacion("retiros", "Dólares", false,Double.parseDouble(monto),LocalDate.now());
                        OperacionBD.realizarOperacionEnBD(oper,Encriptar.cifrar(numeroCuenta));
                        
                        CuentaBancaria clienteActual = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(numeroCuenta));
                        mensaje = "Estimado usuario, el monto de este retiro es " + monto + " colones \n" +
                                "[Según el BCCR, el tipo de cambio de venta del dólar de hoy es: " + tipoCambio.getVenta() + "]\n"+
                                "[El monto equivalente de su retiro es "+ Math.round(montoEnColones*100)/100 + " colones]\n"+
                                "[El monto cobrado por concepto de comisión fue de 0 colones, que fueron rebajados automáticamente de su saldo actual\n";
                                
                        this.vista.retiroRealizado(mensaje);
                        return;
                    }
                }
                else{
                    break;
                }
            }
            
            intentos++;
        }
        System.out.println("Su cuenta se inactivo");
        //CuentaBD.modificarEstado(Encriptar.cifrar(numeroCuenta), "Inactiva");
        Persona comparacionPersonaCuenta = CuentaBD.compararPersonaConCuenta(Encriptar.cifrar(numeroCuenta));
        Email.enviarEmail(comparacionPersonaCuenta.getCorreoPersona(), "Su cuenta a pasado a estar Inactiva por fallar el PIN");
    }
    
    private boolean verificarPalabraCorrectaRetiro(String cuenta) throws MessagingException, IOException{
        
        Persona comparacionPersonaCuenta = CuentaBD.compararPersonaConCuenta(Encriptar.cifrar(cuenta));
        String mensajeTexto = SMS.enviarSMS(String.valueOf(comparacionPersonaCuenta.getNumTelefonico()));
        String mensajeTextoDigitado = this.vista.realizarRetiroPedirSMS();
        int intentos = 0;
        while(intentos < 2){
            if(Objects.equals(mensajeTexto, mensajeTextoDigitado)){
                return true;
            }
            else{
                mensajeTexto = SMS.enviarSMS(String.valueOf(comparacionPersonaCuenta.getNumTelefonico()));
                mensajeTextoDigitado = this.vista.realizarRetiroPedirSMS();
            }
            intentos++;
        }
        return false;
    }
    
    public static void main(String args[]) throws IOException, MessagingException{
        RealizarRetiroCLI vista = new RealizarRetiroCLI();
        ControladorRealizarRetiroCLI controlador = new ControladorRealizarRetiroCLI(vista);
        controlador.realizarRetiroColones();
    }
}
