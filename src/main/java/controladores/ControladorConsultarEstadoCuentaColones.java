package controladores;


import gui.*;

import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
        
import javax.swing.*;
import java.util.*;
import controladores.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import util.Encriptar;
import logicadeaccesoadatos.CuentaBD;
import validaciones.*;

import logicadenegocios.*;
import logicadeaccesoadatos.*;
import java.time.format.DateTimeFormatter;
import validaciones.*;
/**
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 *
 * @version 1.0
 */
public class ControladorConsultarEstadoCuentaColones implements ActionListener{
    private EstadoDeCuentaColones estadoCuentaColones;
    private int attempt = 0;
    
    public ControladorConsultarEstadoCuentaColones( EstadoDeCuentaColones pEstadoCuentaColones){
        this.estadoCuentaColones.numCuentaEstadoColones.addActionListener(this);
        this.estadoCuentaColones.pinEstadoCuentaColones.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent evento) {
        switch(evento.getActionCommand()){
            case "Consultar cuenta": 
               break;
            case "Volver":
                controladores.ControladoresGlobales.volver();
                this.estadoCuentaColones.setVisible(false);
            default:
                break;
        }
    }
    
    public void realizarConsultaCuentaColones(){
        CuentaBancaria numCuenta = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(this.estadoCuentaColones.numCuentaEstadoColones.getText()));
        if(numCuenta != null && !Objects.equals(numCuenta.getEstatus(),"Inactiva")){
            if(numCuenta != null && !Objects.equals(numCuenta.getPin(),String.valueOf(Encriptar.cifrar(this.estadoCuentaColones.pinEstadoCuentaColones.getText())))){
                JOptionPane.showMessageDialog(null, numCuenta.toString());
                attempt = 0;
                Operacion oper = new Operacion()
            }
            else{
                JOptionPane.showConfirmDialog(null, "El pin que indico es incorrecto");
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "La cuenta que digito no existe o está inactiiva");
        }
    }
}
