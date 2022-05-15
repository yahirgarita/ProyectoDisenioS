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
    
    public ControladorConsultarClientesCLI(){
        this.vistaClientes = new ConsultarClientesCLI();
        this.vistaSeleccionarCliente = new SeleccionarClienteCLI();
        this.lista = new  ArrayList<Persona>();
        this.convetirClientesAObj();
        this.organizarPersonas();
        
        
    }
    public void listarClientes(){
        for(int i = 0;i < lista.size(); i++){
            vistaClientes.listarClientes(lista.get(i).getPrimerApellido(), lista.get(i).getSegundoApellido(), 
                    lista.get(i).getNombre(), lista.get(i).getIdPersona());
        }
    }
    
    public void seleccionarCliente() throws IOException{
       
        String id = this.vistaClientes.selecionarCliente();
        Persona usuario = PersonaBD.recuperarClientePorID(Integer.parseInt(id));
        ArrayList<CuentaBancaria> cuentas = PersonaBD.recuperarCuentasClientes(usuario.getCodigo());
        this.vistaSeleccionarCliente.consultarInformacionCliente(usuario.toString());
        System.out.println("el tamano de la lista es: " + cuentas.size());
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
 
    public static void main(String argss[]) throws IOException{
        
        ControladorConsultarClientesCLI nuevo = new ControladorConsultarClientesCLI();
        nuevo.listarClientes();
        nuevo.seleccionarCliente();
    }
   
}
