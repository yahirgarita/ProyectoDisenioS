/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorescli;

import cli.ConsultarGananciasCLI;
import java.io.IOException;
import logicadeaccesoadatos.OperacionBD;
import util.Encriptar;

/**
 *
 * @author Jimmy
 */
public class ControladorConsultarGananciasCLI {
    
    private ConsultarGananciasCLI vista;
    
    public ControladorConsultarGananciasCLI(ConsultarGananciasCLI vista){
        this.vista = vista;
    }
    
    public void consultarGananciasTotales(){
        
        double gananciasDepositos = OperacionBD.obtenerComisionOperacionesDepositos();
        double gananciasRetiros = OperacionBD.obtenerComisionOperacionesRetiros();
        double gananciasTotales = gananciasDepositos + gananciasRetiros;

        this.vista.consultarGanancias(gananciasTotales,gananciasDepositos,gananciasRetiros);
    }
    
     public void consultarGananciasPorCuenta() throws IOException{
        String cuenta = Encriptar.cifrar(this.vista.consultarGananciasPedirCuenta());
        double gananciasDepositos = OperacionBD.obtenerComisionOperacionesDepositosPorCuenta(cuenta);
        double gananciasRetiros = OperacionBD.obtenerComisionOperacionesRetirosPorCuenta(cuenta);
        double gananciasTotales = gananciasDepositos + gananciasRetiros;

        this.vista.consultarGanancias(gananciasTotales,gananciasDepositos,gananciasRetiros);
    }
}
