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
public class ConsultarClientesCLI {
    
    public void listarClientes(String primerApellido, String segundoApellido, String nombre, int identificacion){
        System.out.println("Infomación del cliente");
        System.out.println("Primero Apellido: " + primerApellido);
        System.out.println("Segundo Apellido: " + segundoApellido);
        System.out.println("Nombre: " + nombre);
        System.out.println("Numero de identificacion: " + identificacion);
        System.out.println();
    }
    
    public String consultarCliente() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String opcion;
        System.out.println("Digite 1 si desea consultar un cliente o digite cualquier otra tecla para salir");
        opcion = reader.readLine();
        return opcion;
    }
    
    public String selecionarCliente() throws IOException{
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String codigo;
        
        System.out.println("Por favor digite la identificacion del cliente que desea consultar: "); ;
        while(Validar.existeCliente(codigo = reader.readLine()) != true){
            System.out.println("Por favor digite la identificacion de un cliente que valido: "); ;
        }
        return codigo;
    }
}
