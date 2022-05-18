package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import controladores.*;
import gui.*;
import javax.swing.*;
import logicadeaccesoadatos.CuentaBD;

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
        this.menuInicial.btnConsultarSaldoDolares.addActionListener(this);  
        this.menuInicial.btnDepositarDolares1.addActionListener(this);
        this.menuInicial.btnRetiroEnColones.addActionListener(this);
        this.menuInicial.btnCompraDolar.addActionListener(this);
        this.menuInicial.btnVentaDolar.addActionListener(this);
        this.menuInicial.btnRetiroEnDolares1.addActionListener(this);
        this.menuInicial.btnrealizarTransferencia.addActionListener(this);
        this.menuInicial.btnVerEstatus.addActionListener(this);
        this.menuInicial.btnGananciasCuenta.addActionListener(this);
        this.menuInicial.btnGananciaBanco.addActionListener(this);
        this.menuInicial.btnCerrar.addActionListener(this);
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
             case "Retiro en Dolares": realizarRetiroDolares();
                break;
             case "Saldo actual en Dólares": verSaldoDolares();
                break;
             case "Tipo de Cambio Venta": consultarTipoCambioVenta();
                break;
             case "Tipo de Cambio Compra": consultarTipoCambioCompra();
                break;
             case "Realizar transferencia": realizarTransferencia();
                break;
             case "Ver estatus de cuenta": verEstatus();
                break;
             case " Ganancias comisiones por cuenta": consultarGananciasCuenta();
                break;
             case "Ganancia comisiones banco": consultarGananciasTotales();
                break;
             case "Cerrar": cerrar();
                default:break;
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
     
     private void realizarRetiroDolares(){
        RetiroDolaresPaso1 retiroDolares1 = new RetiroDolaresPaso1();
        RetiroDolaresPaso2 retiroDolares2 = new RetiroDolaresPaso2();
        RetiroDolaresPaso3 retiroDolares3 = new RetiroDolaresPaso3();
        RetiroDolaresPaso4 retiroDolares4 = new RetiroDolaresPaso4();
        ControladorRetiroDolares controRetiroD = new ControladorRetiroDolares(retiroDolares1,retiroDolares2,retiroDolares3,retiroDolares4, menuInicial);
        controRetiroD.retiroDolares1.setVisible(true);
        this.menuInicial.setVisible(false);
     }
     
     private void realizarTransferencia(){
        TransferenciaPaso1 transferencia1 = new TransferenciaPaso1();
        TransferenciaPaso2 transferencia2 = new TransferenciaPaso2(); 
        TransferenciaPaso3 transferencia3 = new TransferenciaPaso3();
        TransferenciaPaso4 transferencia4 = new TransferenciaPaso4();
        ControladorTransferencia controTrans = new ControladorTransferencia(transferencia1, transferencia2, transferencia3, transferencia4, menuInicial);
        controTrans.transferencia1.setVisible(true);
        this.menuInicial.setVisible(false);
     }
     
     private void consultarTipoCambioCompra(){
        ConsultarCompraDolar vista = new ConsultarCompraDolar();
        ControladorCompraDolar nuevo = new ControladorCompraDolar(vista);
        nuevo.compraDolar.setVisible(true);
        this.menuInicial.setVisible(false);
     }
     
     private void consultarTipoCambioVenta(){
        ConsultarVentaDolar vista = new ConsultarVentaDolar();
        ControladorVentaDolar nuevo = new ControladorVentaDolar(vista);
        nuevo.ventaDolar.setVisible(true);
        this.menuInicial.setVisible(false);
     }
     
     private void verEstatus(){
         ConsultarEstatus vista = new ConsultarEstatus();
         ControladorConsultarEstatus controlEstatus = new ControladorConsultarEstatus(vista);
         controlEstatus.consultaEstatus.setVisible(true);
         this.menuInicial.setVisible(false);
     }
     
     private void consultarGananciasCuenta(){
         Ganancias ganan = new Ganancias();
         ControladorGanancias controGanan = new ControladorGanancias(ganan);
         controGanan.ganancias.setVisible(true);
         this.menuInicial.setVisible(false);
     }
     
     private void consultarGananciasTotales(){
         JOptionPane.showMessageDialog(null, "El monto total de comisiones por concepto de depósitos es de: " + 
                 CuentaBD.totalComisionesBancoDepositos() + " colones\n" +
                 "El monto total de comisiones por concepto de retiros es de: " + CuentaBD.totalComisionesBancoRetiros() + " colones\n" +
                 "El monto total del cobro de comisiones es de: " + CuentaBD.totalComisiones() + " colones\n"); 
     }

     private void cerrar(){
         System.exit(0);
     }
}