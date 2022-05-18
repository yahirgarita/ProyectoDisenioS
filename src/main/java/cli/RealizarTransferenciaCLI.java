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
public class RealizarTransferenciaCLI{
    /**
     * Método para solicitar cuenta origen de la transferencia.
     * @return cuenta dada por el usuario.
     * @throws IOException 
     */
    public String realizarTranferenciaPedirCuentaOrigen() throws IOException{     
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String cuenta;       
        System.out.println("Por favor ingrese el numero de cuenta: ");
        while(Validar.existeCuenta(cuenta = reader.readLine()) != true){
            System.out.println("Por favor ingrese un numero de cuenta existente: ");
        }      
        return cuenta;
    }   
    
    /**
     * Método para pedir el PIN de la cuenta origen.
     * @return PIN dado por el usuario.
     * @throws IOException 
     */
    public String realizarTransferenciaPedirPin() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Por favor ingrese el numero de PIN de la cuenta: ");
        String pin;
        pin = reader.readLine();
        return pin;
    }
    
    /**
     * Método para informar el envío de palabra por SMS.
     * @return palabra enviada por SMS.
     * @throws IOException 
     */
    public String realizarTransferenciaPedirSMS() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Estimado usuario se ha enviado una palabra por mensaje de texto, por favor revise sus mensajes y proceda a digitar la palabra enviada: ");
        String mensajeTexto;
        mensajeTexto = reader.readLine();
        return mensajeTexto;
    }
    
    /**
     * Método para pedir monto de la transferencia.
     * @param cuenta cuenta origen.
     * @return monto de la transferencia.
     * @throws IOException 
     */
    public String realizarTransferenciaPedirMonto(String cuenta) throws IOException{    
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String monto;
        System.out.println("Por favor ingrese el monto por transferir: ");
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
     * Método para pedir cuenta destino de la transferencia.
     * @return cuenta dada por el usuario.
     * @throws IOException 
     */
    public String realizarTranferenciaPedirCuentaDestino() throws IOException{      
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String cuenta;     
        System.out.println("Por favor ingrese el numero de cuenta a transferir: ");
        while(Validar.existeCuenta(cuenta = reader.readLine()) != true){
            System.out.println("Por favor ingrese un numero de cuenta existente: ");
        }       
        return cuenta;
    }  
    
    /**
     * Método para imprimir mensaje de error.
     * @param mensaje mensaje de error.
     */
    public void transferenciaError(String mensaje){
        System.out.println(mensaje);
    }
    
    /**
     * Método para imprimir mensaje de confirmación.
     * @param mensaje mensaje de confirmación.
     */
    public void transferenciaRealizado(String mensaje){
        System.out.println(mensaje);
    }   
}
