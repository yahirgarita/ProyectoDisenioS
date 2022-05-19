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
import javax.mail.MessagingException;
import util.Email;
import validaciones.*;
/**
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 *
 * @version 1.0
 */
public class ControladorConsultarEstadoCuentaDolares implements ActionListener{
     public EstadoDeCuentaColones estadoCuentaDolares;
     private int attempt = 0;
     private Menu menuInicial;
     
     public ControladorConsultarEstadoCuentaDolares( EstadoDeCuentaColones pEstadoCuentaColones, Menu pMenuInicial){
        this.estadoCuentaDolares = pEstadoCuentaColones;
        this.menuInicial = pMenuInicial;
        this.estadoCuentaDolares.Consultar.addActionListener(this);
        this.estadoCuentaDolares.volverPin1.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        switch(evento.getActionCommand()){
            case "Consultar estado": {
                try {
                    realizarConsultaCuentaColones();
                } catch (MessagingException ex) {
                    Logger.getLogger(ControladorConsultarEstadoCuentaColones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
               break;

            case "Volver":
                controladores.ControladoresGlobales.volver();
                this.estadoCuentaColones.setVisible(false);
            default:
                break;
        }
    }
     public void realizarConsultaCuentaColones() throws MessagingException{
}
