package controladoresgui;

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
import util.TipoCambio;
import validaciones.*;

/**
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 *
 * @version 1.0
 */
public class ControladorRetiroDolares implements ActionListener{
    public RetiroDolaresPaso1 retiroDolares1;
    public RetiroDolaresPaso2 retiroDolares2;
    public RetiroDolaresPaso3 retiroDolares3;
    public RetiroDolaresPaso4 retiroDolares4;
    private int attempt = 0;
    private CuentaBancaria clienteActual;
    private Menu menuInicial;
    private TipoCambio tipoCambio = new TipoCambio();
    
    public ControladorRetiroDolares(RetiroDolaresPaso1 pRetiroDolares1, RetiroDolaresPaso2 pRetiroDolares2, RetiroDolaresPaso3 pRetiroDolares3, RetiroDolaresPaso4 pRetiroDolares4, Menu pMenuInicial){
        this.retiroDolares1 = pRetiroDolares1;
        this.retiroDolares2 = pRetiroDolares2;
        this.retiroDolares3 = pRetiroDolares3;
        this.retiroDolares4 = pRetiroDolares4;
        this.clienteActual = null;
        this.menuInicial = pMenuInicial;
        this.retiroDolares1.continuarRetiroDolares.addActionListener(this);
        this.retiroDolares1.volverRetiroDolares1.addActionListener(this);
        this.retiroDolares2.continuarRetiroDolares2.addActionListener(this);
        this.retiroDolares2.volverRetiroDolares2.addActionListener(this);
        this.retiroDolares3.continuarRetiroDolares3.addActionListener(this);
        this.retiroDolares3.volverRetiroDolares3.addActionListener(this);
        this.retiroDolares4.continuarRetiroDolares4.addActionListener(this);
        this.retiroDolares4.volverRetiroDolaers4.addActionListener(this);
    }
    
