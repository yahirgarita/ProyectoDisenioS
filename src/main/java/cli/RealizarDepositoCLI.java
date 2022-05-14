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
public class RealizarDepositoCLI {
    
    public String[] realizarDepositoColones() throws IOException{
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String cuenta;
        String monto;
        
        System.out.println("Por favor ingrese el numero de cuenta: ");
        while(Validar.existeCuenta(cuenta = reader.readLine()) != true){
            System.out.println("Por favor ingrese un numero de cuenta existente: ");
        }
        System.out.println("Por favor ingrese el monto a depositar: ");
        while(ValidarTipoDeDato.validarEsEntero(monto = reader.readLine()) != true){
            System.out.println("Por favor ingrese un monto valido sin decimales: ");
        }
        String[] informacion = {cuenta,monto};
        return informacion;
    }   
}
