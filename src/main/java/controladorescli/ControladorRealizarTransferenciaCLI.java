/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorescli;

import cli.RealizarTransferenciaCLI;
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

/**
 *
 * @author Jimmy
 */
public class ControladorRealizarTransferenciaCLI {
    
    private RealizarTransferenciaCLI vista;
    
    public ControladorRealizarTransferenciaCLI(RealizarTransferenciaCLI vista){
        this.vista = vista;
    }
    
    public void realizarTransferencia() throws IOException, MessagingException{
        String numeroCuenta = this.vista.realizarTranferenciaPedirCuentaOrigen();
        String mensaje ="";
        CuentaBancaria cuentaBanc = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(numeroCuenta));
        if(cuentaBanc != null && Objects.equals(cuentaBanc.getEstatus(), "Inactiva")){  
            mensaje = "Cuenta se encuentra inactiva";
            this.vista.transferenciaRealizado(mensaje);
            return;
        }
        String pinActual;
        int intentos = 0;
        while(intentos < 2){
            pinActual = this.vista.realizarTransferenciaPedirPin();
            if(pinActual.equals(cuentaBanc.getPin())){
                if(verificarPalabraCorrectaRetiro(numeroCuenta)){
                    String monto = this.vista.realizarTransferenciaPedirMonto(Encriptar.cifrar(numeroCuenta));
                    String numeroCuentaDestino = this.vista.realizarTranferenciaPedirCuentaDestino();
                    if(OperacionBD.numOperacionEnCuenta(Encriptar.cifrar(numeroCuenta)) >= 3){
                        
                        double comision = Double.parseDouble(monto) * 0.02;
                        while(Double.parseDouble(monto) + comision > cuentaBanc.getSaldo() ){
                            this.vista.transferenciaError("Saldo no alcanza para pagar comisiones");
                            monto = this.vista.realizarTransferenciaPedirMonto(Encriptar.cifrar(numeroCuenta));
                        }
                        CuentaBD.agregarComision(Encriptar.cifrar(numeroCuenta), comision, "retiros");
                        Double montoTotal = Operacion.calcularComision(Double.parseDouble(monto));

                        CuentaBD.quitarSaldoCuenta(Encriptar.cifrar(String.valueOf(montoTotal)), Encriptar.cifrar(numeroCuenta));
                        CuentaBD.actualizarSaldo(Encriptar.cifrar(monto), Encriptar.cifrar(numeroCuentaDestino));
                        Operacion operOrigen = new Operacion("retiros", "Colones", true,Double.parseDouble(monto),LocalDate.now());
                        Operacion operDestino = new Operacion("depósitos", "Colones", false,Double.parseDouble(monto),LocalDate.now());
                        OperacionBD.realizarOperacionEnBD(operOrigen, Encriptar.cifrar(numeroCuenta));
                        OperacionBD.realizarOperacionEnBD(operDestino, Encriptar.cifrar(numeroCuentaDestino));

                        mensaje = "Estimado usuario, la transferencia de fondos se ejecutó satisfactoriamente.\n" +
                                "El monto retirado de la cuenta origen y depositado en la cuenta destino es " + monto + "colones \n" +
                                "[El monto cobrado por concepto de comisión a la cuenta origen fue de: " + Math.round(comision*100)/100  + 
                                " colones, que fueron rebajados automáticamente de su saldo actual]";
                        this.vista.transferenciaRealizado(mensaje);
                        return;
                    }
                    else{
                        CuentaBD.quitarSaldoCuenta(Encriptar.cifrar(monto), Encriptar.cifrar(numeroCuenta));
                        CuentaBD.actualizarSaldo(Encriptar.cifrar(monto), Encriptar.cifrar(numeroCuentaDestino));
                        Operacion operOrigen = new Operacion("retiros", "Colones", false,Double.parseDouble(monto),LocalDate.now());
                        Operacion operDestino = new Operacion("depósitos", "Colones", false,Double.parseDouble(monto),LocalDate.now());
                        OperacionBD.realizarOperacionEnBD(operOrigen,Encriptar.cifrar(numeroCuenta));
                        OperacionBD.realizarOperacionEnBD(operDestino, Encriptar.cifrar(numeroCuentaDestino));
                       
                        mensaje = "\"Estimado usuario, la transferencia de fondos se ejecutó satisfactoriamente. \n" +
                                "El monto retirado de la cuenta origen y depositado en la cuenta destino es " + monto + "colones \n" +
                                "[El monto cobrado por concepto de comisión a la cuenta origen fue de: 0 "+ 
                                " colones, que fueron rebajados automáticamente de su saldo actual]";
                        this.vista.transferenciaRealizado(mensaje);
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
        String mensajeTextoDigitado = this.vista.realizarTransferenciaPedirSMS();
        int intentos = 0;
        while(intentos < 2){
            if(Objects.equals(mensajeTexto, mensajeTextoDigitado)){
                return true;
            }
            else{
                mensajeTexto = SMS.enviarSMS(String.valueOf(comparacionPersonaCuenta.getNumTelefonico()));
                mensajeTextoDigitado = this.vista.realizarTransferenciaPedirSMS();
            }
            intentos++;
        }
        return false;
    }
    
}
