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
       
        int opcion;
        while(true){
            opcion = menu.mostrarMenu();
            switch(opcion){
               case 1:
                   registrarCliente();
                   break;
               case 2:
                   crearCuentaBancaria();
                   break;
               case 3:
                   listarClientes();
                   break;
               case 4:
                   listarCuentas();
                   break;
               case 5:
                   cambiarPIN();
                   break;
               case 6:
                   realizarDepositoEnColones();
                   break;
               case 7:
                   realizarDepositoEnDolares();
                   break;
               case 8:
                   realizarRetiroEnColones();
                   break;
               case 9:
                   realizarRetiroEnDolares();
                   break;
               case 10:
                   realizarTransferencia();
                   break;
               case 11:
                   consultarTipoCambioCompra();
                   break;
               case 12:
                   consultarTipoCambioVenta();
                   break;
               case 13:
                   verSaldoActual();
                   break;
               case 14:
                   verSaldoDolares();
                   break;
               case 15:
                   consultarEstadoCuentaColones();
                   break;
               case 16:
                   consultarEstadoCuentaDolares();
                   break;
               case 17:
                   consultarEstatusCuenta();
                   break;
               case 18:
                   consultarGananciasTotales();
                   break;
               case 19:
                   consultarGananciasPorCuenta();
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
     
    private void verSaldoActual() throws IOException, MessagingException{
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
    
    private void realizarRetiroEnColones() throws IOException, MessagingException{
        RealizarRetiroCLI vista = new RealizarRetiroCLI();
        ControladorRealizarRetiroCLI controlador = new ControladorRealizarRetiroCLI(vista);
        controlador.realizarRetiroColones();
    }
     
    private void realizarRetiroEnDolares() throws IOException, MessagingException{
        RealizarRetiroCLI vista = new RealizarRetiroCLI();
        ControladorRealizarRetiroCLI controlador = new ControladorRealizarRetiroCLI(vista);
        controlador.realizarRetiroDolares();
    }
    private void verSaldoDolares() throws IOException, MessagingException{
        ConsultarSaldoActualCLI vista = new ConsultarSaldoActualCLI();
        ControladorConsultarSaldoActualCLI controlador = new ControladorConsultarSaldoActualCLI(vista);
        controlador.consultarSaldoActualDolares();
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
    
    private void realizarTransferencia() throws IOException, MessagingException{
       RealizarTransferenciaCLI vista = new RealizarTransferenciaCLI();
       ControladorRealizarTransferenciaCLI controlador = new ControladorRealizarTransferenciaCLI(vista);
       controlador.realizarTransferencia();
       
    }
    
    private void consultarEstatusCuenta() throws IOException{
       ConsultarEstatusCuentaCLI vista = new ConsultarEstatusCuentaCLI();
       ControladorConsultarEstatusCuentaCLI controlador = new ControladorConsultarEstatusCuentaCLI(vista);
       controlador.consultarEstatus();
    }
    
    private void consultarGananciasTotales() throws IOException{
       ConsultarGananciasCLI vista = new ConsultarGananciasCLI();
       ControladorConsultarGananciasCLI controlador = new ControladorConsultarGananciasCLI(vista);
       controlador.consultarGananciasTotales();
    }
    
    private void consultarGananciasPorCuenta() throws IOException{
       ConsultarGananciasCLI vista = new ConsultarGananciasCLI();
       ControladorConsultarGananciasCLI controlador = new ControladorConsultarGananciasCLI(vista);
       controlador.consultarGananciasPorCuenta();
    }
    
    private void consultarEstadoCuentaColones() throws IOException, MessagingException{
        ConsultarEstadoCuentaCLI vista = new ConsultarEstadoCuentaCLI();
        ControladorConsultarEstadoCuentaCLI controlador = new ControladorConsultarEstadoCuentaCLI(vista);
        controlador.consultarEstadoCuentaColones();
    }
    
    private void consultarEstadoCuentaDolares() throws IOException, MessagingException{
        ConsultarEstadoCuentaCLI vista = new ConsultarEstadoCuentaCLI();
        ControladorConsultarEstadoCuentaCLI controlador = new ControladorConsultarEstadoCuentaCLI(vista);
        controlador.consultarEstadoCuentaDolares();
    }

}
