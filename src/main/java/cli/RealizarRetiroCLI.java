package cli;

//Librerías importadas.
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
public class RealizarRetiroCLI {
    /**
     * Método para pedir el número de la cuenta por realizar el retiro.
     * @return cuenta dada por el usuario.
     * @throws IOException 
     */
    public String realizarRetiroPedirCuenta() throws IOException{      
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String cuenta;       
        System.out.println("Por favor ingrese el numero de cuenta: ");
        while(Validar.existeCuenta(cuenta = reader.readLine()) != true){
            System.out.println("Por favor ingrese un numero de cuenta existente: ");
        }      
        return cuenta;
    }
    
    /**
     * Método para pedir el PIN de la cuenta por realizar el retiro.
     * @return PIN dado por el usuario.
     * @throws IOException 
     */
    public String realizarRetiroPedirPin() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Por favor ingrese el numero de PIN de la cuenta: ");
        String pin;
        pin = reader.readLine();
        return pin;
    }
    
    /**
     * Método para imprimir el mensaje del envió por SMS.
     * @return palabra enviada por SMS.
     * @throws IOException 
     */
    public String realizarRetiroPedirSMS() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Estimado usuario se ha enviado una palabra por mensaje de texto, por favor revise sus mensajes y proceda a digitar la palabra enviada: ");
        String mensajeTexto;
        mensajeTexto = reader.readLine();
        return mensajeTexto;
    }
    
    /**
     * Método para solicitar el monto a retirar.
     * @param cuenta cuenta bancaria.
     * @return monto por realizar retiro.
     * @throws IOException 
     */
    public String realizarRetiroPedirMonto(String cuenta) throws IOException{       
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String monto;
        System.out.println("Por favor ingrese el monto a retirar: ");
        while(true){
            while(ValidarTipoDeDato.validarEsEntero(monto = reader.readLine()) != true){
                System.out.println("Por favor ingrese un monto valido sin decimales: ");
            }          
            if(Validar.existeSaldoSuficiente(Integer.parseInt(monto), cuenta)){
                break;
            }
            else{
                System.out.println("Saldo insuficiente, por favor ingrese un monto valido sin decimales: ");
            }
        }

        return monto;
    }
    
    /**
     * Método para realizar el retiro por dólares.
     * @param cuenta número de cuenta por retirar.
     * @return monto en dólares del retiro.
     * @throws IOException 
     */
    public String realizarRetiroPedirMontoDolares(String cuenta) throws IOException{      
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String monto;
        System.out.println("Por favor ingrese el monto a retirar: ");
        while(true){
            while(ValidarTipoDeDato.validarEsEntero(monto = reader.readLine()) != true){
                System.out.println("Por favor ingrese un monto valido sin decimales: ");
            }          
            if(Validar.existeSaldoSuficienteEnTipoCambio(Integer.parseInt(monto), cuenta)){
                break;
            }
            else{
                System.out.println("Saldo insuficiente, por favor ingrese un monto valido sin decimales: ");
            }
        }
        return monto;
    }
    
    /**
     * Método para imprimir mensaje de error en el proceso.
     * @param mensaje mensaje de error.
     */
    public void retiroError(String mensaje){
        System.out.println(mensaje);
    } 
    
    /**
     * Método para imprimir mensaje de confirmación
     * @param mensaje mensaje de confirmación.
     */
    public void retiroRealizado(String mensaje){
        System.out.println(mensaje);
    }   
}
