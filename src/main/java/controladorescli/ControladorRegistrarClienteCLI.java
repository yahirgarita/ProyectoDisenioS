/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorescli;

import cli.RegistrarClienteCLI;
import java.io.IOException;
import java.sql.SQLException;
import logicadenegocios.Persona;
import logicadeaccesoadatos.PersonaBD;
import validaciones.ValidarTipoDeDato;

/**
 *
 * @author Jimmy
 */
public class ControladorRegistrarClienteCLI {
    private Persona usuario;
    private RegistrarClienteCLI ventana;
    
    public ControladorRegistrarClienteCLI(RegistrarClienteCLI ventana){
        this.ventana = ventana;
    }
    
    public void registrarCliente() throws IOException, SQLException{
        String[] informacion = this.ventana.registrarCliente();
        usuario = new Persona(informacion[0],informacion[1],informacion[2],Integer.parseInt(informacion[3]),
                ValidarTipoDeDato.corregirFormatoFechaCLI(informacion[4]),Integer.parseInt(informacion[5]),informacion[6]);
        System.out.println(usuario.toString());
    }
    
    public static void main(String[] args) throws IOException, SQLException{
        RegistrarClienteCLI vista = new RegistrarClienteCLI();
        ControladorRegistrarClienteCLI nuevo = new ControladorRegistrarClienteCLI(vista);
        nuevo.registrarCliente();
    }
    
}
