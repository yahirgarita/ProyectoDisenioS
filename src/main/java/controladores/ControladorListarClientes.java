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
import controladores.*;

/**
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 *
 * @version 1.0
 */

public class ControladorListarClientes implements ActionListener{
    public ListarClientes listarClientes;
    private Menu menuInicial;
    
    /**
     * ControladorListarClientes
     * 
     * @param pListarClientes
     */
    public ControladorListarClientes(ListarClientes pClientes){
        this.listarClientes = pClientes;
        this.menuInicial = null;
        this.listarClientes.botonConsultarClientes.addActionListener(this);
        this.listarClientes.botonVolver.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent evento){
        switch(evento.getActionCommand()){
            case "Consultar clientes":consultarClientes();
                break;
            case "Volver":
                controladores.ControladoresGlobales.volver();
                this.listarClientes.setVisible(false);
                break;
            default:
                break;
       }        
    }
    public void consultarClientes(){
        
    }
}
