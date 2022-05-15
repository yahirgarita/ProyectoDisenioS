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
public class SeleccionarClienteCLI {
    
    public void consultarInformacionCliente(String informacion){
        
        System.out.println("Infomación del cliente");
        System.out.println(informacion);
      
    }
    
    public void listarCuentas(int numeroCuenta){
        System.out.println("Numero de cuenta: " + numeroCuenta);
    }
}
