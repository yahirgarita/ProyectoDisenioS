/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorescli;

import cli.RegistrarClienteCLI;
import java.io.IOException;
import logicadenegocios.Persona;

/**
 *
 * @author Jimmy
 */
public class ControladorRegistrarClienteCLI {
    private Persona usuario;
    private RegistrarClienteCLI ventana;
    
    public ControladorRegistrarClienteCLI(Persona usuario, RegistrarClienteCLI ventana){
        this.usuario = usuario;
        this.ventana = ventana;
    }
    
    public void registrarCliente() throws IOException{
        
        String primerApellido = "";
        String segundoApellido = "";
        String nombre = null;
        String identificacion = null;
        String fechaNacimiento = null;
        String numeroTelefonico = null;
        String correoElectronico = null;
        this.ventana.registrarCliente(primerApellido,segundoApellido,nombre,identificacion,fechaNacimiento,
                numeroTelefonico,correoElectronico);
       /* this.usuario = new Persona(primerApellido,segundoApellido,nombre,identificacion,fechaNacimiento,
                numeroTelefonico,correoElectronico);*/
        
    }
    
    
}
