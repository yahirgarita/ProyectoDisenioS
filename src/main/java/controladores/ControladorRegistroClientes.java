package controladores;

import gui.*;


import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
        
import javax.swing.*;
import java.util.*;
import controladores.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import logicadenegocios.Persona;
import logicadeaccesoadatos.PersonaBD;
import validaciones.*;

/**
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 *
 * @version 1.0
 */
public class ControladorRegistroClientes implements ActionListener{
    public RegistroCliente registrarCliente;
    private Menu menuInicial;
    private ArrayList<Persona> clientesSistema;
    
    /**
     * Constructor de la clase ControlodaroRegistro
     * 
     * @param pRegistrarCliente
     */
    public ControladorRegistroClientes (RegistroCliente pRegistrarCliente){
        this.registrarCliente = pRegistrarCliente;
        this.clientesSistema = new ArrayList<>();
        this.menuInicial = null;
        this.registrarCliente.botonContinuar.addActionListener(this);
        this.registrarCliente.botonVolver.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent evento){
        
        switch(evento.getActionCommand()){
            case "Continuar": {
                try {
                    hacerRegistro();
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorRegistroClientes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                break;

            case "Volver":controladores.ControladoresGlobales.volver();
                this.registrarCliente.setVisible(false);
                break;
            default:
                break;
        }
    }
    private void hacerRegistro() throws SQLException{
        Persona nuevoCliente = new Persona(this.registrarCliente.primerApellidoRegistro1.getText(),
            this.registrarCliente.segundoApellidoRegistro1.getText(), this.registrarCliente.nombreRegistro.getText(), 
            Integer.parseInt(this.registrarCliente.idRegistro.getText()), ValidarTipoDeDato.corregirFormatoFecha(this.registrarCliente.nacimientoRegistro.getDate()),
            Integer.parseInt(this.registrarCliente.telRegistro1.getText()), this.registrarCliente.correoRegistro1.getText());
        if(PersonaBD.registrarClientesEnBD(nuevoCliente)){
            clientesSistema.add(nuevoCliente);
            JOptionPane.showMessageDialog(null, nuevoCliente.mensajeCreacion());
            controladores.ControladoresGlobales.volver();
            this.registrarCliente.setVisible(false);
        }
    } 
    
}
