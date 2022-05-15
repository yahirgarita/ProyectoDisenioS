/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cli;

/**
 *
 * @author Jimmy
 */
public class ConsultarTipoCambioCLI {
    public void ventaDolar(double precio){
        System.out.println("El precio de venta actual del dolar es de: "+ precio + " colones");
    }
    public void compraDolar(double precio){
        System.out.println("El precio de compra actual del dolar es de: "+ precio + " colones");
    }
}
