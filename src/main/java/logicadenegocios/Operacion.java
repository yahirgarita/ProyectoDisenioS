/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicadenegocios;

import java.time.LocalDate;

/**
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 * 
 * @version 1.0
 */
public class Operacion {
    
    private String tipo;
    private String moneda;
    
    private boolean cargo;
    private double monto;
    
    private LocalDate fechaOperacion;
            
    /**
     * Constructor de la clase Opercacion
     * 
     * @param pTipo                  El tipo de operación que se realiza
     * @param pMoneda               El tipo de moneda utilizada
     * @param pCargo                En caso que se realice un cargo 
     * @param pMonto                Monto en caso de que aplique
     * @param pFechaOperacion       Fecha que genera automaticamente el sistema
     */
    public Operacion(String pTipo, String pMoneda, boolean pCargo, double pMonto, LocalDate pFechaOperacion){
        this.tipo = pTipo;
        this.moneda = pMoneda;
        this.cargo = pCargo;
        this.monto = pMonto;
        this.fechaOperacion = pFechaOperacion;
    }        
    
    /**
     * Metodos accesores
     *  
     */
    public String getTipo() {
        return tipo;
    }

    public String getMoneda() {
        return moneda;
    }

    public boolean isCargo() {
        return cargo;
    }

    public double getMonto() {
        return monto;
    }

    public LocalDate getFechaOperacion() {
        return fechaOperacion;
    }
    
    public String getCargo(){
        if(this.cargo){
            return "Si";
        }
        else{
            return "No";
        }
    }
    
    /**
     * Método para conocer la información del objeto de tipo generación
     */
            
}
