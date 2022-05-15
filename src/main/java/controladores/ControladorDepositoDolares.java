package controladores;


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
import util.TipoCambio;
/**
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 *
 * @version 1.0
 */

public class ControladorDepositoDolares implements ActionListener{
    public RealizarDepositoEnDolaresPaso1 depositoDolares1;
    public RealizarDepositoEnDolaresPaso2 depositoDolares2;
    private TipoCambio tipoCambio = new TipoCambio();
    
    public ControladorDepositoDolares(RealizarDepositoEnDolaresPaso1 pRealizarDepositoD, RealizarDepositoEnDolaresPaso2 pRealizarDepositoD2){
       this.depositoDolares1 = pRealizarDepositoD;
       this.depositoDolares2 = pRealizarDepositoD2;
       this.depositoDolares1.continuarDepoDolares.addActionListener(this);
       this.depositoDolares1.volverDepoDolares.addActionListener(this);
       this.depositoDolares2.realizarDeposito.addActionListener(this);
       this.depositoDolares2.volverDepoDolares2.addActionListener(this);
    }
    
   @Override
   public void actionPerformed(ActionEvent evento){
       switch(evento.getActionCommand()){
                case "Continuar proceso": verificarDepositoDolares();
                break;
            case "Depositar": hacerDepositoDolares();
                break;
            case "Volver":
                controladores.ControladoresGlobales.volver();
                this.depositoDolares1.setVisible(false);
                this.depositoDolares2.setVisible(false);
            default:
                break;
        }
   }
   
   private void verificarDepositoDolares(){
       CuentaBancaria cuentaBanc = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(this.depositoDolares1.numCuentaDepocitoDolares.getText()));
       if(cuentaBanc != null && !Objects.equals(cuentaBanc.getEstatus(), "Inactiva")){
           this.depositoDolares2 = new RealizarDepositoEnDolaresPaso2();
           this.depositoDolares2.realizarDeposito.addActionListener(this);
           this.depositoDolares2.volverDepoDolares2.addActionListener(this);
           this.depositoDolares2.labelInfo.setText(String.valueOf(CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(this.depositoDolares1.numCuentaDepocitoDolares.getText())).getNumCuenta()));
           this.depositoDolares2.setVisible(true);
           this.depositoDolares1.setVisible(false);
       }else{
           JOptionPane.showMessageDialog(null,"La cuenta ingresada se encuentra inactiva o no existe, cambie el número de cuenta");
       }
   }
   
   private void hacerDepositoDolares(){
       String montoEnDolar = this.depositoDolares2.txtMonto.getText();
       double dolar = tipoCambio.getCompra();
       double montoColones = Double.parseDouble(montoEnDolar) * dolar;
       if(ValidarTipoDeDato.validarEsEntero(montoEnDolar)){
           if(OperacionBD.numOperacionEnCuenta(Encriptar.cifrar(this.depositoDolares2.labelInfo.getText()))>= 3){
                double comision = montoColones * 0.02;

                CuentaBD.agregarComision(Encriptar.cifrar(this.depositoDolares2.labelInfo.getText()),comision,"depósitos");
                montoColones = Operacion.calcularComision(montoColones);
                CuentaBD.actualizarSaldo(Encriptar.cifrar(String.valueOf(montoColones)),Encriptar.cifrar(this.depositoDolares2.labelInfo.getText()));

                Operacion oper = new Operacion("depósitos","Dolares", true, Double.parseDouble(montoEnDolar), LocalDate.now());
                OperacionBD.realizarOperacionEnBD(oper, Encriptar.cifrar(this.depositoDolares2.labelInfo.getText()));
                JOptionPane.showMessageDialog(null, 
                        "Estimado usuario, se ha realizado correctamente el deposito " + montoEnDolar + " dólares\n" +
                        "[Según el BCCR, el tipo de cambio de compra del dólar de hoy " + LocalDate.now().toString() + " es de: " + dolar + "]\n" +
                        "[El monto equivalente en colones es: " + Math.round(((Double.parseDouble(montoEnDolar) * dolar))*100.0)/100.0 + "] \n"+
                        "[El monto real depositado a su cuenta " + this.depositoDolares2.labelInfo.getText() + " es de " + Math.round(montoColones *100.0)+ " colones]\n" +
                        "[El monto cobrado por concepto añadigo de comisión fue de " + Math.round(comision*100.0)/100.0 + " colones, que fueron rebajados de forma automatica de su saldo actual]");
                this.depositoDolares2.setVisible(false);
           
           }else{
                CuentaBD.actualizarSaldo(Encriptar.cifrar(String.valueOf(montoColones)),Encriptar.cifrar(this.depositoDolares2.labelInfo.getText()));
                Operacion oper = new Operacion("depósitos", "colones", false, Double.parseDouble(montoEnDolar), LocalDate.now());
                OperacionBD.realizarOperacionEnBD(oper,Encriptar.cifrar(this.depositoDolares2.labelInfo.getText()));
                JOptionPane.showMessageDialog(null, 
                        "Estimado usuario, se ha realizado correctamente el deposito " + montoEnDolar + " dólares\n" +
                        "[Según el BCCR, el tipo de cambio de compra del dólar de hoy " + LocalDate.now().toString() + " es de: " + dolar + "]\n" +
                        "[El monto equivalente en colones es: " + Double.parseDouble(montoEnDolar) * dolar + "] \n"+
                        "[El monto real depositado a su cuenta " + this.depositoDolares2.labelInfo.getText() + " es de " + montoColones + " colones]\n" +
                        "[El monto cobrado por concepto añadigo de comisión fue de 0 colones, que fueron rebajados de forma automatica de su saldo actual]");
                this.depositoDolares2.setVisible(false);
            }
        }
       else{
           JOptionPane.showMessageDialog(null,"Debe resivar los datos que ingreso");
       }
    }
}
