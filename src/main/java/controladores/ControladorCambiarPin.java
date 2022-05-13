package controladores;

import gui.*;
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
public class ControladorCambiarPin {
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
            this.cambiarPin2
            this.cambiarPin2.bo
        }        
    }
}
