package cli;

//Librerías importadas.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//Clases importadas.
import validaciones.Validar;

/**
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 * 
 * @version 1.0
 */
public class ConsultarClientesCLI {
    /**
     * 
     * @param primerApellido        Primer apellido del cliente.
     * @param segundoApellido     Segunda apellido del cliente.
     * @param nombre                     Nombre del cliente.
     * @param identificacion           Cédula de identificación del cliente.
     */
    public void listarClientes(String primerApellido, String segundoApellido, String nombre, int identificacion){
        System.out.println("Infomación del cliente");
        System.out.println("Primero Apellido: " + primerApellido);
        System.out.println("Segundo Apellido: " + segundoApellido);
        System.out.println("Nombre: " + nombre);
        System.out.println("Numero de identificacion: " + identificacion);
        System.out.println();
    }
    
    /**
     * Método para leer la opción de la consulta de un cliente.
     * @return opción que digite el usuario.
     * @throws IOException 
     */
    public String consultarCliente() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String opcion;
        System.out.println("Digite 1 si desea consultar un cliente o digite cualquier otra tecla para salir");
        opcion = reader.readLine();
        return opcion;
    }
    
    /**
     * Método para solicitar la cédula de un cliente para consultar en específico.
     * @return
     * @throws IOException 
     */
    public String selecionarCliente() throws IOException{      
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String codigo;      
        System.out.println("Por favor digite la identificacion del cliente que desea consultar: ");
        while(Validar.existeCliente(codigo = reader.readLine()) != true){
            System.out.println("Por favor digite la identificacion de un cliente que valido: ");
        }
        return codigo;
    }
}
