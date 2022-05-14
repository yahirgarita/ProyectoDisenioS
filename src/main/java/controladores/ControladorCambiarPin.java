package controladores;

import gui.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.JOptionPane;
import logicadenegocios.*;
import logicadeaccesoadatos.*;
import util.*;
/**
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 *
 * @version 1.0
 */
public class ControladorCambiarPin implements ActionListener{
    public CambiarPinPaso1 cambiarPin1;
    public CambiarPinPaso2 cambiarPin2;
    
    public ControladorCambiarPin(CambiarPinPaso1 pCambiarPin1, CambiarPinPaso2 pCambiarPin2){
        this.cambiarPin1 = pCambiarPin1;
        this.cambiarPin2 = pCambiarPin2;

    }
    
    private void verificarPin(){
        CuentaBancaria cuentaBanc = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(this.cambiarPin1.numCuentaPin.getText()));
        if(cuentaBanc != null && !Objects.equals(cuentaBanc.getEstatus(),"Inactiva")){
            this.cambiarPin2 = new CambiarPinPaso2();
            this.cambiarPin2.botonCambiarPin2.addActionListener(this);
            this.cambiarPin2.botonVolverPin2.addActionListener(this);
            this.cambiarPin2.jLabel4.setText(String.valueOf(CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(this.cambiarPin1.numCuentaPin.getText()))));
            this.cambiarPin2.setVisible(true);
            this.cambiarPin1.setVisible(false);
        }   
        else {
            JOptionPane.showMessageDialog(null, "La cuenta no existe");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
