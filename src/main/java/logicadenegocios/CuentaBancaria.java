package logicadenegocios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import logicadenegocios.Persona;

/**
 *
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 * 
 * @version 1.0
 */
public class CuentaBancaria {
    private int numCuenta;
    private double saldo;
    private String estatus; 
    private String pin;
    private Persona duenio;
    private LocalDate fechaCreacion;
    private ArrayList<Operacion> registros;
    
    /**
     * Constructor de la clase CuentaBancaria
     *
     * @param pNumCuenta        El numero de cuenta que se genera
     * @param pSaldo            El saldo inicial de la cuenta
     * @param pPin              El pin de la cuenta encriptado
     * @param pFechaCreacion    La fecha generada automaticamente por el sistema
    */
    
    public CuentaBancaria(int pNumCuenta, double pSaldo, String pPin, LocalDate pFechaCreacion){
        this.numCuenta = pNumCuenta;
        this.saldo = pSaldo;
        this.estatus = "Activo";
        this.pin = pPin;
        this.duenio = null;
        this.fechaCreacion = pFechaCreacion;
        this.registros = new ArrayList<>();
        }

    public double getSaldo() {
        return saldo;
    }

    public void getEstatus(String pEstatus) {
        this.estatus = pEstatus;
    }

    public String getPin() {
        return pin;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }
    
    public int getNumCuenta(){
        return numCuenta; 
    }
    
    public static int generarNumCuenta(){
        Random random = new Random();
        int numRandom = random.nextInt(400000 + 300000)+ 300000;
        
    }
    
    /**
     * Método para conocer la información de un objeto de tipo CuentaBancaria.
     * 
     * @return  la infromación que contiene el objeto
     */
    
    public String toString(){
        String info = "";
        info += "\n Numero de la cuenta: " + this.numCuenta;
        info += "\n Saldo actual: " + this.saldo;
        info += "\n Estatus: " + this.estatus;
        info += "\n Dueño:" + this.duenio.getNombreCompleto() + "\n";
        return info;
     
    }
            
}

