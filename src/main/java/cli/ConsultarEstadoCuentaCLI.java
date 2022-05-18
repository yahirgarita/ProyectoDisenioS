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
public class ConsultarEstadoCuentaCLI{   
    /**
     * Método para recibir la cuenta bancaria ingresada por el cliente.
     * @return cuenta bancaria por ver el estado.
     * @throws IOException 
     */
    public String consultarEstadoCuentaPedirCuenta() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String cuenta;       
        System.out.println("Por favor ingrese el numero de cuenta: ");
        while(Validar.existeCuenta(cuenta = reader.readLine()) != true){
            System.out.println("Por favor ingrese un numero de cuenta existente: ");
        }       
        return cuenta;
    }
    /**
     * Método para solicitar el PIN de la cuenta bancaria por ver el estado.
     * @return
     * @throws IOException 
     */
    public String consultarEstadoCuentaPedirPin() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Por favor ingrese el numero de PIN de la cuenta: ");
        String pin;
        pin = reader.readLine();
        return pin;
    }
    
    /**
     * Método para la impresión del formato del estado de cuenta por consola.
     */
    public void mostrarEstadoCuentaTitulo(){  
        System.out.println("FECHA \t\t TIPO \t\t MONTO \t\t CARGO");    
    }
    
    /**
     * Método para imprimir los datos del estado de cuenta.
     * @param fecha     fecha en la que realizó la operación.
     * @param tipo        tipo de operación que se realizó.
     * @param monto    monto restado o aumentado según la operación.
     * @param cargo    cargo cobrado por realizar la operación en caso de que hubiese.
     * @throws IOException 
     */
    public void mostrarEstadoCuenta(String fecha, String tipo, double monto, String cargo) throws IOException{       
        System.out.println(fecha + " \t " + tipo + " \t " + monto + " \t\t " + cargo);     
    }
    
    /**
     * Método para imprimir por consola el estado de inactividad.
     */
    public void cuentaInactiva(){
        System.out.println("Estimado usuario esta cuenta esta inactiva.");
    }
}
