package cli;

/**
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 * 
 * @version 1.0
 */
public class SeleccionarClienteCLI {
    /**
     * Método para imprimir la información de un cliente en lista de cuentas.
     * @param informacion información del dueño de la cuenta.
     */
    public void consultarInformacionCliente(String informacion){      
        System.out.println("Infomación del cliente");
        System.out.println(informacion);     
    }
    
    /**
     * Método para listar los las cuentas por número.
     * @param numeroCuenta número de cuenta bancaria.
     */
    public void listarCuentas(int numeroCuenta){
        System.out.println("Numero de cuenta: " + numeroCuenta);
    }
}
