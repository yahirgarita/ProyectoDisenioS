package cli;

//Librerías importadas.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//Clases importadas
import validaciones.Validar;
import validaciones.ValidarTipoDeDato;

/**
 * Clase para cambiar el PIN de la vista CLI.
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 * 
 * @version 1.0
 */
public class CambiarPinCLI {
    /**
     * Método para pedir el número de cuenta al cambiar de pin como primer paso.
     * @return cuenta número de cuenta bancaria.
     * @throws IOException 
     */
    public String cambiarPinPedirCuenta() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String cuenta;
        System.out.println("Por favor ingrese el numero de cuenta: ");
        while(Validar.existeCuenta(cuenta = reader.readLine()) != true){
            System.out.println("Por favor ingrese un numero de cuenta existente: ");
        }
        return cuenta;
    }
    
    /**
     * Método para solicitar el PIN actual de la cuenta.
     * @return cuenta pin único de la cuenta bancaria.
     * @throws IOException 
     */
    public String cambiarPinPedirPinActual() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Por favor ingrese el numero de PIN actual: ");
        String pin;
        pin = reader.readLine();
        return pin;
    }
    
    /**
     * Método para solicitarle al usuario que ingrese en nuevo PIN para cambiar.
     * @return nuevo PIN para cambiar.
     * @throws IOException 
     */
    public String cambiarPinPedirPinNuevo() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Por favor ingrese el numero de PIN nuevo con un formato correcto: ");
        String pin;
        while(ValidarTipoDeDato.validarFormatoPIN(pin = reader.readLine())!= true){
            System.out.println("Por favor ingresar un formato de PIN valido: ");
        }
        return pin;    
    }
    
    /**
     * Método para confirmar el cambio de PIN por medio de un mensaje impreso.
     * @param numeroCuenta número de cuenta bancaria del usuario.
     * @throws IOException 
     */
    public void cambiarPinCompletado(String numeroCuenta) throws IOException{
        System.out.println("Estimado usuario, se ha cambiado satisfactoriamente el PIN de su cuenta: " + numeroCuenta);
    } 
    
    /**
     * Método para confirmar el estado de inactividad de la cuenta por medio de un mensaje impreso.
     */
    public void cuentaInactiva(){
        System.out.println("Estimado usuario esta cuenta esta inactiva.");
    }
}
