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
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import logicadeaccesoadatos.CuentaBD;

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
    private ArrayList<CuentaBancaria> cuentasEnBD;
    private Persona clienteActual;
    
    
    /**
     * ControladorListarClientes
     * 
     * @param pCrearCuenta
     */
    public ControladorCrearCuenta(CrearCuenta pCrearCuenta){
        this.crearCuenta = pCrearCuenta;
        this.menuInicial = null;
        this.cuentasEnBD = new ArrayList<>();
        this.crearCuenta.botonRegistrar.addActionListener(this);
        this.crearCuenta.botonVolver.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent evento){
        switch(evento.getActionCommand()){
            case "Consultar clientes":crearCuenta();
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
        if(validarDatosCrearCuenta()){
            CuentaBancaria newCuenta = new CuentaBancaria(numRandomCuenta, Double.parseDouble(this.crearCuenta.montoInicialCrearCuenta.getText()),
                    this.crearCuenta.pinCrearCuenta.getText(), LocalDate.now());
        
        newCuenta.afiliarDuenio(this.clienteActual);
        this.clienteActual.aniadirCuentaBancariaCliente(newCuenta);
        CuentaBD.registrarCuentaEnBD(newCuenta);
        cuentasEnBD.add(newCuenta);
        JOptionPane.showMessageDialog(null, newCuenta.msgCreacion());
        this.crearCuenta.setVisible(false);
        this.menuInicial.setVisible(true);
        }
    }
    
    private boolean validarDatosCrearCuenta(){
        ArrayList<String> espacios = new ArrayList<>();
        espacios.add(this.crearCuenta.pinCrearCuenta.getText());
        espacios.add(this.crearCuenta.montoInicialCrearCuenta.getText());
        
        ArrayList<Boolean> atributos = new ArrayList<>();
        atributos.add(validaciones.ValidarTipoDeDato.validarEsEntero(this.crearCuenta.montoInicialCrearCuenta.getText()));
        atributos.add(validaciones.Validar.espaciosVacios(espacios));
        atributos.add(validaciones.ValidarTipoDeDato.validarFormatoPIN(this.crearCuenta.pinCrearCuenta.getText()));
        
        for(boolean comprobar: atributos){
            if(!comprobar){
                return false;
            }
        }
        return true;
    }
}
