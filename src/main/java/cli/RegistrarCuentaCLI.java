/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import validaciones.*;

/**
 *
 * @author Jimmy
 */
public class RegistrarCuentaCLI {
    
    public String[] registrarCuenta() throws IOException{
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        String codigo="";
        String pin;
        String montoInicial;
        
        System.out.println("Por favor ingresar la identifiacaion cliente: "); 
        while(Validar.existeCliente(codigo = reader.readLine()) != true){
            System.out.println("Por favor ingresar una identificacion de cliente valido: ");
        }
        
        System.out.println("Por favor ingrese un nuevo PIN para la cuenta: "); 
        while(ValidarTipoDeDato.validarFormatoPIN(pin = reader.readLine())!= true){
            System.out.println("Por favor ingresar un formato de PIN valido: ");
        }
        
        System.out.println("Por favor deposite un monto inicial sin decimales (Ejemplo: 2000) : "); 
        while(ValidarTipoDeDato.validarEsEntero(montoInicial = reader.readLine())!= true){
            System.out.println("Por favor ingresar monto inicial sin decimales valido: ");
        }
        
        String informacion[] = {codigo,pin,montoInicial};
        return informacion;
    }
    
    public void mostrarMensaje(String mensaje){
        System.out.println(mensaje);
    }
   
}
