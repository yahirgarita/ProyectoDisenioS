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
public class CambiarPinCLI {
    
    public void cambiarPin() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Por favor ingrese el numero de cuenta: ");
        String cuenta;
        while(Validar.numCuentasPresentesEnBD(cuenta = reader.readLine()) != true){
            System.out.println("Por favor ingrese un numero de cuenta existente: ");
        }
        
        System.out.println("Por favor ingrese el numero de PIN actual: ");
        String pin;
        while(Validar.numCuentasPresentesEnBD(cuenta = reader.readLine()) != true){
            System.out.println("Por favor ingrese un numero de cuenta existente: ");
        }
        
    }
}
