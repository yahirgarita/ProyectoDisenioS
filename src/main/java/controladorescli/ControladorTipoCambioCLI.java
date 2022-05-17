/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorescli;

import cli.ConsultarTipoCambioCLI;
import util.cambio;

/**
 *
 * @author Jimmy
 */
public class ControladorTipoCambioCLI {
    
    private cambio precio;  
    
    private ConsultarTipoCambioCLI vista;
    
    public ControladorTipoCambioCLI(ConsultarTipoCambioCLI vista){
        this.precio = new cambio();
        this.vista = vista;   
    }
    
    public void consultarVenta(){
        this.vista.ventaDolar(precio.getVenta());
    }
    
    public void consultarCompra(){
        this.vista.compraDolar(precio.getCompra());
    }
}
