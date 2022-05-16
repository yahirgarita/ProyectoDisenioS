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
public class RealizarRetiroCLI {
    
    public String realizarRetiroPedirCuenta() throws IOException{
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String cuenta;
        
        System.out.println("Por favor ingrese el numero de cuenta: ");
        while(Validar.existeCuenta(cuenta = reader.readLine()) != true){
            System.out.println("Por favor ingrese un numero de cuenta existente: ");
        }
        
        return cuenta;
    }   
    public String realizarRetiroPedirPin() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Por favor ingrese el numero de PIN de la cuenta: ");
        String pin;
        pin = reader.readLine();
        return pin;
    }
    
    public String realizarRetiroPedirSMS() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Estimado usuario se ha enviado una palabra por mensaje de texto, por favor revise sus mensajes y proceda a digitar la palabra enviada: ");
        String mensajeTexto;
        mensajeTexto = reader.readLine();
        return mensajeTexto;
    }
    
    public String realizarRetiroPedirMonto(String cuenta) throws IOException{
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String monto;
        System.out.println("Por favor ingrese el monto a retirar: ");
        while(true){
            while(ValidarTipoDeDato.validarEsEntero(monto = reader.readLine()) != true){
                System.out.println("Por favor ingrese un monto valido sin decimales: ");
            }
            
            if(Validar.existeSaldoSuficiente(Integer.parseInt(monto), cuenta)){
                break;
            }
            else{
                System.out.println("Saldo insuficiente, por favor ingrese un monto valido sin decimales: ");
            }
        }

        return monto;
    }
    public String realizarRetiroPedirMontoDolares(String cuenta) throws IOException{
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String monto;
        System.out.println("Por favor ingrese el monto a retirar: ");
        while(true){
            while(ValidarTipoDeDato.validarEsEntero(monto = reader.readLine()) != true){
                System.out.println("Por favor ingrese un monto valido sin decimales: ");
            }
            
            if(Validar.existeSaldoSuficienteEnTipoCambio(Integer.parseInt(monto), cuenta)){
                break;
            }
            else{
                System.out.println("Saldo insuficiente, por favor ingrese un monto valido sin decimales: ");
            }
        }

        return monto;
    }
    public void retiroError(String mensaje){
        System.out.println(mensaje);
    } 
    
    public void retiroRealizado(String mensaje){
        System.out.println(mensaje);
    }
    
}
