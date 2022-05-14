package logicadenegocios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import logicadenegocios.Persona;
import util.Encriptar;
import validaciones.Validar;
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
    
    public CuentaBancaria(int pNumCuenta, LocalDate pFechaCreacion, double pSaldo, String pPin, String pEstatus){
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

    public void setEstatus(String pEstatus) {
        this.estatus = pEstatus;
    }
    public String getEstatus(){
        return estatus;
    }
    public Persona getDuenio(){
        return duenio;
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
    
    public String msgCreacion(){
        String msg = "Ha sido creada una nueva cuenta la base de datos, los datos que se almacenaron son;";
        msg += "\n Número de cuenta: " + this.getNumCuenta();
        msg += "\n Estatus de la cuenta: " + this.estatus;
        msg += "\n Saldo actual de la cuenta: " + this.saldo;
        msg += "\n ------------o------------o------------o------------o------------o------------ ";
        msg += "\n Nombre del dueño de la cuenta: " + this.duenio.getNombreCompleto();
        msg += "\n Número de teléfono asociado a la cuenta: " + this.duenio.getNumTelefonico();
        msg += "\n Dirección de correo electrónico asociado a la cuenta: " + this.duenio.getCorreoPersona();
        return msg;
    }
    public static int generarNumCuenta(){
        Random random = new Random();
        int numRandom = random.nextInt(400000 + 300000)+ 300000;
        if(Validar.numCuentasPresentesEnBD(Encriptar.cifrar(Integer.toString(numRandom)))){
            return numRandom;
        }else{
            return generarNumCuenta();
        }
    }
    
    public void afiliarDuenio(Persona pDueno){
        this.duenio = pDueno;
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
        info += "\n Fecha de creación: " + this.fechaCreacion + "\n";
        return info;
     
    }
            
}

