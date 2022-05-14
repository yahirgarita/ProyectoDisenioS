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
import validaciones.ValidarTipoDeDato;

/**
 *
 * @author Jimmy
 */
public class CambiarPinCLI {
    
    public String cambiarPinPedirCuenta() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String cuenta;
        
        System.out.println("Por favor ingrese el numero de cuenta: ");
        while(Validar.existeCuenta(cuenta = reader.readLine()) != true){
            System.out.println("Por favor ingrese un numero de cuenta existente: ");
        }
        return cuenta;
    }
    
    public String cambiarPinPedirPinActual() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Por favor ingrese el numero de PIN actual: ");
        String pin;
        pin = reader.readLine();
        return pin;
        
    }
    
    public String cambiarPinPedirPinNuevo() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Por favor ingrese el numero de PIN nuevo con un formato correcto: ");
        String pin;
        while(ValidarTipoDeDato.validarFormatoPIN(pin = reader.readLine())!= true){
            System.out.println("Por favor ingresar un formato de PIN valido: ");
        }
        return pin;
        
    }
    
    public void cambiarPinCompletado(String numeroCuenta) throws IOException{

        System.out.println("Estimado usuario, se ha cambiado satisfactoriamente el PIN de su cuenta: " + numeroCuenta);

    }
    
}
