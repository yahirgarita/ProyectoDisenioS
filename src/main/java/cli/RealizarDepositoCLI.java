package cli;

//Liobrerías importadas.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//Clases importadas.
import validaciones.Validar;
import validaciones.ValidarTipoDeDato;

/**
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 * 
 * @version 1.0
 */
public class RealizarDepositoCLI {
    /**
     * Método para pedir la cuenta por realizar depósito.
     * @return cuenta dada por el usuario.
     * @throws IOException 
     */
    public String realizarDepositoPedirCuenta() throws IOException{      
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String cuenta;       
        System.out.println("Por favor ingrese el numero de cuenta: ");
        while(Validar.existeCuenta(cuenta = reader.readLine()) != true){
            System.out.println("Por favor ingrese un numero de cuenta existente: ");
        }       
        return cuenta;
    }   
    
    /**
     * Método para pedir el monto por realizar en el depósito.
     * @return monto dado por el usuario.
     * @throws IOException 
     */
    public String realizarDepositoPedirMonto() throws IOException{     
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String monto;
        System.out.println("Por favor ingrese el monto a depositar: ");
        while(ValidarTipoDeDato.validarEsEntero(monto = reader.readLine()) != true){
            System.out.println("Por favor ingrese un monto valido sin decimales: ");
        }
        return monto;
    }
    
    /**
     * Método para confirmar la realización del depósito.
     * @param mensaje mensaje de confirmación.
     */
    public void depositoRealizado(String mensaje){
        System.out.println(mensaje);
    }   
}