   @Override
   public void actionPerformed(ActionEvent evento){
       switch(evento.getActionCommand()){
            case "Continuar proceso": verificarNumCuentaRetiroDolares();
                break;
            case "Continuar": {
               try {
                   verificarPinCuentaRetiroDolares();
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

            case "Realizar retiro": realizarRetiroDolares();
                break;      
            case "Volver":
                controladoresgui.ControladoresGlobales.volver();
                this.retiroDolares1.setVisible(false);
                this.retiroDolares2.setVisible(false);
                this.retiroDolares3.setVisible(false);
                this.retiroDolares4.setVisible(false);
            default:
                break;
        }
   }
   private void verificarNumCuentaRetiroDolares(){
       CuentaBancaria cuentaBanc = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(this.retiroDolares1.numCuentaRetiroDolares.getText()));
       if(cuentaBanc != null && !Objects.equals(cuentaBanc.getEstatus(),"Inactiva")){
           this.retiroDolares2 = new RetiroDolaresPaso2();
           this.retiroDolares2.continuarRetiroDolares2.addActionListener(this);
           this.retiroDolares2.volverRetiroDolares2.addActionListener(this);
           this.retiroDolares2.jLabel3.setText(String.valueOf(cuentaBanc.getNumCuenta()));
           this.retiroDolares2.setVisible(true);
           this.retiroDolares1.setVisible(false);
       }
       else{
           JOptionPane.showMessageDialog(null,"La cuenta que indico est?? inactiva o no existe, cambie el n??mero de cuenta");
       }
   }
   
   private void verificarPinCuentaRetiroDolares() throws MessagingException{
       CuentaBancaria cuentaBanc = CuentaBD.recuperarCuentaPorNumPin(Encriptar.cifrar(this.retiroDolares2.jLabel3.getText()), 
               Encriptar.cifrar(this.retiroDolares2.pinCuentaRetiroDolares.getText()));
       Persona comparacionPersonaCuenta = CuentaBD.compararPersonaConCuenta(Encriptar.cifrar(retiroDolares1.numCuentaRetiroDolares.getText()));

       if(cuentaBanc != null){
           this.retiroDolares3 = new RetiroDolaresPaso3();
           this.retiroDolares3.continuarRetiroDolares3.addActionListener(this);
           this.retiroDolares3.volverRetiroDolares3.addActionListener(this);
           this.retiroDolares3.palabraMsgTelefono = SMS.enviarSMS(String.valueOf(comparacionPersonaCuenta.getNumTelefonico()));
           this.retiroDolares3.setVisible(true);
           this.retiroDolares2.setVisible(false);
           attempt = 0;
       }
       else{
           JOptionPane.showMessageDialog(null, "El pin que ingreso es incorrecto");
           attempt ++;
           comprobrarIntentos(Encriptar.cifrar(this.retiroDolares2.jLabel3.getText()), this.retiroDolares2, "La cuenta se ha desactivado por fallar repetidamente el Pin");
       } 
   }
   
   private void verificarPalabraCorrectaRetiro() throws MessagingException{ 
       Persona comparacionPersonaCuenta = CuentaBD.compararPersonaConCuenta(Encriptar.cifrar(retiroDolares1.numCuentaRetiroDolares.getText()));
       if(Objects.equals(this.retiroDolares3.msgTelRetiroDolares.getText(), this.retiroDolares3.palabraMsgTelefono)){
           this.retiroDolares4 = new RetiroDolaresPaso4();
           this.retiroDolares4.continuarRetiroDolares4.addActionListener(this);
           this.retiroDolares4.volverRetiroDolaers4.addActionListener(this);
           this.retiroDolares4.jLabel1.setText(this.retiroDolares2.jLabel3.getText());
           this.retiroDolares4.setVisible(true);
           this.retiroDolares3.setVisible(false);
           attempt =0;
       }
       else{
           JOptionPane.showMessageDialog(null,"La palabra que ingreso es incorrecta, intente nuevamente");
           attempt ++;
           comprobrarIntentos(Encriptar.cifrar(this.retiroDolares2.jLabel3.getText()),this.retiroDolares3,"Se ha inactivado la cuenta por fallar repetidamente la palabra clave");
           this.retiroDolares3.palabraMsgTelefono = SMS.enviarSMS(String.valueOf(comparacionPersonaCuenta.getNumTelefonico()));
       }
   }
   
   private void realizarRetiroDolares(){ 
       String montoDolares = this.retiroDolares4.montoRetiroDolares.getText();
       CuentaBancaria cuentaBanc = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(this.retiroDolares4.jLabel1.getText()));
       double dolar = tipoCambio.getVenta();
       double montoColones = Double.parseDouble(montoDolares) * dolar;
       if(ValidarTipoDeDato.validarEsEntero(montoDolares)){
           if(Validar.existeSaldoSuficiente(Double.parseDouble(montoDolares), this.retiroDolares4.jLabel1.getText())){
               if(OperacionBD.numOperacionEnCuenta(Encriptar.cifrar(this.retiroDolares4.jLabel1.getText())) >= 3){
                   
                   double comision = montoColones * 0.02;
                   CuentaBD.agregarComision(Encriptar.cifrar(this.retiroDolares4.jLabel1.getText()), comision, "retiros");
                   montoColones = Operacion.calcularComision(montoColones);
                   
                   CuentaBD.quitarSaldoCuenta(Encriptar.cifrar(String.valueOf(montoColones)), Encriptar.cifrar(this.retiroDolares4.jLabel1.getText()));
                   Operacion oper = new Operacion("retiros", "D??lares", true,montoColones,LocalDate.now());
                   OperacionBD.realizarOperacionEnBD(oper, Encriptar.cifrar(this.retiroDolares4.jLabel1.getText()));
                   
                   clienteActual = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(retiroDolares1.numCuentaRetiroDolares.getText()));
                   JOptionPane.showMessageDialog(null,"Estimado usario, el monto de este retiro es "+ this.retiroDolares4.montoRetiroDolares.getText() + " d??lares\n" +
                           "[Seg??n el BCCR, el tipo de cambio de venta del d??lar de hoy: " + dolar +"]\n" +
                           "[El monto equivalente de su retiro es " + montoColones + " colones]\n" +        
                           "[El monto cobrado por concepto de comisi??n fue de " + comision + " colones, que" + "fueron rebajados autom??ticament de su saldo actual] \n" +
                           "[Su saldo actual es de: '" + Math.round(clienteActual.getSaldo()*100)/100  + "' colones]");
                   this.retiroDolares4.setVisible(false);
                   this.menuInicial.setVisible(true);
               }
               else{
                   CuentaBD.quitarSaldoCuenta(Encriptar.cifrar(String.valueOf(montoColones)), Encriptar.cifrar(this.retiroDolares4.jLabel1.getText()));
                   Operacion oper = new Operacion("retiros", "D??lares", false, montoColones,LocalDate.now());
                   OperacionBD.realizarOperacionEnBD(oper,Encriptar.cifrar(String.valueOf(cuentaBanc.getNumCuenta())));
                   
                   clienteActual = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(retiroDolares1.numCuentaRetiroDolares.getText()));
                   JOptionPane.showMessageDialog(null, "Estimado usuario, el monto de este retiro es " + this.retiroDolares4.montoRetiroDolares.getText() + " d??lares \n" +
                           "[Seg??n el BCCR, el tipo de cambio de venta del d??lar de hoy:" + dolar + "]\n" +
                           "[El monto equivalente de su retiro es " + montoColones + " colones ]\n" +        
                           "[El monto cobrado por concepto de comisi??n fue de 0 colones, que fueron rebajados autom??ticamente de su saldo actual\n" +
                           "[Su saldo actual es de: '" + Math.round(clienteActual.getSaldo()*100)/100  + "']");
                   this.retiroDolares4.setVisible(false);
                   this.menuInicial.setVisible(true);
               }
           }
           else{
               JOptionPane.showMessageDialog(null,"No existe suficiente saldo en la cuenta");
           }
       }
       else{
           JOptionPane.showMessageDialog(null,"Debe ingresar un n??mero en el monto ");
       }
   }
   
   private  void comprobrarIntentos(String pNumCuenta, JFrame frame, String pMsg) throws MessagingException{
        Persona comparacionPersonaCuenta = CuentaBD.compararPersonaConCuenta(Encriptar.cifrar(this.retiroDolares2.jLabel3.getText()));
        if(attempt == 2){
            CuentaBD.modificarEstado(pNumCuenta, "Inactiva");
            attempt = 0;
            JOptionPane.showMessageDialog(null, pMsg);
            Email.enviarEmail(comparacionPersonaCuenta.getCorreoPersona(), pMsg);
            frame.dispose();
            controladoresgui.ControladoresGlobales.volver();
        }
    }
}
