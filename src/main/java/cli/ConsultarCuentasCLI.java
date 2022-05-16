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
public class ConsultarCuentasCLI {
    
    public void listarCuentas(int numeroCuenta, String estatusCuenta, double saldo, int identificacion, String nombre){

        System.out.println("Infomación de la cuenta");
        System.out.println("Numero de cuenta: " + numeroCuenta);
        System.out.println("Estatus de la cuenta: " + estatusCuenta);
        System.out.println("Saldo: " + saldo);
        System.out.println("Identificacion del duenio: " + identificacion);
        System.out.println("Nombre del duenio " + nombre);
        System.out.println();
    }
    
    public String consultarCuenta() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String opcion;
        System.out.println("Digite 1 si desea consultar una cuenta o digite cualquier otra tecla para salir");
        opcion = reader.readLine();
        return opcion;
    }
    
    public String seleccionarCuenta() throws IOException{
        
        String cuenta;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Por favor digite el numero de cuenta que desea consultar: "); ;
        while(Validar.existeCuenta(cuenta = reader.readLine())!= true){
            System.out.println("Por favor digite un numero de cuenta existente: "); ;
        }
        return cuenta;
    }
    
    public void mostrarCuenta(String informacion){
        System.out.println("Informacion de la cuenta: ");
        System.out.println(informacion);
    }
}
