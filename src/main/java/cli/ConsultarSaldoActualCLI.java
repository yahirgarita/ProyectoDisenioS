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
public class ConsultarSaldoActualCLI {
    
    public String consultarSaldoActual() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String cuenta;
        String pin;
        System.out.println("Por favor ingrese el numero de cuenta: ");
        while(Validar.existeCuenta(cuenta = reader.readLine()) != true){
            System.out.println("Por favor ingrese un numero de cuenta existente: ");
        }
        System.out.println("Por favor ingrese el pin de la cuenta: ");
        while(Validar.validarPin(cuenta,pin = reader.readLine()) != true){
            System.out.println("Por favor ingrese el pin correcto: ");
        }
        
        return cuenta;
    }
    
    public void mostrarSaldoActualColones(double saldo){
        System.out.println("Estimado usuario el saldo actual de su cuenta es "+ saldo +" colones.");
    }
    
    public void mostrarSaldoActualDolares(double saldo, double precioDolar){
        System.out.println("Estimado usuario el saldo actual de su cuenta es "+ saldo +" dolares.");
        System.out.println("Para esta conversión se utilizó el tipo de cambio del dólar, precio de compra.");
        System.out.println("[Según el BCCR, el tipo de cambio de compra del dólar de hoy es: " + precioDolar +"]");
    }
    
    public void mosstrarMensajeCuentaInactiva(){
        System.out.println("Estimado usuario esta cuenta see encueentra ina ");
    }
}

