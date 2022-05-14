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
public class MenuCLI {
    
    public int mostrarMenu() throws IOException{
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Bienvenido al menu del banco");
        System.out.println("Por favor elija una opcion");
        System.out.println("1. Registrar Cliente");
        System.out.println("2. Crear cuenta bancaria");
        System.out.println("3. Listar Clientes");
        System.out.println("4. Listar Cuentas");
        System.out.println("5. Cambiar");
        System.out.println("6. Salir");
        
        int opcion = Integer.parseInt(reader.readLine());
        
        return opcion;
    }
    
}
