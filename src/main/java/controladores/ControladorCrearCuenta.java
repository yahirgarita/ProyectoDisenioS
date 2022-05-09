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

import logicadenegocios.*;
import logicadeaccesoadatos.PersonaBD;
import validaciones.*;
/**
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 *
 * @version 1.0
 */
public class ControladorCrearCuenta implements ActionListener{
    public CrearCuenta crearCuenta;
    private Menu menuInicial;
    
    /**
     * ControladorListarClientes
     * 
     * @param pCrearCuenta
     */
    public ControladorCrearCuenta(CrearCuenta pCrearCuenta){
        this.crearCuenta = pCrearCuenta;
        this.menuInicial = null;
        this.crearCuenta.botonRegistrar.addActionListener(this);
        this.crearCuenta.botonVolver.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent evento){
        switch(evento.getActionCommand()){
            case "Consultar clientes":consultarClientes();
                break;
            case "Volver":
                controladores.ControladoresGlobales.volver();
                this.crearCuenta.setVisible(false);
                break;
            default:
                break;
       }        
    }
    private void crearCuenta(){
        int numRandomCuenta = CuentaBancaria.generarNumCuenta();
    }
}
