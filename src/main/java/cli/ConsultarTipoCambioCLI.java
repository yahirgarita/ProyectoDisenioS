package cli;

/**
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 * 
 * @version 1.0
 */
public class ConsultarTipoCambioCLI {
    /**
     * Método para imprimir el tipo de cambio de venta del dólar.
     * @param precio precio del dólar.
     */
    public void ventaDolar(double precio){
        System.out.println("El precio de venta actual del dolar es de: "+ precio + " colones");
    }
    
    /**
     * Método para imprimir el tipo de cambio de compra del dólar.
     * @param precio precio del dólar.
     */
    public void compraDolar(double precio){
        System.out.println("El precio de compra actual del dolar es de: "+ precio + " colones");
    }
}
