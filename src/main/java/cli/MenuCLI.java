package cli;

//Librerías importadas.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//Clases importadas.
import validaciones.ValidarTipoDeDato;

/**
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 * 
 * @version 1.0
 */
public class MenuCLI {
    /**
     * Método para imprimir por consola todo el menú.
     * @return opción dada por el usuario.
     * @throws IOException 
     */
    public int mostrarMenu() throws IOException{       
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String opcion;
        System.out.println("Bienvenido al menu del banco");
        System.out.println("Por favor elija una opcion (1-20)");
        System.out.println("1. Registrar Cliente");
        System.out.println("2. Crear cuenta bancaria");
        System.out.println("3. Listar Clientes");
        System.out.println("4. Listar Cuentas");
        System.out.println("5. Cambiar PIN");
        System.out.println("6. Realizar Deposito en Colones");
        System.out.println("7. Realizar Deposito en Dolares");
        System.out.println("8. Realizar Retiro en Colones");
        System.out.println("9. Realizar Retiro en Dolares");
        System.out.println("10. Realizar Transferencia");
        System.out.println("11. Consultar Tipo de cambio: Precio compra Dolar");
        System.out.println("12. Consultar Tipo de cambio: Precio venta Dolar");
        System.out.println("13. Consultar saldo actual");
        System.out.println("14. Consultar saldo actual en dolares");
        System.out.println("15. Consultar estado de cuenta");
        System.out.println("16. Consultar estado de cuenta en dolares");
        System.out.println("17. Consultar estatus de una cuenta");
        System.out.println("18. Consultar ganancias del banco TOTALIZADO");
        System.out.println("19. Consultar ganancias del banco de una cuenta especifico");
        System.out.println("20. Salir");
        while(!ValidarTipoDeDato.validarEsEntero(opcion = reader.readLine())){
            System.out.println("Por favor elija una opcion correcta (1-20)");
        }
        return Integer.parseInt(opcion);
    }   
}
