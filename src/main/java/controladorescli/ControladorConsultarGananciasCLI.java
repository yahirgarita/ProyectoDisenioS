/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorescli;

import cli.ConsultarGananciasCLI;
import java.io.IOException;
import logicadeaccesoadatos.CuentaBD;
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
        
        double gananciasDepositos = CuentaBD.totalComisionesBancoDepositos();
        double gananciasRetiros = CuentaBD.totalComisionesBancoRetiros();
        double gananciasTotales = gananciasDepositos + gananciasRetiros;

        this.vista.consultarGanancias(gananciasTotales,gananciasDepositos,gananciasRetiros);
    }
    
     public void consultarGananciasPorCuenta() throws IOException{
        String cuenta = Encriptar.cifrar(this.vista.consultarGananciasPedirCuenta());
        double gananciasDepositos = CuentaBD.totalComisionesCuentaDepositos(cuenta);
        double gananciasRetiros = CuentaBD.totalComisionesCuentaRetiros(cuenta);
        double gananciasTotales = gananciasDepositos + gananciasRetiros;

        this.vista.consultarGanancias(gananciasTotales,gananciasDepositos,gananciasRetiros);
    }
}
