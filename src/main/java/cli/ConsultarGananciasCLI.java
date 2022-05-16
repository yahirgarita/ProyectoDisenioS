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
public class ConsultarGananciasCLI {
    
    public void consultarGanancias(double montoTotal,double montoDepositos,double montoRetiros){
        System.out.printf("Las ganancias TOTALES por conceptos de comision es de %.2f colones\n",montoTotal);
        System.out.printf("Las ganancias Totales por conceptos de comision de depósitos es de %.2f colones\n",montoDepositos);
        System.out.printf("Las ganancias TOTALES por conceptos de comision de retiros es de %.2f colones\n",montoRetiros);

    }
    
    public String consultarGananciasPedirCuenta() throws IOException{
        String cuenta;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Por favor ingrese el numero de cuenta: ");
        while(Validar.existeCuenta(cuenta = reader.readLine()) != true){
            System.out.println("Por favor ingrese un numero de cuenta existente: ");
        }
        return cuenta;
    }
    
    public void consultarGananciasPorCuenta(double montoTotal,double montoDepositos,double montoRetiros){
        System.out.printf("Las ganancias con respecto a la cuenta por conceptos de comision es de %.2f colones\n",montoTotal);
        System.out.printf("Las ganancias con respecto a la cuenta por conceptos de comision de depósitos es de %.2f colones\n",montoDepositos);
        System.out.printf("Las ganancias con respecto a la cuenta por conceptos de comision de retiros es de %.2f colones\n",montoRetiros);
    }
  
}
