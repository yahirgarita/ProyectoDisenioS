/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorescli;

import cli.ConsultarClientesCLI;
import cli.SeleccionarClienteCLI;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.JOptionPane;
import logicadeaccesoadatos.PersonaBD;
import logicadenegocios.CuentaBancaria;
import logicadenegocios.Persona;

/**
 *
 * @author Jimmy
 */
public class ControladorConsultarClientesCLI {
    
    private ConsultarClientesCLI vistaClientes;
    private SeleccionarClienteCLI vistaSeleccionarCliente; 
    private ArrayList<Persona> lista;
    
    public ControladorConsultarClientesCLI(ConsultarClientesCLI vistaClientes, SeleccionarClienteCLI vistaSeleccionarCliente){
        this.vistaClientes = vistaClientes;
        this.vistaSeleccionarCliente = vistaSeleccionarCliente;
        this.lista = new  ArrayList<Persona>();
        this.convetirClientesAObj();
        this.organizarPersonas();
        
        
    }
    public void listarClientes() throws IOException{
        for(int i = 0;i < lista.size(); i++){
            vistaClientes.listarClientes(lista.get(i).getPrimerApellido(), lista.get(i).getSegundoApellido(), 
                    lista.get(i).getNombre(), lista.get(i).getIdPersona());
        }
        if(this.vistaClientes.consultarCliente().equals("1")){
            seleccionarCliente();
        }
    }
    
    private void seleccionarCliente() throws IOException{
       
        String id = this.vistaClientes.selecionarCliente();
        Persona usuario = PersonaBD.recuperarClientePorID(Integer.parseInt(id));
        ArrayList<CuentaBancaria> cuentas = PersonaBD.recuperarCuentasClientes(usuario.getCodigo());
        this.vistaSeleccionarCliente.consultarInformacionCliente(usuario.toString());
        for(int i = 0;i<cuentas.size();i++){
            this.vistaSeleccionarCliente.listarCuentas(cuentas.get(i).getNumCuenta());
            
        }
        
    }
    
    private void organizarPersonas(){
        lista.sort(Comparator.comparing(Persona::getPrimerApellido));
    }
    
    private void convetirClientesAObj(){
        ResultSet resultado = PersonaBD.cargarTodosLosClientes();
        try{
            while(resultado.next()){
                Persona infoCliente = new Persona(resultado.getString("primerApellido"),
                resultado.getString("segundoApellido"), resultado.getString("nombre"), Integer.parseInt(resultado.getString("identificacion")), LocalDate.parse(resultado.getString("fechaNacimiento"),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")), Integer.parseInt(resultado.getString("telefono")), resultado.getString("correo"));
                infoCliente.setCodigo(resultado.getString("codigo"));
                lista.add(infoCliente);
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
    }
}
