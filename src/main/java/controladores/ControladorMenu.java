package controladores;

import newpackage.RetiroColonesPaso1;
import newpackage.RetiroColonesPaso2;
import newpackage.RetiroColonesPaso3;
import newpackage.RetiroColonesPaso4;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import controladores.*;
import gui.*;
import javax.swing.*;

/**
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 *
 * @version 1.0
 */
public class ControladorMenu implements ActionListener{
     public Menu menuInicial;
     /**
      * Constructor de la clase controladorMenu
      * 
      * @param pMenuInicial
      */
     
     public ControladorMenu(Menu pMenuInicial){
        this.menuInicial = pMenuInicial;
        this.menuInicial.botonRegistrarClienteMenu1.addActionListener(this);
        this.menuInicial.btnConsultarSaldo.addActionListener(this);
        this.menuInicial.butonCrearCuenta.addActionListener(this);
        this.menuInicial.botonListarClientes.addActionListener(this);
        this.menuInicial.botonListarCuentas.addActionListener(this);
        this.menuInicial.botonCambiarPin.addActionListener(this);
        this.menuInicial.btnDepositarColones.addActionListener(this);
        this.menuInicial.btnConsultarSaldo.addActionListener(this);
        this.menuInicial.btnConsultarSaldoDolares.addActionListener(this);  
        this.menuInicial.btnDepositarDolares1.addActionListener(this);
        this.menuInicial.btnRetiroEnColones.addActionListener(this);
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
             case "Listar cuentas": listarCuentas();
                break;
             case "Depositar en Colones": realizarDepositoEnColones();
                break;
             case "Depositar en Dolares": realizarDepositoEnDolares();
                break;
             case "Consultar saldo actual": verSaldoActual();
                break;
             case "Retiro en Colones":realizarRetiroColones();
                break;
             case "Saldo actual en Dólares": verSaldoDolares();
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
         CreacionCuenta nuevaCuenta = new CreacionCuenta();
         ControladorCrearCuenta controlNuevaCuenta = new ControladorCrearCuenta(nuevaCuenta,menuInicial); 
         controlNuevaCuenta.crearCuenta.setVisible(true);
         this.menuInicial.setVisible(false);
     }
     private void listarClientes(){
         ListarClientes listarCli = new ListarClientes();
         ControladorListarClientes contro2 = new ControladorListarClientes(listarCli, menuInicial);
         contro2.listarClientes.setVisible(true);
         this.menuInicial.setVisible(false);
     }
     
     private void cambiarPIN(){
         CambiarPinPaso1 cambiarP = new CambiarPinPaso1();
         CambiarPinPaso2 cambiarP2 = new CambiarPinPaso2();
         ControladorCambiarPin contro3 = new ControladorCambiarPin(cambiarP,cambiarP2);
         contro3.cambiarPin1.setVisible(true);
         this.menuInicial.setVisible(false);
     }
     
     private void listarCuentas(){
         ListarCuentas listarCuen = new ListarCuentas();
         ControladorListarCuentas contro2 = new ControladorListarCuentas(listarCuen, menuInicial);
         contro2.listarCuentas.setVisible(true);
         this.menuInicial.setVisible(false);
     }
     
     private void verSaldoActual(){
         ConsultarSaldo verSaldo = new ConsultarSaldo();
         ControladorVerSaldo control4 = new ControladorVerSaldo(verSaldo);
         control4.consultaSaldo.setVisible(true);
         this.menuInicial.setVisible(false);
     }

     private void realizarDepositoEnColones(){
         RealizarDepositoEnColones depositoEnColones = new RealizarDepositoEnColones();
         RealizarDepositoEnColonesPaso2 depositoColones2 = new RealizarDepositoEnColonesPaso2();
         ControladoDepositoColones controColones = new ControladoDepositoColones(depositoEnColones, depositoColones2);
         controColones.realizarDepositoC.setVisible(true);
         this.menuInicial.setVisible(false);
     }
     
     private void realizarDepositoEnDolares(){
         RealizarDepositoEnDolaresPaso1 depositoEnDolares = new RealizarDepositoEnDolaresPaso1();
         RealizarDepositoEnDolaresPaso2 depositoDolares2 = new RealizarDepositoEnDolaresPaso2();
         ControladorDepositoDolares controDolares = new ControladorDepositoDolares(depositoEnDolares, depositoDolares2);
         controDolares.depositoDolares1.setVisible(true);
         this.menuInicial.setVisible(false);
     }
     
     private void verSaldoDolares(){
         ConsultarSaldoDolares verDolares = new ConsultarSaldoDolares();
         ping control5 = new ping(verDolares);
         control5.consultaDolares.setVisible(true);
         this.menuInicial.setVisible(false);
     }
     
     private void realizarRetiroColones(){
        RetiroColonesPaso1 retiroColones1 = new RetiroColonesPaso1();
        RetiroColonesPaso2 retiroColones2 = new RetiroColonesPaso2();
        RetiroColonesPaso3 retiroColones3 = new RetiroColonesPaso3();
        RetiroColonesPaso4 retiroColones4 = new RetiroColonesPaso4();
        ControladorRetiroColones controRetiroC = new ControladorRetiroColones(retiroColones1,retiroColones2,retiroColones3,retiroColones4, menuInicial);
        controRetiroC.retiroColones1.setVisible(true);
        this.menuInicial.setVisible(false);
     }
     
     private void volver(){
         this.menuInicial.dispose();
     }
}
