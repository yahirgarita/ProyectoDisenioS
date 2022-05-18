package cli;

//Librerías importadas.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//Clases importadas.
import validaciones.*;

/**
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 * 
 * @version 1.0
 */
public class RegistrarCuentaCLI {
    /**
     * Método para realizar toda la operación del registro de cuenta.
     * @return información de la cuenta.
     * @throws IOException 
     */
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
    
    /**
     * Método para mostrar mensaje de confirmación.
     * @param mensaje mensaje de confirmación.
     */
    public void mostrarMensaje(String mensaje){
        System.out.println(mensaje);
    }
}
