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
import logicadeaccesoadatos.CuentaBD;

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

public class ControladorCrearCuenta implements ActionListener{
    public CreacionCuenta crearCuenta;
    private Menu menuInicial;
    private ArrayList<CuentaBancaria> cuentasEnBD;
    private Persona clienteActual;
    private ArrayList<Persona> personasSistema;

    
    
    /**
     * ControladorListarClientes
     * 
     * @param pCrearCuenta
     */
    public ControladorCrearCuenta(CreacionCuenta pCrearCuenta){
        this.crearCuenta = pCrearCuenta;
        this.menuInicial = null;
        this.clienteActual = null;
        this.cuentasEnBD = new ArrayList<>();
        this.personasSistema = new ArrayList<>();
        this.crearCuenta.botonRegistrarC.addActionListener(this);
        this.crearCuenta.botonVolverC.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent evento){
        switch(evento.getActionCommand()){
            case "Registrar":crearCuenta();
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
        System.out.println("p"); 
        if(validarDatosCrearCuenta()){
            CuentaBancaria newCuenta = new CuentaBancaria(numRandomCuenta, Double.parseDouble(this.crearCuenta.montoInicialCrearCuenta.getText()),
                    this.crearCuenta.pinCrearCuenta.getText(), LocalDate.now());
            System.out.println("ada"); 

            newCuenta.afiliarDuenio(this.clienteActual);
            this.clienteActual.aniadirCuentaBancariaCliente(newCuenta);
            CuentaBD.registrarCuentaEnBD(newCuenta);
            cuentasEnBD.add(newCuenta);
            JOptionPane.showMessageDialog(null, newCuenta.msgCreacion());
            this.crearCuenta.setVisible(false);
            this.menuInicial.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Error, revise los datos ingresados");
        }
        
    }
    
    private boolean validarDatosCrearCuenta(){
        ArrayList<String> espacios = new ArrayList<>();
        espacios.add(this.crearCuenta.pinCrearCuenta.getText());
        espacios.add(this.crearCuenta.montoInicialCrearCuenta.getText());
        System.out.println("p"); 

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
    
        
    private void cargarInfoPersonas(){
        ResultSet info = PersonaBD.cargarTodosLosClientes();
        try{
            while(info.next()){
                Persona infoCliente = new Persona(info.getString("primerApellido"),
                info.getString("segundoApellido"), info.getString("nombre"), Integer.parseInt(info.getString("identificacion")), LocalDate.parse(info.getString("fechaNacimiento"),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")), Integer.parseInt(info.getString("telefono")), info.getString("correo"));
                infoCliente.setCodigo(info.getString("codigo"));
                personasSistema.add(infoCliente);
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
        
    }
}
