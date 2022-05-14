/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorescli;

import cli.MenuCLI;
import cli.RegistrarClienteCLI;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Jimmy
 */
public class ControladorMenuCLI {
    
    MenuCLI menu;
    
    public ControladorMenuCLI(MenuCLI menu){
        this.menu = menu;
    }
    public void listarMenu() throws IOException, SQLException{
       
        int opcion;
        while((opcion = menu.mostrarMenu()) != 6 ){
           switch(opcion){
               case 1:
                   new ControladorRegistrarClienteCLI(new RegistrarClienteCLI()).registrarCliente();
               case 6:
                   break;
           }
        }
        
      
    }
    
    public static void main(String[] args) throws IOException, SQLException{
        MenuCLI vista = new MenuCLI();
        ControladorMenuCLI nuevo =  new ControladorMenuCLI(vista);
        nuevo.listarMenu();
    }
        
   
}
