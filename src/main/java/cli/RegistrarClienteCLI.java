package cli;

//Librerías importadas.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//Clases importadas.
import validaciones.ValidarTipoDeDato;

/**
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 * 
 * @version 1.0
 */
public class RegistrarClienteCLI {
    /**
     * Método para realizar la operación completa del registro de un cliente.
     * @return información del cliente por registrar.
     * @throws IOException 
     */
    public String[] registrarCliente() throws IOException{    
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String primerApellido;
        String segundoApellido;
        String nombre;
        String identificacion;
        String fechaNacimiento;
        String numeroTelefonico ;
        String correoElectronico;
        System.out.println("Sistema de Registro de Cliente");     
        System.out.println("Por favor ingresar su primer apellido: ");
        primerApellido = reader.readLine();      
        System.out.println("hola " + primerApellido);
        System.out.println("Por favor ingresar su segundo apellido: ");
        segundoApellido = reader.readLine();        
        System.out.println("Por favor ingresar su nombre: ");
        nombre = reader.readLine();        
        System.out.println("Por favor ingresar el numero de identificacion: "); 
        //identificacion = reader.readLine();
        while(ValidarTipoDeDato.validarEsEntero(identificacion = reader.readLine())!= true){
            System.out.println("Por favor ingresar el numero de identificacion valido: ");
        }        
        System.out.println("Por favor ingresar la fecha de nacimiento (DD/MM/AAAA): ");
        //fechaNacimiento = reader.readLine();
        while(ValidarTipoDeDato.validarFormatoFechaCLI(fechaNacimiento = reader.readLine())!= true){
            System.out.println("Por favor ingresar la fecha de nacimiento con formato correcto (DD/MM/AAAA): ");
        }      
        System.out.println("Por favor ingresar su numero telefonico: ");
        while(ValidarTipoDeDato.validarEsEntero(numeroTelefonico = reader.readLine()) != true){
            System.out.println("Por favor ingresar un numero telefonico valido: ");
        }      
        System.out.println("Por favor ingresar el correo electronico: ");
        while(ValidarTipoDeDato.validarFormatoCorreo(correoElectronico = reader.readLine()) != true){
            System.out.println("Por favor ingresar un numero telefonico valido: ");
        }       
        String[] informacion ={primerApellido,segundoApellido,nombre,identificacion,fechaNacimiento,numeroTelefonico,correoElectronico};
        return informacion;
    }
}
