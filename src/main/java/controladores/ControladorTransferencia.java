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
import util.TipoCambio;
import validaciones.*;
/**
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 *
 * @version 1.0
 */

public class ControladorTransferencia implements ActionListener{
    public TransferenciaPaso1 transferencia1;
    public TransferenciaPaso2 transferencia2;
    public TransferenciaPaso3 transferencia3;
    public TransferenciaPaso4 transferencia4;
    private int attempt = 0;
    private CuentaBancaria clienteActual;
    private Menu menuInicial;
    
    public ControladorTransferencia(TransferenciaPaso1 pTransferencia1, TransferenciaPaso2 pTransferencia2, TransferenciaPaso3 pTransferencia3, TransferenciaPaso4 pTransferencia4, Menu pMenuInicial){
        this.transferencia1 = pTransferencia1;
        this.transferencia2 = pTransferencia2;
        this.transferencia3 = pTransferencia3;
        this.transferencia4 = pTransferencia4;
        this.clienteActual = null;
        this.menuInicial = pMenuInicial;
        this.transferencia1.continuarTransferencia.addActionListener(this);
        this.transferencia1.volverTransferencia1.addActionListener(this);
        this.transferencia2.continuarTransfencia2.addActionListener(this);
        this.transferencia2.volverTransferencia2.addActionListener(this);
        this.transferencia3.continuarTransferencia3.addActionListener(this);
        this.transferencia3.volverTransferencia3.addActionListener(this);
        this.transferencia4.continuarTransferencia4.addActionListener(this);
        this.transferencia4.volverTransferencia4.addActionListener(this);
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
                 controladores.ControladoresGlobales.volver();
                 this.transferencia1.setVisible(false);
                 this.transferencia2.setVisible(false);
                 this.transferencia3.setVisible(false);
                 this.transferencia4.setVisible(false);
             default:
                 break;
         }
    }
    private void verificarNumCuentaRetiroDolares(){
       CuentaBancaria cuentaBanc = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(this.transferencia1.numCuentaRetiroColones.getText()));
       if(cuentaBanc != null && !Objects.equals(cuentaBanc.getEstatus(),"Inactiva")){
           this.transferencia2 = new TransferenciaPaso2();
           this.transferencia2.continuarTransfencia2.addActionListener(this);
           this.transferencia2.volverTransferencia2.addActionListener(this);
           this.transferencia2.jLabel3.setText(String.valueOf(cuentaBanc.getNumCuenta()));
           this.transferencia2.setVisible(true);
           this.transferencia1.setVisible(false);
       }
       else{
           JOptionPane.showMessageDialog(null,"La cuenta que indico está inactiva o no existe, cambie el número de cuenta");
       }
    }
    
    private void verificarPinCuentaRetiroDolares() throws MessagingException{
       CuentaBancaria cuentaBanc = CuentaBD.recuperarCuentaPorNumPin(Encriptar.cifrar(this.transferencia2.jLabel3.getText()), 
               Encriptar.cifrar(this.transferencia2.pinTransferencia.getText()));
       Persona comparacionPersonaCuenta = CuentaBD.compararPersonaConCuenta(Encriptar.cifrar(transferencia1.numCuentaRetiroColones.getText()));

       if(cuentaBanc != null){
           this.transferencia3 = new TransferenciaPaso3();
           this.transferencia3.continuarTransferencia3.addActionListener(this);
           this.transferencia3.volverTransferencia3.addActionListener(this);
           this.transferencia3.palabraMsgTelefono = SMS.enviarSMS(String.valueOf(comparacionPersonaCuenta.getNumTelefonico()));
           this.transferencia3.setVisible(true);
           this.transferencia2.setVisible(false);
           attempt = 0;
       }
       else{
           JOptionPane.showMessageDialog(null, "El pin que ingreso es incorrecto");
           attempt ++;
           comprobrarIntentos(Encriptar.cifrar(this.transferencia2.jLabel3.getText()), this.transferencia2, "La cuenta se ha desactivado por fallar repetidamente el Pin");
       } 
    }
    
    private void verificarPalabraCorrectaRetiro() throws MessagingException{ 
       Persona comparacionPersonaCuenta = CuentaBD.compararPersonaConCuenta(Encriptar.cifrar(transferencia1.numCuentaRetiroColones.getText()));
       if(Objects.equals(this.transferencia3.msgTelTransferencia.getText(), this.transferencia3.palabraMsgTelefono)){
           this.transferencia4 = new TransferenciaPaso4();
           this.transferencia4.continuarTransferencia4.addActionListener(this);
           this.transferencia4.volverTransferencia4.addActionListener(this);
           this.transferencia4.jLabel1.setText(this.transferencia2.jLabel3.getText());
           this.transferencia4.setVisible(true);
           this.transferencia3.setVisible(false);
           attempt =0;
       }
       else{
           JOptionPane.showMessageDialog(null,"La palabra que ingreso es incorrecta, intente nuevamente");
           attempt ++;
           comprobrarIntentos(Encriptar.cifrar(this.transferencia2.jLabel3.getText()),this.transferencia3,"Se ha inactivado la cuenta por fallar repetidamente la palabra clave");
           this.transferencia3.palabraMsgTelefono = SMS.enviarSMS(String.valueOf(comparacionPersonaCuenta.getNumTelefonico()));
       }
   }
    
    private void realizarRetiroDolares(){ 
       String monto = this.transferencia4.montoTransferencia.getText();
       CuentaBancaria cuentaReceptora = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(this.transferencia4.jLabel1.getText()));
       String cuentaActual = this.transferencia4.jLabel1.getText();
       if(ValidarTipoDeDato.validarEsEntero(monto)){
           if(Validar.existeSaldoSuficiente(Double.parseDouble(monto), cuentaActual)){
               if(cuentaReceptora != null && !Objects.equals(cuentaReceptora.getEstatus(),"Inactiva")){
                    if(OperacionBD.numOperacionEnCuenta(Encriptar.cifrar(cuentaActual)) >= 3){

                        double montoEnDouble = Double.parseDouble(monto);
                        double comision = montoEnDouble * 0.02;
                        CuentaBD.agregarComision(Encriptar.cifrar(this.transferencia4.jLabel1.getText()), comision, "depósitos");
                        montoEnDouble = Operacion.calcularComision(montoEnDouble);
                        
                        CuentaBD.quitarSaldoCuenta(Encriptar.cifrar(String.valueOf(montoEnDouble)), Encriptar.cifrar(this.transferencia4.jLabel1.getText()));
                        CuentaBD.actualizarSaldo(Encriptar.cifrar(monto), Encriptar.cifrar(String.valueOf(cuentaReceptora.getNumCuenta())));
                        Operacion oper = new Operacion("retiros", "Colones", true,Double.parseDouble(monto),LocalDate.now());
                        OperacionBD.realizarOperacionEnBD(oper, Encriptar.cifrar(cuentaActual));

                        clienteActual = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(transferencia1.numCuentaRetiroColones.getText()));
                        Operacion oper2 = new Operacion("depósitos", "Colones", false, Double.parseDouble(monto),LocalDate.now());
                        JOptionPane.showMessageDialog(null,"Estimado usario, la transferencia de fondos se ejecutó satisfactoriamente\n" +
                                "El monto retiro de la cuenta origen y depositado en la cuenta destino es " + monto + " colones \n" +    
                                "[El monto cobrado por concepto de comisión fue de " + comision + " colones, que" + "fueron rebajados automáticament de su saldo actual] \n" +
                                "[Su saldo actual es de: '" + Math.round(clienteActual.getSaldo()*100)/100  + "' colones]");
                        this.transferencia4.setVisible(false);
                        this.menuInicial.setVisible(true);
                    }
                    else{
                        CuentaBD.quitarSaldoCuenta(Encriptar.cifrar(String.valueOf(monto)), Encriptar.cifrar(this.transferencia4.jLabel1.getText()));
                        CuentaBD.actualizarSaldo(Encriptar.cifrar(monto),Encriptar.cifrar(String.valueOf(String.valueOf(cuentaReceptora.getNumCuenta()))));
                        Operacion oper = new Operacion("retiros", "Colones", false, Double.parseDouble(monto),LocalDate.now());
                        OperacionBD.realizarOperacionEnBD(oper,Encriptar.cifrar(cuentaActual));
                        Operacion oper2 = new Operacion("depósitos", "Colones", false, Double.parseDouble(monto),LocalDate.now());
                        OperacionBD.realizarOperacionEnBD(oper2, Encriptar.cifrar(String.valueOf(cuentaReceptora.getNumCuenta())));
                        
                        clienteActual = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(transferencia1.numCuentaRetiroColones.getText()));
                        JOptionPane.showMessageDialog(null, "Estimado usuario, la transferencia de fondos se ejecutó satisfactoriamente. \n" +
                                "El monto retirado de la cuenta de origen y depositado en la cuenta destino es " + monto + " colones \n" +      
                                "[El monto cobrado por concepto de comisión a la cuenta origen de 0 colones, que fueron rebajados automáticamente de su saldo actual\n" +
                                "[Su saldo actual es de: '" + Math.round(clienteActual.getSaldo()*100)/100  + "']");
                        this.transferencia4.setVisible(false);
                        this.menuInicial.setVisible(true);
                    }
                }
                else{
                   JOptionPane.showMessageDialog(null,"La cuenta no existe o está inactiva");
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
        Persona comparacionPersonaCuenta = CuentaBD.compararPersonaConCuenta(Encriptar.cifrar(this.transferencia2.jLabel3.getText()));
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
