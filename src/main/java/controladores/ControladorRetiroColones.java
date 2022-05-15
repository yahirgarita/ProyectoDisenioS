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
    
    public ControladorRetiroColones(RetiroColonesPaso1 pRetiroColones1, RetiroColonesPaso2 pRetiroColones2, RetiroColonesPaso3 pRetiroColones3, RetiroColonesPaso4 pRetiroColones4){
        this.retiroColones1 = pRetiroColones1;
        this.retiroColones2 = pRetiroColones2;
        this.retiroColones3 = pRetiroColones3;
        this.retiroColones4 = pRetiroColones4;
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
       }
   }
   
}
