package controladoresgui;
import gui.ConsultarSaldoDolares;
import logicadeaccesoadatos.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import util.Encriptar;
import util.cambio;

/**
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 *
 * @version 1.0
 */

public class ControladorVerSaldoDolares implements ActionListener{
    public ConsultarSaldoDolares consultaDolares;
    private cambio tipoCambio = new cambio();
    
    public ControladorVerSaldoDolares(ConsultarSaldoDolares pConsultarSaldoDolares){
        consultaDolares = pConsultarSaldoDolares;
        consultaDolares.btnVerSaldo.addActionListener(this);
        consultaDolares.btnVolver.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Ver Saldo Dolares": verSaldo();
                break;
            case "Volver":
                controladoresgui.ControladoresGlobales.volver();
                this.consultaDolares.setVisible(false);
                break;
            default:
                break;
       }        
    }
    
    private void verSaldo(){
       String cuenta = this.consultaDolares.numCuenta.getText();
       String pin = this.consultaDolares.pinUsuario.getText();
       if(CuentaBD.recuperarCuentaPorNumPin(Encriptar.cifrar(cuenta), Encriptar.cifrar(pin)) != null){
           JOptionPane.showMessageDialog(null, "Estimado usuario el saldo actual de su cuenta es " + CuentaBD.recuperarCuentaPorNumPin(Encriptar.cifrar(cuenta), Encriptar.cifrar(pin)).getSaldo() / tipoCambio.getCompra() + " dólares.");
       }else{
           JOptionPane.showMessageDialog(null, "Error: Datos inválidos");
       } 
    } 
}