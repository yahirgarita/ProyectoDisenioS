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
import javax.swing.*;
import util.Ordenamiento;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
     * @param pClientes
     * @param pMenuInicial
     * @param pListarClientes
     */
    public ControladorListarClientes(ListarClientes pClientes, Menu pMenuInicial){
        this.listarClientes = pClientes;
        this.menuInicial = pMenuInicial;
        this.personasEnBD = new ArrayList<>();
        this.listarClientes.botonConsultarClientes.addActionListener(this);
        this.listarClientes.botonVolver.addActionListener(this);
        this.listarClientes.botonConsultarInfoCliente.addActionListener(this);
        convetirClientesAObj();
        Ordenamiento.ordenarAscendentemente(personasEnBD);

    }
    
    @Override
    public void actionPerformed(ActionEvent evento){
        switch(evento.getActionCommand()){
            case "Consultar clientes":consultarClientes();
                break;
            case "Consultar información de un cliente": consultarListarClientes();
                break;
            case "Volver":
                controladores.ControladoresGlobales.volver();
                this.listarClientes.setVisible(false);
                break;
            default:
                break;
       }        
    }
    private void organizarPersona(){
        Ordenamiento.ordenarAscendentemente(personasEnBD);
    }
    
    
    private void convetirClientesAObj(){
        ResultSet resultado = PersonaBD.cargarTodosLosClientes();
        try{
            while(resultado.next()){
                Persona infoCliente = new Persona(resultado.getString("primerApellido"),
                resultado.getString("segundoApellido"), resultado.getString("nombre"), Integer.parseInt(resultado.getString("identificacion")), LocalDate.parse(resultado.getString("fechaNacimiento"),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")), Integer.parseInt(resultado.getString("telefono")), resultado.getString("correo"));
                infoCliente.setCodigo(resultado.getString("codigo"));
                personasEnBD.add(infoCliente);
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
    }
    private void consultarClientes(){
        
        //Tabla con todos los clientes
        
        this.listarClientes.modelo = new DefaultTableModel(){
            public boolean isCellEditable(int row,int column){
            return false;
            }
        };
        this.listarClientes.modelo.addColumn("Primer apellido");
        this.listarClientes.modelo.addColumn("Segundo apellido");
        this.listarClientes.modelo.addColumn("Nombre");
        this.listarClientes.modelo.addColumn("Identificación");

        //Tabla para mostrar toda la informacion de la persona
        
        this.listarClientes.modeloInfoCliente = new DefaultTableModel(){
            public boolean isCellEditable(int row,int column){
            return false;
            }
        };
        this.listarClientes.modeloInfoCliente.addColumn("Nombre completo");
        this.listarClientes.modeloInfoCliente.addColumn("Identificación");
        this.listarClientes.modeloInfoCliente.addColumn("Fecha de nacimiento");
        this.listarClientes.modeloInfoCliente.addColumn("Teléfono");
        this.listarClientes.modeloInfoCliente.addColumn("Correo electrónico");
        this.listarClientes.modeloInfoCliente.addColumn("Código");        
        this.listarClientes.tablaInfoCliente.setModel(this.listarClientes.modeloInfoCliente);

        //Table de las cuentas
        
        this.listarClientes.modeloCuenta = new DefaultTableModel(){
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        this.listarClientes.modeloCuenta.addColumn("Número de cuenta");
        this.listarClientes.tablaCuenta.setModel(this.listarClientes.modeloCuenta);
        
        for(Persona person: personasEnBD){
            Object[] msg = {person.getPrimerApellido(), person.getSegundoApellido(), person.getNombre(), person.getIdPersona()};
            this.listarClientes.modelo.addRow(msg);
            
        }
        this.listarClientes.tablaClientes.setModel(listarClientes.modelo);
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
    
    private void consultarListarClientes(){
        this.listarClientes.modeloInfoCliente.setRowCount(0);
        this.listarClientes.modeloCuenta.setRowCount(0);
        int filaSeleccionada = this.listarClientes.tablaClientes.getSelectedRow();
        
        Persona person = verPersonaPorId(Integer.parseInt(this.listarClientes.tablaClientes.getModel().getValueAt(filaSeleccionada,3).toString()));

        Object[] cliente1 = {person.getNombreCompleto(),person.getIdPersona(), person.getFechaNacimiento(),
            person.getNumTelefonico(), person.getCorreoPersona(), person.getCodigo()};
        this.listarClientes.modeloInfoCliente.addRow(cliente1);
        ArrayList<CuentaBancaria> cuentaBancariaArrayList = PersonaBD.recuperarCuentasClientes(person.getCodigo());
        for(CuentaBancaria cuentaBanc:cuentaBancariaArrayList){
            Object[] cliente2 = {cuentaBanc.getNumCuenta(),};
            this.listarClientes.modeloCuenta.addRow(cliente2);
        }
    }
    
    
}
