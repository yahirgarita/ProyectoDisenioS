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
public class ControladorConsultarEstadoCuentaColones implements ActionListener{
    public EstadoDeCuentaColones estadoCuentaColones;
    private int attempt = 0;
    private Menu menuInicial;
    
    public ControladorConsultarEstadoCuentaColones( EstadoDeCuentaColones pEstadoCuentaColones, Menu pMenuInicial){
        this.estadoCuentaColones = pEstadoCuentaColones;
        this.menuInicial = pMenuInicial;
        this.estadoCuentaColones.Consultar.addActionListener(this);
        this.estadoCuentaColones.volverPin1.addActionListener(this);
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
        CuentaBancaria numCuenta = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(this.estadoCuentaColones.numCuentaEstadoColones.getText()));
        if(numCuenta != null && !Objects.equals(numCuenta.getEstatus(),"Inactiva")){
            if(numCuenta != null && !Objects.equals(numCuenta.getPin(),String.valueOf(Encriptar.cifrar(this.estadoCuentaColones.pinEstadoCuentaColones.getText())))){
                JOptionPane.showMessageDialog(null, numCuenta.toString());
                attempt = 0;
                Operacion oper = new Operacion("consultas","colones",false,0, LocalDate.now());
                OperacionBD.realizarOperacionEnBD(oper, Encriptar.cifrar(String.valueOf(numCuenta.getNumCuenta())));
                this.estadoCuentaColones.setVisible(false);
                this.menuInicial.setVisible(true);
            }
            else{
                JOptionPane.showConfirmDialog(null, "El pin que indico es incorrecto");
                attempt++;
                comprobrarIntentos(Encriptar.cifrar(this.estadoCuentaColones.pinEstadoCuentaColones.getText()),this.estadoCuentaColones,"Se ha inactivado la cuenta por fallar el pin repetiadamente");
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "La cuenta que digito no existe o está inactiiva");
        }
    }
    private  void comprobrarIntentos(String pNumCuenta, JFrame frame, String pMsg) throws MessagingException{
        
        Persona comparacionPersonaCuenta = CuentaBD.compararPersonaConCuenta(Encriptar.cifrar(pNumCuenta));
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
