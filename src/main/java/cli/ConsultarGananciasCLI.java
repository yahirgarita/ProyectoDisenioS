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
public class ConsultarGananciasCLI {
    /**
     * Método para imprimir las ganancias universales del banco.
     * @param montoTotal            monto total acumulado.
     * @param montoDepositos    monto total de comisión acumulado solo por depósitos.
     * @param montoRetiros         monto total de comisión acumulado solo por retiros.
     */
    public void consultarGanancias(double montoTotal,double montoDepositos,double montoRetiros){
        System.out.printf("Las ganancias TOTALES por conceptos de comision es de %.2f colones\n",montoTotal);
        System.out.printf("Las ganancias Totales por conceptos de comision de depósitos es de %.2f colones\n",montoDepositos);
        System.out.printf("Las ganancias TOTALES por conceptos de comision de retiros es de %.2f colones\n",montoRetiros);
    }
    
    /**
     * Método para solicitar al usuario el número de cuenta por consultar.
     * @return cuenta dada por el usuario
     * @throws IOException 
     */
    public String consultarGananciasPedirCuenta() throws IOException{
        String cuenta;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Por favor ingrese el numero de cuenta: ");
        while(Validar.existeCuenta(cuenta = reader.readLine()) != true){
            System.out.println("Por favor ingrese un numero de cuenta existente: ");
        }
        return cuenta;
    }
    
    /**
     * Método para imprimir las ganancias acumuladas del banco por cuenta en específico.
     * @param montoTotal            monto total acumulado.
     * @param montoDepositos   monto total de comisión acumulado solo por depósitos.
     * @param montoRetiros         monto total de comisión acumulado solo por retiros.
     */
    public void consultarGananciasPorCuenta(double montoTotal,double montoDepositos,double montoRetiros){
        System.out.printf("Las ganancias con respecto a la cuenta por conceptos de comision es de %.2f colones\n",montoTotal);
        System.out.printf("Las ganancias con respecto a la cuenta por conceptos de comision de depósitos es de %.2f colones\n",montoDepositos);
        System.out.printf("Las ganancias con respecto a la cuenta por conceptos de comision de retiros es de %.2f colones\n",montoRetiros);
    } 
}
