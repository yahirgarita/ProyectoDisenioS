package controladores;

import gui.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Objects;
import javax.mail.MessagingException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import logicadeaccesoadatos.*;
import logicadenegocios.*;
import util.Email;
import util.Encriptar;
import validaciones.*;
import util.SMS;
import validaciones.*;
/**
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 *
 * @version 1.0
 */
public class ControladorRetiroColones implements ActionListener{
    public RetiroColonesPaso1 retiroColones1;
    public RetiroColonesPaso2 retiroColones2;
    public RetiroColonesPaso3 retiroColones3;
    public RetiroColonesPaso4 retiroColones4;
    private int attempt = 0;
    private Persona clienteActual;
    
    public ControladorRetiroColones(RetiroColonesPaso1 pRetiroColones1, RetiroColonesPaso2 pRetiroColones2, RetiroColonesPaso3 pRetiroColones3, RetiroColonesPaso4 pRetiroColones4){
        this.retiroColones1 = pRetiroColones1;
        this.retiroColones2 = pRetiroColones2;
        this.retiroColones3 = pRetiroColones3;
        this.retiroColones4 = pRetiroColones4;
        this.clienteActual = null;
        this.retiroColones1.continuarRetiroColones.addActionListener(this);
        this.retiroColones1.volverRetiroColones1.addActionListener(this);
        this.retiroColones2.continuarRetiroColones2.addActionListener(this);
        this.retiroColones2.volverRetiroColones2.addActionListener(this);
        this.retiroColones3.continuarRetiroColones3.addActionListener(this);
        this.retiroColones3.volverRetiroColones3.addActionListener(this);
        this.retiroColones4.continuarRetiroColones4.addActionListener(this);
        this.retiroColones4.volverRetiroColones4.addActionListener(this);
    }
   @Override
   public void actionPerformed(ActionEvent evento){
       switch(evento.getActionCommand()){
            case "Continuar proceso": verificarDepositoColones();
                break;
            case "Continuar": hacerDepositoColones();
                break;
            case "Verificar": hacerDepositoColones();
                break;
            case "RealizarRetiro": hacerDepositoColones();
                break;      
            case "Volver":
                controladores.ControladoresGlobales.volver();
                this.retiroColones1.setVisible(false);
                this.retiroColones2.setVisible(false);
                this.retiroColones3.setVisible(false);
                this.retiroColones4.setVisible(false);
            default:
                break;
        }
   }
   
   private void verificarNumCuentaRetiroColones(){
       CuentaBancaria cuentaBanc = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(this.retiroColones1.numCuentaRetiroColones.getText()));
       if(cuentaBanc != null && !Objects.equals(cuentaBanc.getEstatus(),"Inactiva")){
           this.retiroColones2 = new RetiroColonesPaso2();
           this.retiroColones2.continuarRetiroColones2.addActionListener(this);
           this.retiroColones2.volverRetiroColones2.addActionListener(this);
           this.retiroColones2.jLabel3.setText(String.valueOf(cuentaBanc.getNumCuenta()));
           this.retiroColones2.setVisible(true);
           this.retiroColones1.setVisible(false);
       }
       else{
           JOptionPane.showMessageDialog(null,"La cuenta que indico está inactiva o no existe, cambbie el número de cuenta");
       }
   }
   private void verificarPinCuentaRetiroColones() throws MessagingException{
       CuentaBancaria cuentaBanc = CuentaBD.recuperarCuentaPorNumPin(Encriptar.cifrar(this.retiroColones2.jLabel3.getText()), 
               Encriptar.cifrar(this.retiroColones2.pinCuentaRetiroColones.getText()));
       Persona comparacionPersonaCuenta = CuentaBD.compararPersonaConCuenta(Encriptar.cifrar(retiroColones1.numCuentaRetiroColones.getText()));
       if(cuentaBanc != null){
           this.retiroColones3 = new RetiroColonesPaso3();
           this.retiroColones3.continuarRetiroColones3.addActionListener(this);
           this.retiroColones3.volverRetiroColones3.addActionListener(this);
           this.retiroColones3.palabraMsgTelefono = SMS.enviarSMS(String.valueOf(comparacionPersonaCuenta.getNumTelefonico()));
           this.retiroColones3.setVisible(true);
           this.retiroColones2.setVisible(false);
           attempt = 0;
       }
       else{
           JOptionPane.showMessageDialog(null, "El pin que ingreso es incorrecto");
           attempt ++;
           comprobrarIntentos(Encriptar.cifrar(this.retiroColones2.jLabel3.getText()), this.retiroColones2, "La cuenta se ha desactivado por fallar repetidamente el Pin");
       }
       
   }
   
   private void verificarPalabraCorrectaRetiro() throws MessagingException{
       Persona comparacionPersonaCuenta = CuentaBD.compararPersonaConCuenta(Encriptar.cifrar(retiroColones1.numCuentaRetiroColones.getText()));
       if(Objects.equals(this.retiroColones3.msgTelRetiroColones, this.retiroColones3.palabraMsgTelefono)){
           this.retiroColones4 = new RetiroColonesPaso4();
           this.retiroColones4.continuarRetiroColones4.addActionListener(this);
           this.retiroColones4.volverRetiroColones4.addActionListener(this);
           this.retiroColones4.jLabel1.setText(this.retiroColones2.jLabel3.getText());
           this.retiroColones4.setVisible(true);
           this.retiroColones3.setVisible(false);
           attempt =0;
       }
       else{
           JOptionPane.showMessageDialog(null,"La palabra que ingreso es incorrecta, intente nuevametne");
           attempt ++;
           comprobrarIntentos(Encriptar.cifrar(this.retiroColones2.jLabel3.getText()),this.retiroColones3,"Se ha inactivado la cuenta por fallar repetidamente la palabra clave");
           this.retiroColones3.palabraMsgTelefono = SMS.enviarSMS(String.valueOf(comparacionPersonaCuenta.getNumTelefonico()));
       }
   }
   
   private void realizarRetiroColones(){
       if(ValidarTipoDeDato.validarEsEntero(this.retiroColones4.montoRetiroColones.getText())){
           if(Validar.(Double.parseDouble(this.retiroColones4.montoRetiroColones.getText()), this.retiroColones4.jLabel1.getText())){
               
           }
       }
   }
   
   
   private  void comprobrarIntentos(String pNumCuenta, JFrame frame, String pMsg) throws MessagingException{
        Persona comparacionPersonaCuenta = CuentaBD.compararPersonaConCuenta(Encriptar.cifrar(this.retiroColones2.jLabel3.getText()));
        if(attempt == 2){
            CuentaBD.modificarEstado(pNumCuenta, "Inactiva");
            attempt = 0;
            JOptionPane.showMessageDialog(null, pMsg);
            Email.enviarEmail(comparacionPersonaCuenta.getCorreoPersona(), pMsg);
            frame.dispose();
            controladores.ControladoresGlobales.volver();
        }
    }
   
}
