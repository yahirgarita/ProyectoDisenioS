/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Jimmy
 */
public class ConsultarClientesCLI {
    
    public void listarClientes(String primerApellido, String segundoApellido, String nombre, int identificacion){
        System.out.println("Infomación del cliente");
        System.out.println("Primero Apellido: " + primerApellido);
        System.out.println("Segundo Apellido: " + segundoApellido);
        System.out.println("Nombre: " + nombre);
        System.out.println("Numero de identificacion: " + identificacion);
    }
    
    public String selecionarCliente() throws IOException{
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String codigo;
        
        System.out.println("Por favor digite el codigo del cliente que desea consultar: "); ;
        codigo = reader.readLine();
        return codigo;
    }
}
