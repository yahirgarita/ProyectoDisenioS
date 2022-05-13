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
import controladores.*;
import javax.swing.table.DefaultTableModel;

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
    private ArrayList<Persona> personasEnBD;
    
    
    /**
     * ControladorListarClientes
     * 
     * @param pListarClientes
     */
    public ControladorListarClientes(ListarClientes pClientes, Menu pMenuInicial){
        this.listarClientes = pClientes;
        this.menuInicial = pMenuInicial;
        this.personasEnBD = new ArrayList<>();
        this.listarClientes.botonConsultarClientes.addActionListener(this);
        this.listarClientes.botonVolver.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent evento){
        switch(evento.getActionCommand()){
            case "Consultar clientes":consultarListaClientes();
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
        String[] title = {"Primer apellido", "Segundo apellido", "Nombre", "Identificación"};
        String[] title2 = {"Nombre completo", "Identificación", "Fecha de nacimiento", "Teléfono", "Correo electrónico", "Código"};
        String[] title3 = {"Número de cuenta"};
        
        this.listarClientes = new ListarClientes();
        this.listarClientes.botonConsultarClientes.addActionListener(this);
        this.listarClientes.botonVolver.addActionListener(this);
        
        //Tabla con todos los clientes
        
        this.listarClientes.modelo = new DefaultTableModel(null,title);
        ListarClientes.tablaInfoCliente.setModel(this.listarClientes.modelo);
        this.listarClientes.modelo.setColumnIdentifiers(new Object[]{"Primer apellido","Segundo apellido", "Nombre", "Identificacion"});
        
        //Tabla para mostrar toda la informacion de la persona
        
        this.listarClientes.modeloInfoCliente = new DefaultTableModel(null, title2);
       
        ListarClientes.tablaInfoCliente.setModel(this.listarClientes.modeloInfoCliente);

        //Table de las cuentas
        
        this.listarClientes.modeloCuenta = new DefaultTableModel(null,title3);
        
        ListarClientes.tablaCuenta.setModel(this.listarClientes.modeloCuenta);

        for(Persona person: personasEnBD){
            Object[] msg = {person.getPrimerApellido(), person.getSegundoApellido(), person.getNombre(), person.getIdPersona()};
        }
        
        this.listarClientes.setVisible(true);
        this.menuInicial.setVisible(false);
        
    }
    private Persona verPersonaPorId(int pId){
        for(Persona persona: personasEnBD){
            if(persona.getIdPersona() == pId){
                return persona;
            }
        }
        return null;
    }
    private void consultarListaClientes(){
        this.listarClientes.modeloInfoCliente.setRowCount(0);
        this.listarClientes.modeloCuenta.setRowCount(0);
        int linea = this.listarClientes.tablaInfoCliente.getSelectedRow();
        
        Persona person1 = verPersonaPorId(Integer.parseInt(this.listarClientes.tablaInfoCliente.getModel().getValueAt(linea,3).toString()));
        Object[] datoPersona = { person1.getNombreCompleto(),person1.getIdPersona(),person1.getFechaNacimiento(),person1.getNumTelefonico(),
            person1.getCorreoPersona(), person1.getCodigo()};
        
        this.listarClientes.modeloInfoCliente.addRow(datoPersona);
        ArrayList<CuentaBancaria> cuentaBancariaCadena = PersonaBD.recuperarCuentasClientes(person1.getCodigo());
        for(CuentaBancaria cuentaBancaria: cuentaBancariaCadena){
            Object[] datoPersona2 = {cuentaBancaria.getNumCuenta(),};
            this.listarClientes.modeloCuenta.addRow(datoPersona2);
        } 
    }
}
