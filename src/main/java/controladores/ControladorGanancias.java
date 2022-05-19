package controladores;


import gui.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import logicadeaccesoadatos.*;
import logicadenegocios.*;
import util.Email;
import util.Encriptar;
import validaciones.*;
import util.SMS;
import util.TipoCambio;
import validaciones.*;
/**
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 *
 * @version 1.0
 */

public class ControladorGanancias implements ActionListener{
    Ganancias ganancias;
    
    public ControladorGanancias(Ganancias pGanancias){
        this.ganancias = pGanancias;
        this.ganancias.consultarGanancias.addActionListener(this);
        this.ganancias.volverGanancias1.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Consultar": consultarGanancias();
                break;
            case "Volver":
                controladores.ControladoresGlobales.volver();
                this.ganancias.setVisible(false);
                break;
            default:
                break;
       }
    }
    
    private void consultarGanancias(){
        CuentaBancaria cuentaBanc = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(this.ganancias.numCuentaGanancias.getText()));
        if(cuentaBanc != null){
            JOptionPane.showMessageDialog(null, "El total de comisiones por concepto de retiros es de: " 
                    + CuentaBD.totalComisionesCuentaRetiros(Encriptar.cifrar(String.valueOf(cuentaBanc.getNumCuenta()))) + " colones \n" + 
                     "El total de comisiones por concepto de dépositos es de: " 
                    + CuentaBD.totalComisionesCuentaDepositos(Encriptar.cifrar(String.valueOf(cuentaBanc.getNumCuenta()))) + " colones \n" + 
                    "Las ganancias que tuvo el banco del cobro de comisiones para la cuenta " + cuentaBanc.getNumCuenta() + 
                    " es de " + CuentaBD.totalComisionesCuenta(Encriptar.cifrar(String.valueOf(cuentaBanc.getNumCuenta()))) + " colones");
        }
        else{
            JOptionPane.showMessageDialog(null, "El número de cuenta que ingreso no existe");
        }
    }
}
