/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;
import gui.ConsultarSaldo;
import logicadeaccesoadatos.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import util.Encriptar;

/**
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 *
 * @version 1.0
 */
public class ControladorVerSaldo implements ActionListener{
    public ConsultarSaldo consultaSaldo;
    
    public ControladorVerSaldo(ConsultarSaldo pConsultarSaldo){
    consultaSaldo = pConsultarSaldo;
    consultaSaldo.btnVerSaldo.addActionListener(this);
    consultaSaldo.btnVolver.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Ver Saldo": verSaldo();
                break;
            case "Volver":
                controladores.ControladoresGlobales.volver();
                this.consultaSaldo.setVisible(false);
                break;
            default:
                break;
       }        
    }
    private void verSaldo(){
       String cuenta = this.consultaSaldo.numCuenta.getText();
       String pin = this.consultaSaldo.pinUsuario.getText();
       if(CuentaBD.recuperarCuentaPorNumPin(Encriptar.cifrar(cuenta), Encriptar.cifrar(pin)) != null){
           JOptionPane.showMessageDialog(null, "Estimado usuario el saldo actual de su cuenta es " + CuentaBD.recuperarCuentaPorNumPin(Encriptar.cifrar(cuenta), Encriptar.cifrar(pin)).getSaldo() + " colones.");
       }else{
           JOptionPane.showMessageDialog(null, "Error: Datos inválidos");
       } 
    } 
}


