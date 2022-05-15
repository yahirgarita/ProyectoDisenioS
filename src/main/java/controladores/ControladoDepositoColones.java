package controladores;

import gui.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.JOptionPane;
import logicadeaccesoadatos.*;
import logicadenegocios.*;
import util.Encriptar;
import validaciones.*;
/**
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 *
 * @version 1.0
 */
public class ControladoDepositoColones implements ActionListener{
   public RealizarDepositoEnColones realizarDepositoC;
   public RealizarDepositoEnColonesPaso2 realizarDepositoC2;
   
   private ControladoDepositoColones(RealizarDepositoEnColones pRealizarDepositoC, RealizarDepositoEnColonesPaso2 pRealizarDepositoC2){
       this.realizarDepositoC = pRealizarDepositoC;
       this.realizarDepositoC2 = pRealizarDepositoC2;
       this.realizarDepositoC.continuarDepoColones.addActionListener(this);
       this.realizarDepositoC.volverDepoColones.addActionListener(this);
       //this.realizarDepositoC2.
   }
   @Override
   public void actionPerformed(ActionEvent evento){
       switch(evento.getActionCommand()){
           case "Continuar proceso":}
   }
   private void verificarDepositoColones(){
       CuentaBancaria cuentaBanc = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(this.realizarDepositoC.numCuentaDepocitoColones.getText()));
       if(cuentaBanc != null && !Objects.equals(cuentaBanc.getEstatus(), "Inactiva")){
           this.realizarDepositoC2 = new RealizarDepositoEnColonesPaso2();
           this.realizarDepositoC2.realizarDeposito.addActionListener(this);
           this.realizarDepositoC2.volverDepoColones2.addActionListener(this);
           this.realizarDepositoC2.labelInfo.setText(String.valueOf(CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(this.realizarDepositoC.numCuentaDepocitoColones.getText())).getNumCuenta()));
           this.realizarDepositoC2.setVisible(true);
           this.realizarDepositoC.setVisible(false);
       }else{
           JOptionPane.showMessageDialog(null,"La cuenta ingresada se encuentra inactiva o no existe, cambie el número de cuenta");
       }
   }
   private void hacerDepositoColones(){
       String monto = this.realizarDepositoC2.txtMonto.getText();
       if(ValidarTipoDeDato.validarEsEntero(monto)){
           if(OperacionBD.numOperacionEnCuenta(Encriptar.cifrar(this.realizarDepositoC2.labelInfo.getText()))>= 3){}
           double montoConvDouble = Double.parseDouble(monto);
           double comision = montoConvDouble * 0.02;
           CuentaBD.
           
           
       }
   }
}
