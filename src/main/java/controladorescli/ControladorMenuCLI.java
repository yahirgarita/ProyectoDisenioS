/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorescli;

import cli.MenuCLI;
import cli.RegistrarClienteCLI;
import cli.RegistrarCuentaCLI;
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
        while((opcion = menu.mostrarMenu()) < 21 ){
           switch(opcion){
               case 1:
                   new ControladorRegistrarClienteCLI(new RegistrarClienteCLI()).registrarCliente();
               case 2:
                   new ControladorRegistrarCuentaCLI(new RegistrarCuentaCLI()).registrarCuenta();
               case 3:
                   
               case 4:
                   break;
               case 5:
                   break;
               case 6:
                   break;
               case 7:
                   break;
               case 8:
                   break;
               case 9:
                   break;
               case 10:
                   break;
               case 11:
                   break;
               case 12:
                   break;
               case 13:
                   break;
               case 14:
                   break;
               case 15:
                   break;
               case 16:
                   break;
               case 17:
                   break;
               case 18:
                   break;
               case 19:
                   break;
               case 20:
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
