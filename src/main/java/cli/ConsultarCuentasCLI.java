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
public class ConsultarCuentasCLI {
    /**
     * Método para listar las cuentas por datos personales.
     * @param numeroCuenta  número de cuenta bancaria.
     * @param estatusCuenta   estatus en el que se encuentra la cuenta bancaria.
     * @param saldo                 saldo actual de la cuenta bancaria.
     * @param identificacion   cédula de identificación del dueño de la cuenta.
     * @param nombre             nombre del dueño de la cuenta. 
     */ 
    public void listarCuentas(int numeroCuenta, String estatusCuenta, double saldo, int identificacion, String nombre){
        System.out.println("Infomación de la cuenta");
        System.out.println("Numero de cuenta: " + numeroCuenta);
        System.out.println("Estatus de la cuenta: " + estatusCuenta);
        System.out.println("Saldo: " + saldo);
        System.out.println("Identificacion del duenio: " + identificacion);
        System.out.println("Nombre del duenio " + nombre);
        System.out.println();
    }
    
    /**
     * Método para leer la opción del usuario al consultar una cuenta.
     * @return
     * @throws IOException 
     */
    public String consultarCuenta() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String opcion;
        System.out.println("Digite 1 si desea consultar una cuenta o digite cualquier otra tecla para salir");
        opcion = reader.readLine();
        return opcion;
    }
    
    /**
     * Método para leer la cuenta bancaria que ingresó el usuario por consultar.
     * @return los datos de una cuenta bancaria.
     * @throws IOException 
     */
    public String seleccionarCuenta() throws IOException{  
        String cuenta;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));        
        System.out.println("Por favor digite el numero de cuenta que desea consultar: ");
        while(Validar.existeCuenta(cuenta = reader.readLine())!= true){
            System.out.println("Por favor digite un numero de cuenta existente: ");
        }
        return cuenta;
    }
    
    /**
     * Método para imprimir en consola la información de una cuenta bancaria.
     * @param informacion información de la cuenta bancaria.
     */
    public void mostrarCuenta(String informacion){
        System.out.println("Informacion de la cuenta: ");
        System.out.println(informacion);
    }
}
