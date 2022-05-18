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
public class ConsultarSaldoActualCLI {
    /**
     * Método para solicitar al usuario que ingrese la cuenta por consultar.
     * @return cuenta dada por el usuario.
     * @throws IOException 
     */
    public String consultarSaldoActual() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String cuenta;
        System.out.println("Por favor ingrese el numero de cuenta: ");
        while(Validar.existeCuenta(cuenta = reader.readLine()) != true){
            System.out.println("Por favor ingrese un numero de cuenta existente: ");
        }
        return cuenta;
    }
    
    /**
     * Método para solicitar al usuario que ingrese el PIN de la cuenta por consultar.
     * @return PIN dado por el usuario.
     * @throws IOException 
     */
    public String consultarSaldoActualPedirPin() throws IOException{   
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Por favor ingrese el numero de PIN de la cuenta: ");
        String pin;
        pin = reader.readLine();
        return pin;     
    }
    
    /**
     * Método para imprimir el saldo de la cuenta.
     * @param saldo saldo actual de la cuenta.
     */
    public void mostrarSaldoActualColones(double saldo){
        System.out.println("Estimado usuario el saldo actual de su cuenta es "+ saldo +" colones.");
    }
    
    /**
     * Método para imprimir el saldo en dólares de la cuenta por consola.
     * @param saldo             saldo actual de la cuenta en dólares.
     * @param precioDolar   tipo de cambio en el que se encuentra el dolar.
     */
    public void mostrarSaldoActualDolares(double saldo, double precioDolar){
        System.out.printf("Estimado usuario el saldo actual de su cuenta es %.2f\n dolares.", saldo);
        System.out.println("Para esta conversión se utilizó el tipo de cambio del dólar, precio de compra.");
        System.out.println("[Según el BCCR, el tipo de cambio de compra del dólar de hoy es: " + precioDolar +"]");
    }
    
    /**
     * Método para imprimir el estado de inactividad por consola.
     */
    public void cuentaInactiva(){
        System.out.println("Estimado usuario esta cuenta esta inactiva.");
    }
}

