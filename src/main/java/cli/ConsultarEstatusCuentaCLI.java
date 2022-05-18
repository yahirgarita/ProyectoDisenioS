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
public class ConsultarEstatusCuentaCLI {
   /**
    * Método para solicitar al usuario la cuenta por ver el estatus.
    * @return el número de cuenta dado por el usuario.
    * @throws IOException 
    */
    public String verEstatusPedirCuenta() throws IOException{      
        String cuenta;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Por favor ingrese el numero de cuenta: ");
        while(Validar.existeCuenta(cuenta = reader.readLine()) != true){
            System.out.println("Por favor ingrese un numero de cuenta existente: ");
        }
        return cuenta;
    }
    
    /**
     * Método para imprimir el estatus de la cuenta por consola.
     * @param mensaje mensaje del estatus.
     */
    public void verEstatusMostrar(String mensaje){
        System.out.println(mensaje);
    }
}
