package controladores;

import gui.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Objects;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import logicadenegocios.*;
import logicadeaccesoadatos.*;
import util.*;
import validaciones.*;
import util.Email;
/**
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 *
 * @version 1.0
 */
public class ControladorCambiarPin implements ActionListener{
    public CambiarPinPaso1 cambiarPin1;
    public CambiarPinPaso2 cambiarPin2;
    private int attempt = 0;
    private Cuenta clienteActual;
    
    public ControladorCambiarPin(CambiarPinPaso1 pCambiarPin1, CambiarPinPaso2 pCambiarPin2){
        this.cambiarPin1 = pCambiarPin1;
        this.cambiarPin2 = pCambiarPin2;
        this.clienteActual = null;
        this.cambiarPin1.volverPin1.addActionListener(this);
        this.cambiarPin1.continuarPin1.addActionListener(this);
        this.cambiarPin2.botonCambiarPin2.addActionListener(this);
        this.cambiarPin2.botonVolverPin2.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent evento){
        switch(evento.getActionCommand()){
            case "Continuar proceso":verificarPin();
                break;
            case "Continuar":
                break;
            case "Volver":
                controladores.ControladoresGlobales.volver();
                this.cambiarPin1.setVisible(false);
                this.cambiarPin2.setVisible(false);
                break;
            default:
                break;
       }        
    }
    private void verificarPin(){
        CuentaBancaria cuentaBanc = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(this.cambiarPin1.numCuentaPin.getText()));
        if(cuentaBanc != null && !Objects.equals(cuentaBanc.getEstatus(),"Inactiva")){
            this.cambiarPin2 = new CambiarPinPaso2();
            this.cambiarPin2.botonCambiarPin2.addActionListener(this);
            this.cambiarPin2.botonVolverPin2.addActionListener(this);
            this.cambiarPin2.jLabel4.setText(String.valueOf(CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(this.cambiarPin1.numCuentaPin.getText())).getNumCuenta()));
            this.cambiarPin2.setVisible(true);
            this.cambiarPin1.setVisible(false);
        }   
        else {
            JOptionPane.showMessageDialog(null, "La cuenta no existe");
        }
    }
    
    private void cambiarPinPaso2(){
        CuentaBancaria cuentaBanc = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(this.cambiarPin2.jLabel4.getText()));
        if(Objects.equals(this.cambiarPin2.jLabel4.getText(),cuentaBanc.getPin())){
            if(ValidarTipoDeDato.validarFormatoPIN(this.cambiarPin2.pinActual.getText())){
                CuentaBD.cambiarPinCuenta(String.valueOf(cuentaBanc.getNumCuenta()), this.cambiarPin2.nuevoPin.getText());
                Operacion operacion = new Operacion("modificación de Pin","Colones", false, 0, LocalDate.now());
                OperacionBD.realizarOperacionEnBD(operacion, Encriptar.cifrar(this.cambiarPin2.jLabel4.getText()));
                JOptionPane.showMessageDialog(null, "Su nuevo PIN ha sido almacenado exitosamente");
                controladores.ControladoresGlobales.volver();
                this.cambiarPin2.setVisible(false);
                attempt = 0;
            }else{
                JOptionPane.showMessageDialog(null,"El formato del nuevo PIN que ingreso es incorrecto");
            }
        }else{
            JOptionPane.showMessageDialog(null, "El Pin que ingreso es erroneo, revise los datos ingresados");
            attempt++;
            comprobarIntentos(Encriptar.cifrar(String.valueOf(cuentaBanc.getNumCuenta())),this.cambiarPin2);
        }
    }
    
    private void comprobrarIntentos(String pNumCuenta, JFrame frame, String pMsg){
        
        clienteActual = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(cambiarPin1.numCuentaPin.getText()));
        Persona correoPersona = Persona(getCodigo().getText());
        if(attempt == 2){
            CuentaBD.modificarEstado(pNumCuenta, "Inactiva");
            attempt = 0;
            JOptionPane.showMessageDialog(null, pMsg);
            Email.enviarEmail(clienteActual.getCorreoElectronico(), pMsg);
            frame.dispose();
            controladores.ControladoresGlobales.volver();
        }
    }
}
