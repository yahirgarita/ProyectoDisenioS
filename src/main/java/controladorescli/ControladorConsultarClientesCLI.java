/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorescli;

import cli.ConsultarClientesCLI;
import java.util.ArrayList;
import logicadenegocios.Persona;

/**
 *
 * @author Jimmy
 */
public class ControladorConsultarClientesCLI {
    
    private ConsultarClientesCLI vista;
    
    public ControladorConsultarClientesCLI(){
        vista = new ConsultarClientesCLI();
    }
    public void listarClientes(ArrayList<Persona> lista){
        for(int i = 0;i < lista.size(); i++){
            vista.listarClientes(lista.get(i).getPrimerApellido(), lista.get(i).getSegundoApellido(), 
                    lista.get(i).getNombre(), lista.get(i).getIdPersona());
        }
    }
   
}
