/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import validaciones.Validar;

/**
 *
 * @author Jimmy
 */
public class ConsultarEstadoCuentaCLI {
    
    public String consultarEstadoCuentaPedirCuenta() throws IOException{
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String cuenta;
        
        System.out.println("Por favor ingrese el numero de cuenta: ");
        while(Validar.existeCuenta(cuenta = reader.readLine()) != true){
            System.out.println("Por favor ingrese un numero de cuenta existente: ");
        }
        
        return cuenta;
    }   
    public String consultarEstadoCuentaPedirPin() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Por favor ingrese el numero de PIN de la cuenta: ");
        String pin;
        pin = reader.readLine();
        return pin;
    }
    
    public void mostrarEstadoCuentaTitulo(){
       
        System.out.println("FECHA \t\t TIPO \t\t MONTO \t\t CARGO");
       
    }
    public void mostrarEstadoCuenta(String fecha, String tipo, double monto, String cargo) throws IOException{
       
        System.out.println(fecha + " \t " + tipo + " \t " + monto + " \t\t " + cargo);
       
    }
    public void cuentaInactiva(){
        System.out.println("Estimado usuario esta cuenta esta inactiva.");
    }
}
