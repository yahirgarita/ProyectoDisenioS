package controladores;


import gui.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private CuentaBancaria clienteActual;
    private Menu menuInicial;
    
    public ControladorRetiroColones(RetiroColonesPaso1 pRetiroColones1, RetiroColonesPaso2 pRetiroColones2, RetiroColonesPaso3 pRetiroColones3, RetiroColonesPaso4 pRetiroColones4, Menu pMenuInicial){
        this.retiroColones1 = pRetiroColones1;
        this.retiroColones2 = pRetiroColones2;
        this.retiroColones3 = pRetiroColones3;
        this.retiroColones4 = pRetiroColones4;
        this.clienteActual = null;
        this.menuInicial = pMenuInicial;
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
            case "Continuar proceso": verificarNumCuentaRetiroColones();
                break;
            case "Continuar": {
               try {
                   verificarPinCuentaRetiroColones();
               } catch (MessagingException ex) {
                   Logger.getLogger(ControladorRetiroColones.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
                break;

            case "Verificar": {
               try {
                   verificarPalabraCorrectaRetiro();
               } catch (MessagingException ex) {
                   Logger.getLogger(ControladorRetiroColones.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
                break;

            case "Realizar retiro": realizarRetiroColones();
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
           JOptionPane.showMessageDialog(null,"La cuenta que indico está inactiva o no existe, cambie el número de cuenta");
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
       if(Objects.equals(this.retiroColones3.msgTelRetiroColones.getText(), this.retiroColones3.palabraMsgTelefono)){
           this.retiroColones4 = new RetiroColonesPaso4();
           this.retiroColones4.continuarRetiroColones4.addActionListener(this);
           this.retiroColones4.volverRetiroColones4.addActionListener(this);
           this.retiroColones4.jLabel1.setText(this.retiroColones2.jLabel3.getText());
           this.retiroColones4.setVisible(true);
           this.retiroColones3.setVisible(false);
           attempt =0;
       }
       else{
           JOptionPane.showMessageDialog(null,"La palabra que ingreso es incorrecta, intente nuevamente");
           attempt ++;
           comprobrarIntentos(Encriptar.cifrar(this.retiroColones2.jLabel3.getText()),this.retiroColones3,"Se ha inactivado la cuenta por fallar repetidamente la palabra clave");
           this.retiroColones3.palabraMsgTelefono = SMS.enviarSMS(String.valueOf(comparacionPersonaCuenta.getNumTelefonico()));
       }
   }
   
   private void realizarRetiroColones(){
       if(ValidarTipoDeDato.validarEsEntero(this.retiroColones4.montoRetiroColones.getText())){
           if(Validar.existeSaldoSuficiente(Double.parseDouble(this.retiroColones4.montoRetiroColones.getText()), this.retiroColones4.jLabel1.getText())){
               if(OperacionBD.numOperacionEnCuenta(Encriptar.cifrar(this.retiroColones4.jLabel1.getText())) >= 3){
                   
                   double comision = Double.parseDouble(this.retiroColones4.montoRetiroColones.getText()) * 0.02;
                   CuentaBD.agregarComision(Encriptar.cifrar(this.retiroColones4.jLabel1.getText()), comision, "retiros");
                   Double monto = Operacion.calcularComision(Double.parseDouble(this.retiroColones4.montoRetiroColones.getText()));
                   
                   CuentaBD.quitarSaldoCuenta(Encriptar.cifrar(String.valueOf(monto)), Encriptar.cifrar(this.retiroColones4.jLabel1.getText()));
                   Operacion oper = new Operacion("retiros", "Colones", true,Double.parseDouble(this.retiroColones4.montoRetiroColones.getText()),LocalDate.now());
                   OperacionBD.realizarOperacionEnBD(oper, Encriptar.cifrar(this.retiroColones4.jLabel1.getText()));
                   
                   clienteActual = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(retiroColones1.numCuentaRetiroColones.getText()));
                   JOptionPane.showMessageDialog(null,"Estimado usario, el monto de este retiro es "+ this.retiroColones4.montoRetiroColones.getText() + " colones\n" +
                           "[El monto cobrado por concepto de comisión fue de " + comision + " colones, que\n" + "fueron rebajados automáticament de su saldo actual] \n" +
                           "[Su saldo actual es de: '" + clienteActual.getSaldo()  + "']");
                   this.retiroColones4.setVisible(false);
                   this.menuInicial.setVisible(true);
               }
               else{
                   CuentaBD.quitarSaldoCuenta(Encriptar.cifrar(this.retiroColones4.montoRetiroColones.getText()), Encriptar.cifrar(this.retiroColones4.jLabel1.getText()));
                   Operacion oper = new Operacion("retiros", "Colones", true,Double.parseDouble(this.retiroColones4.montoRetiroColones.getText()),LocalDate.now());
                   OperacionBD.realizarOperacionEnBD(oper,Encriptar.cifrar(this.retiroColones4.jLabel1.getText()));
                   
                   clienteActual = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(retiroColones1.numCuentaRetiroColones.getText()));
                   JOptionPane.showMessageDialog(null, "Estimado usuario, el monto de este retiro es " + this.retiroColones4.montoRetiroColones.getText() + " colones \n" +
                           "[El monto cobrado por concepto de comisión fue de 0 colones, que fueron rebajados automáticamente de su saldo actual\n" +
                           "[Su saldo actual es de: '" + clienteActual.getSaldo()  + "']");
                   this.retiroColones4.setVisible(false);
                   this.menuInicial.setVisible(true);
               }
           }
           else{
               JOptionPane.showMessageDialog(null,"No existe suficiente saldo en la cuenta");
           }
       }
       else{
           JOptionPane.showMessageDialog(null,"Debe ingresar un número en el monto ");
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
