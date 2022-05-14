package controladores;

import gui.ListarClientes;
import gui.Menu;
import java.util.ArrayList;
import logicadenegocios.Persona;
import gui.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import javax.swing.table.DefaultTableModel;

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
    
    public ControladorListarCuentas(ListarCuentas pCuentas, Menu pMenuInicial){
        this.listarCuentas = pCuentas;
        this.menuInicial = pMenuInicial;
        this.personasEnBD = new ArrayList<>();
        this.listarCuentas.botonConsultarClientes.addActionListener(this);
        this.listarCuentas.botonVolver.addActionListener(this);
        this.listarCuentas.botonConsultarInfoCliente.addActionListener(this);
        convetirClientesAObj();
        organizarPersona();
    }  
    
    @Override
    public void actionPerformed(ActionEvent evento){
        switch(evento.getActionCommand()){
            case "Consultar clientes":consultarCuentas();
                break;
            case "Consultar información de un cliente": consultarListarCuentas();
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
    
    private void consultarClientes(){
        
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
        
        for(Persona person: personasEnBD){
            Object[] msg = {person.getPrimerApellido(), person.getSegundoApellido(), person.getNombre(), person.getIdPersona()};
            this.listarCuentas.modelo.addRow(msg);
            
        }
        this.listarCuentas.tablaCuentas.setModel(listarCuentas.modelo);
        this.menuInicial.setVisible(false);
    }
}
