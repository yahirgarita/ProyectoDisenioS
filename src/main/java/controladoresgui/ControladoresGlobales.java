
package controladoresgui;

import gui.Menu;

/**
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 *
 * @version 1.0
 */
public class ControladoresGlobales {
    private Menu menuInicial;
    
   public static void volver(){
        
        Menu inicio = new Menu();
        ControladorMenu menu =  new ControladorMenu(inicio);
        menu.menuInicial.setVisible(true);
        
    }
}
