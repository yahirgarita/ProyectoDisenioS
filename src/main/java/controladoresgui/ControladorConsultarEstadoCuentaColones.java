package controladoresgui;

import gui.*;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
        
import javax.swing.*;
import java.util.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import util.Encriptar;
import logicadeaccesoadatos.CuentaBD;
import validaciones.*;

import logicadenegocios.*;
import logicadeaccesoadatos.*;
import java.time.format.DateTimeFormatter;
import javax.mail.MessagingException;
import util.Email;
import util.TipoCambio;
import validaciones.*;
/**
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 *
 * @version 1.0
 */
public class ControladorConsultarEstadoCuentaColones implements ActionListener{
    public EstadoDeCuentaColones estadoCuentaColones;
    private int attempt = 0;
    private Menu menuInicial;
    private TipoCambio tipoCambio;
    
    public ControladorConsultarEstadoCuentaColones( EstadoDeCuentaColones pEstadoCuentaColones, Menu pMenuInicial){
        this.estadoCuentaColones = pEstadoCuentaColones;
        this.menuInicial = pMenuInicial;
        this.tipoCambio = new TipoCambio();
        this.estadoCuentaColones.Consultar.addActionListener(this);
        this.estadoCuentaColones.volverPin1.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent evento) {
        switch(evento.getActionCommand()){
            case "Consultar estado": {
                try {
                    realizarConsultaCuentaColones();
                } catch (MessagingException ex) {
                    Logger.getLogger(ControladorConsultarEstadoCuentaColones.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                Logger.getLogger(ControladorConsultarEstadoCuentaColones.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
               break;

            case "Volver":
                controladoresgui.ControladoresGlobales.volver();
                this.estadoCuentaColones.setVisible(false);
            default:
                break;
        }
    }
    
    public void realizarConsultaCuentaColones() throws MessagingException, IOException{
        CuentaBancaria numCuenta = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(this.estadoCuentaColones.numCuentaEstadoColones.getText()));
        if(numCuenta != null && !Objects.equals(numCuenta.getEstatus(),"Inactiva")){
            if(Objects.equals(numCuenta.getPin(),this.estadoCuentaColones.pinEstadoCuentaColones.getText())){
                ArrayList<Operacion> operaciones = OperacionBD.obtenerOperacionesPorNumCuenta(Encriptar.cifrar(this.estadoCuentaColones.numCuentaEstadoColones.getText()));
                String consulta = "";
                for(int i = 0;i < operaciones.size(); i++){
                    double monto = operaciones.get(i).getMonto();
                    if(operaciones.get(i).getMoneda().equals("Dólares")){
                        monto = operaciones.get(i).getMonto() * this.tipoCambio.getCompra();
                    }
                   consulta += mostrarEstadoCuenta(operaciones.get(i).getFechaOperacion().toString(),operaciones.get(i).getTipo(),Math.round(monto*100.0)/100.0,operaciones.get(i).getCargo());
                }
                JOptionPane.showMessageDialog(null,"FECHA \t\t TIPO \t\t MONTO \t\t CARGO \n" + consulta);
                attempt = 0;
                Operacion oper = new Operacion("consultas","colones",false,0, LocalDate.now());
                OperacionBD.realizarOperacionEnBD(oper, Encriptar.cifrar(String.valueOf(numCuenta.getNumCuenta())));
                this.estadoCuentaColones.setVisible(false);
                this.menuInicial.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(null, "El pin que indico es incorrecto");
                attempt++;
                comprobrarIntentos(Encriptar.cifrar(this.estadoCuentaColones.pinEstadoCuentaColones.getText()),this.estadoCuentaColones,"Se ha inactivado la cuenta por fallar el pin repetiadamente");
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "La cuenta que digito no existe o está inactiiva");
        }
    }
    
    public String mostrarEstadoCuenta(String fecha, String tipo, double monto, String cargo) throws IOException{
       
        return (fecha + " \t " + tipo + " \t " + monto + " \t\t " + cargo + "\n");
       
    }
    private  void comprobrarIntentos(String pNumCuenta, JFrame frame, String pMsg) throws MessagingException{
        
        Persona comparacionPersonaCuenta = CuentaBD.compararPersonaConCuenta(pNumCuenta);
        if(attempt == 2){
            CuentaBD.modificarEstado(pNumCuenta, "Inactiva");
            attempt = 0;
            JOptionPane.showMessageDialog(null, pMsg);
            Email.enviarEmail(comparacionPersonaCuenta.getCorreoPersona(), pMsg);
            frame.dispose();
            controladoresgui.ControladoresGlobales.volver();
        }
    }
}
