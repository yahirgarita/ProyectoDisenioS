package controladores;
import gui.ConsultarEstatus;
import logicadeaccesoadatos.CuentaBD;
import logicadenegocios.CuentaBancaria;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import util.Encriptar;

/**
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 *
 * @version 1.0
 */

public class ControladorConsultarEstatus implements ActionListener{
    public ConsultarEstatus consultaEstatus;
    
    public ControladorConsultarEstatus(ConsultarEstatus pConsultarSaldo){
        consultaEstatus = pConsultarSaldo;
        consultaEstatus.verEstatus.addActionListener(this);
        consultaEstatus.btnVolver.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Ver Estatus": verEstatus();
                break;
            case "Volver":
                controladores.ControladoresGlobales.volver();
                this.consultaEstatus.setVisible(false);
                break;
            default:
                break;
       }
    }
    private void verEstatus(){
        String cuenta = this.consultaEstatus.numCuenta.getText();
        CuentaBancaria buscar = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(cuenta));
        if(buscar != null){
            JOptionPane.showMessageDialog(null,"La cuenta n° " + buscar.getNumCuenta() + " tiene el estatus de " + buscar.getEstatus());
        }else{
            JOptionPane.showMessageDialog(null, "El número de cuenta no existe.");
        }
    }
}
