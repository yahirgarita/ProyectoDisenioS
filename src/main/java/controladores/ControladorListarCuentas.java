package controladores;

import gui.ListarClientes;
import gui.Menu;
import java.util.ArrayList;
import logicadenegocios.*;
import gui.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logicadeaccesoadatos.*;
import util.Encriptar;


/**
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 *
 * @version 1.0
 */
public class ControladorListarCuentas implements ActionListener{
    public ListarCuentas listarCuentas;
    private Menu menuInicial;
    private ArrayList<Persona> personasEnBD;
    private ArrayList<CuentaBancaria> cuentasEnBD;
    
    public ControladorListarCuentas(ListarCuentas pCuentas, Menu pMenuInicial){
        this.listarCuentas = pCuentas;
        this.menuInicial = pMenuInicial;
        this.personasEnBD = new ArrayList<>();
        this.cuentasEnBD = new ArrayList<>();
        this.listarCuentas.botonConsultarCuentas.addActionListener(this);
        this.listarCuentas.botonVolver.addActionListener(this);
        this.listarCuentas.botonConsultarInfoCliente.addActionListener(this);
        recolectarInfoCuentas();
        organizarPersona();
    }  
    
    @Override
    public void actionPerformed(ActionEvent evento){
        switch(evento.getActionCommand()){
            case "Consultar cuentas":{
                try {
                    consultarCuentas();
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorListarCuentas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                break;

            case "Consultar información de un cuenta": consultarListarCuentas();
                break;
            case "Volver":
                controladores.ControladoresGlobales.volver();
                this.listarCuentas.setVisible(false);
                break;
            default:
                break;
        }        
    }
    
    private void organizarPersona(){
        personasEnBD.sort(Comparator.comparing(Persona::getPrimerApellido));
    }
    
    private void consultarCuentas() throws SQLException{
        
        //Tabla con todos los clientes
        
        this.listarCuentas.modelo = new DefaultTableModel(){
            public boolean isCellEditable(int row,int column){
            return false;
            }
        };
        this.listarCuentas.modelo.addColumn("Número de cuenta");
        this.listarCuentas.modelo.addColumn("Estatus");
        this.listarCuentas.modelo.addColumn("Saldo");
        this.listarCuentas.modelo.addColumn("Dueño");
        this.listarCuentas.modelo.addColumn("Identificación");

        //Tabla para mostrar toda la informacion de la persona
        
        this.listarCuentas.modeloInfoCuenta = new DefaultTableModel(){
            public boolean isCellEditable(int row,int column){
            return false;
            }
        };
        this.listarCuentas.modeloInfoCuenta.addColumn("Número de cuenta");
        this.listarCuentas.modeloInfoCuenta.addColumn("Fecha de creación");
        this.listarCuentas.modeloInfoCuenta.addColumn("Saldo");
        this.listarCuentas.modeloInfoCuenta.addColumn("Estatus");       
        this.listarCuentas.tablaInfoCuenta.setModel(this.listarCuentas.modeloInfoCuenta);

        //Table de las cuentas
        
        this.listarCuentas.modeloCuenta = new DefaultTableModel(){
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        this.listarCuentas.modeloCuenta.addColumn("Fecha de la operación");
        this.listarCuentas.modeloCuenta.addColumn("Tipo de operación");
        this.listarCuentas.modeloCuenta.addColumn("Se realizó un cargo");
        this.listarCuentas.modeloCuenta.addColumn("Monto");
        this.listarCuentas.modeloCuenta.addColumn("Moneda");
        this.listarCuentas.tablaCuenta.setModel(this.listarCuentas.modeloCuenta);
        
        ArrayList<CuentaBancaria> cuentaBancCadena = recolectarInfoCuentas();
        for(CuentaBancaria count: cuentaBancCadena){
            Object[] msg = {count.getNumCuenta(),count.getEstatus(),count.getSaldo(),
            CuentaBD.saberDuenioCuenta(Encriptar.cifrar(String.valueOf(count.getNumCuenta()))).getNombreCompleto(),
            CuentaBD.saberDuenioCuenta(Encriptar.cifrar(String.valueOf(count.getNumCuenta()))).getIdPersona()};
            this.listarCuentas.modelo.addRow(msg);
            
        }
        this.listarCuentas.tablaCuentas.setModel(listarCuentas.modelo);
        this.menuInicial.setVisible(false);
    }
    private void consultarListarCuentas(){
        this.listarCuentas.modeloInfoCuenta.setRowCount(0);
        this.listarCuentas.modeloCuenta.setRowCount(0);
        int filaSeleccionada = this.listarCuentas.tablaCuentas.getSelectedRow();
        CuentaBancaria cuentaBanc = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(this.listarCuentas.tablaCuentas.getModel().getValueAt(filaSeleccionada,0).toString()));

        Object[] cliente1 = {cuentaBanc.getNumCuenta(),cuentaBanc.getFechaCreacion(), cuentaBanc.getSaldo(), cuentaBanc.getEstatus()};
        this.listarCuentas.modeloInfoCuenta.addRow(cliente1);
        ArrayList<Operacion> operacionCadena;
        operacionCadena = CuentaBD.saberLasOperacionesDeCuentas(Encriptar.cifrar((Integer.toString(cuentaBanc.getNumCuenta()))));
        
        for(Operacion oper: operacionCadena){
            Object[] cuentaBanc2 = {oper.getFechaOperacion(),oper.getTipo(),oper.getCargo(),oper.getMonto(),oper.getMoneda()};
            this.listarCuentas.modeloCuenta.addRow(cuentaBanc2);
        }
    }
    
    private ArrayList<CuentaBancaria> recolectarInfoCuentas() {
        ResultSet info = CuentaBD.recuperarTodaInfoCuenta();
        ArrayList<CuentaBancaria> cuentaBancCadena = new ArrayList<>();
        try{
            while(info.next()){
                CuentaBancaria cuentaBanc = new CuentaBancaria(Integer.parseInt(Encriptar.descifrar(info.getString("numeroCuenta"))),
                        LocalDate.parse(info.getString("fecha")), Double.parseDouble(Encriptar.descifrar(info.getString("saldo"))), 
                        Encriptar.descifrar(info.getString("pin")), info.getString("estatus"));
                cuentaBancCadena.add(cuentaBanc);
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
            return new ArrayList<>();
        }
        return cuentaBancCadena;
    } 
    
    private Persona verPersonaPorId(int pId){
        for(Persona persona: personasEnBD){
            if(persona.getIdPersona() == pId){
                return persona;
            }
        }
        return null;
    }
    
}
