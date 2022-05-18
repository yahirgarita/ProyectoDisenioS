/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.ArrayList;
import logicadenegocios.*;

/**
 *
 * @author yahir
 */
public class Ordenamiento {
    public static Persona[] ordenarAscendentemente(Comparable[] pArreglo) {

        for (int i = 1; i < pArreglo.length; i ++) { 
            Comparable nuevo = pArreglo [i] ; 
            int j = i - 1; 
            while (j >= 0 && ! pArreglo [j].menorQue(nuevo)) { 
                pArreglo [j + 1] = pArreglo [j]; 
                j --; 
            } 
            pArreglo [j + 1] = nuevo; 
        }
        return (Persona[]) pArreglo;
    }

    public static CuentaBancaria[] ordenarDescendentemente(Comparable[] pArreglo) {
        for (int i = 1; i < pArreglo.length; i ++) { 
            Comparable nuevo = pArreglo [i] ; 
            int j = i - 1; 
            while (j >= 0 && pArreglo [j].menorQue(nuevo)) { 
                pArreglo [j + 1] = pArreglo [j]; 
                j --; 
            } 
            pArreglo [j + 1] = nuevo; 
        }
        return (CuentaBancaria[]) pArreglo;
    }

    public static void ordenarAscendentemente(ArrayList<Persona> personasEnBD) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}