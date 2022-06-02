
package controladoresgui;

import gui.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
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
public class ControladorDepositoColones implements ActionListener{
   public RealizarDepositoEnColones realizarDepositoC;
   public RealizarDepositoEnColonesPaso2 realizarDepositoC2;
   private Menu menuInicial;
   public ControladorDepositoColones(RealizarDepositoEnColones pRealizarDepositoC, RealizarDepositoEnColonesPaso2 pRealizarDepositoC2, Menu pMenuInicial){
       this.realizarDepositoC = pRealizarDepositoC;
       this.realizarDepositoC2 = pRealizarDepositoC2;
       this.menuInicial = pMenuInicial;
       this.realizarDepositoC.continuarDepoColones.addActionListener(this);
       this.realizarDepositoC.volverDepoColones.addActionListener(this);
       this.realizarDepositoC2.realizarDeposito.addActionListener(this);
       this.realizarDepositoC2.volverDepoColones2.addActionListener(this);
   }
   @Override
   public void actionPerformed(ActionEvent evento){
       switch(evento.getActionCommand()){
            case "Continuar proceso": verificarDepositoColones();
                break;
            case "Depositar": hacerDepositoColones();
                break;
            case "Volver":
                controladoresgui.ControladoresGlobales.volver();
                this.realizarDepositoC.setVisible(false);
                this.realizarDepositoC2.setVisible(false);
            default:
                break;
        }
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
           if(OperacionBD.numOperacionEnCuenta(Encriptar.cifrar(this.realizarDepositoC2.labelInfo.getText()))>= 3){
                double montoConvDouble = Double.parseDouble(monto);
                double comision = montoConvDouble * 0.02;

                CuentaBD.agregarComision(Encriptar.cifrar(this.realizarDepositoC2.labelInfo.getText()),comision,"depósitos");
                montoConvDouble = Operacion.calcularComision(montoConvDouble);
                CuentaBD.actualizarSaldo(Encriptar.cifrar(String.valueOf(montoConvDouble)),Encriptar.cifrar(this.realizarDepositoC2.labelInfo.getText()));

                Operacion oper = new Operacion("depósitos","Colones", true, Double.parseDouble(monto), LocalDate.now());
                OperacionBD.realizarOperacionEnBD(oper, Encriptar.cifrar(this.realizarDepositoC2.labelInfo.getText()));
                JOptionPane.showMessageDialog(null, "Estimado usuario, se ha realizado correctamente el deposito " + monto + " colones\n" +
                        "[El monto real deposito a su cuenta " + this.realizarDepositoC2.labelInfo.getText() + " es de" + montoConvDouble + " colones]\n" +
                        "[El monto cobrado por concepto añadigo de comisión fue de " + comision + " colones, que fueron rebajados de forma automatica de su saldo actual]");
                this.realizarDepositoC2.setVisible(false);
                this.menuInicial.setVisible(true);
           
           }else{
                CuentaBD.actualizarSaldo(Encriptar.cifrar(String.valueOf(monto)),Encriptar.cifrar(this.realizarDepositoC2.labelInfo.getText()));
                Operacion oper = new Operacion("depósitos", "Colones", false, Double.parseDouble(monto), LocalDate.now());
                OperacionBD.realizarOperacionEnBD(oper,Encriptar.cifrar(this.realizarDepositoC2.labelInfo.getText()));
                JOptionPane.showMessageDialog(null, "Estimado usuario, se ha realizado correctamente el deposito " + monto + " colones\n" +
                        "[El monto real deposito a su cuenta " + this.realizarDepositoC2.labelInfo.getText() + " es de " + monto + " colones]\n" +
                        "[El monto cobrado por concepto añadigo de comisión fue de 0 colones, que fueron rebajados de forma automatica de su saldo actual]");
                this.realizarDepositoC2.setVisible(false);
                this.menuInicial.setVisible(true);
            }
        }
       else{
           JOptionPane.showMessageDialog(null,"Debe resivar los datos que ingreso");
       }
    }
}
