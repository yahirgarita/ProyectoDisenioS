/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorescli;

import cli.*;
import java.io.IOException;
import java.sql.SQLException;
import javax.mail.MessagingException;

/**
 *
 * @author Jimmy
 */
public class ControladorMenuCLI {
    
    MenuCLI menu;
    
    public ControladorMenuCLI(MenuCLI menu){
        this.menu = menu;
    }
    public void listarMenu() throws IOException, SQLException, MessagingException{
       
        String opcion;
        while((opcion = menu.mostrarMenu()) < 21 ){
           switch(opcion){
               case 1:
                   registrarCliente();
               case 2:
                   crearCuentaBancaria();
               case 3:
                   listarClientes();
               case 4:
                   listarCuentas();
               case 5:
                   cambiarPIN();
               case 6:
                   realizarDepositoEnColones();
               case 7:
                   realizarDepositoEnDolares();
               case 8:
                   break;
               case 9:
                   break;
               case 10:
                   break;
               case 11:
                   consultarTipoCambioCompra();
               case 12:
                   consultarTipoCambioVenta();
               case 13:
                   verSaldoActual();
               case 14:
                   verSaldoDolares();
               case 15:
                   break;
               case 16:
                   break;
               case 17:
                   break;
               case 18:
                   break;
               case 19:
                   break;
               case 20:
                   System.out.println("Saliendo del sistema");
                   return;

           }
        }

    }
    
    private void registrarCliente() throws IOException, SQLException{
        
        RegistrarClienteCLI vista = new RegistrarClienteCLI();
        ControladorRegistrarClienteCLI controlador = new ControladorRegistrarClienteCLI(vista);
        controlador.registrarCliente();
    }
    private void crearCuentaBancaria() throws IOException{
        RegistrarCuentaCLI vista = new RegistrarCuentaCLI();
        ControladorRegistrarCuentaCLI controlador = new ControladorRegistrarCuentaCLI(vista);
        controlador.registrarCuenta();
    }
    private void listarClientes() throws IOException{
        ConsultarClientesCLI vistaLista = new ConsultarClientesCLI();
        SeleccionarClienteCLI vistaConsultar = new SeleccionarClienteCLI();
        ControladorConsultarClientesCLI controlador = new ControladorConsultarClientesCLI(vistaLista,vistaConsultar);
        controlador.listarClientes();  
    }
     
    private void cambiarPIN() throws IOException, MessagingException{
        CambiarPinCLI vista = new CambiarPinCLI();
        ControladorCambiarPINCLI controlador = new ControladorCambiarPINCLI(vista);
        controlador.cambiarPinPedirCuenta();
        
    }
     
    private void listarCuentas() throws IOException{
        ConsultarCuentasCLI vista = new ConsultarCuentasCLI();
        ControladorConsultarCuentasCLI controlador = new ControladorConsultarCuentasCLI(vista);
        controlador.listarCuentas();
    }
     
    private void verSaldoActual() throws IOException{
        ConsultarSaldoActualCLI vista = new ConsultarSaldoActualCLI();
        ControladorConsultarSaldoActualCLI controlador = new ControladorConsultarSaldoActualCLI(vista);
        controlador.consultarSaldoActual();
    }

    private void realizarDepositoEnColones() throws IOException{
        RealizarDepositoCLI vista = new RealizarDepositoCLI();
        ControladorRealizarDepositoCLI controlador = new ControladorRealizarDepositoCLI(vista);
        controlador.realizarDepositoColones();
    }
     
    private void realizarDepositoEnDolares() throws IOException{
        RealizarDepositoCLI vista = new RealizarDepositoCLI();
        ControladorRealizarDepositoCLI controlador = new ControladorRealizarDepositoCLI(vista);
        controlador.realizarDepositoDolares();
    }
     
    private void verSaldoDolares() throws IOException{
        ConsultarSaldoActualCLI vista = new ConsultarSaldoActualCLI();
        ControladorConsultarSaldoActualCLI controlador = new ControladorConsultarSaldoActualCLI(vista);
        controlador.consultarSaldoActualDolares();
    }
     
    private void realizarRetiroColones(){
      
    }
     
    private void consultarTipoCambioCompra(){
        ConsultarTipoCambioCLI vista = new ConsultarTipoCambioCLI();
        ControladorTipoCambioCLI controlador = new ControladorTipoCambioCLI(vista);
        controlador.consultarCompra();
    }
     
    private void consultarTipoCambioVenta(){
        ConsultarTipoCambioCLI vista = new ConsultarTipoCambioCLI();
        ControladorTipoCambioCLI controlador = new ControladorTipoCambioCLI(vista);
        controlador.consultarVenta();
    }
    
    public static void main(String[] args) throws IOException, SQLException, MessagingException{
        MenuCLI vista = new MenuCLI();
        ControladorMenuCLI nuevo =  new ControladorMenuCLI(vista);
        nuevo.listarMenu();
    }
        
   
}
