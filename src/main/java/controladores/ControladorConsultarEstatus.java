/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;
import gui.ConsultarEstatus;
import logicadeaccesoadatos.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 *
 * @version 1.0
 */

public class ControladorConsultarEstatus implements ActionListener{
    public ConsultarEstatus consultaEstatus;
    
    public ControladorConsultarEstatus(ConsultarEstatus pConsultarSaldo){
        consultaEstatus = pConsultarSaldo;
        consultaEstatus.verEstatus.addActionListener(this);
        consultaEstatus.btnVolver.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Ver Estatus": verEstatus();
                break;
            case "Volver":
                controladores.ControladoresGlobales.volver();
                this.consultaEstatus.setVisible(false);
                break;
            default:
                break;
       }
    }
    private void verEstatus(){
        String cuenta = this.consultaEstatus.numCuenta.getText();
    }
}
