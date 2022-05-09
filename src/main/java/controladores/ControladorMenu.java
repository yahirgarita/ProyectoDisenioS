package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import controladores.*;
import gui.*;

/**
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 *
 * @version 1.0
 */
public class ControladorMenu implements ActionListener{
     public Menu menuInicial;
     private RegistroCliente registroCliente ;
     private CrearCuenta crearCuentaBancaria;
     private ListarClientes listarClientes;
    /*private ListarCuenta listarCuentas;*/
     private CambiarPIN cambiarPIN;
     /**
      * Constructor de la clase controladorMenu
      * 
      * @param pMenuInicial
      */
     
     public ControladorMenu(Menu pMenuInicial){
        this.menuInicial = pMenuInicial;
        this.registroCliente = null;
        this.crearCuentaBancaria = null;
        this.listarClientes = null;
        this.cambiarPIN = null;
        this.menuInicial.botonRegistrarClienteMenu.addActionListener(this);
        this.menuInicial.butonCrearCuenta.addActionListener(this);
        this.menuInicial.botonListarClientes.addActionListener(this);
        this.menuInicial.botonListarCuentas.addActionListener(this);
        this.menuInicial.botonCambiarPin.addActionListener(this);
     }
     
     @Override
     public void actionPerformed(ActionEvent opcion){
         switch(opcion.getActionCommand()){
             case "Registrar cliente": registrarCliente();
                break;
             case "Crear cuenta bancaria": crearCuentaBancaria();
                break;
             case "Listar clientes": listarClientes();
                break;
             case "Cambiar PIN": cambiarPIN();
                break;
             default: break;
         }
     }

     private void registrarCliente(){
         RegistroCliente registroCli = new RegistroCliente();
         ControladorRegistroClientes contro = new ControladorRegistroClientes(registroCli);
         contro.registrarCliente.setVisible(true);
         this.menuInicial.setVisible(false);
     }
     private void crearCuentaBancaria(){
         CrearCuenta crearCuentaBan = new CrearCuenta();
       
         this.crearCuentaBancaria.setVisible(true);
         this.menuInicial.dispose();
     }
     private void listarClientes(){
         ListarClientes listarCli = new ListarClientes();
         ControladorListarClientes contro2 = new ControladorListarClientes(listarCli);
         contro2.listarClientes.setVisible(true);
         this.menuInicial.setVisible(false);
     }
     
     private void cambiarPIN(){
         this.cambiarPIN.setVisible(true);
         this.menuInicial.dispose();
     }
     private void volver(){
         this.menuInicial.dispose();
     }
}
