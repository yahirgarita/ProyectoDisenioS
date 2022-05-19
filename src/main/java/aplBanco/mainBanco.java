package aplBanco;
import gui.Menu;
import controladoresgui.ControladorMenu;
/**
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 * 
 * @version 1.0
 */
public class mainBanco {
    public static void main(String[] arg){
        Menu inicio = new Menu();
        ControladorMenu controladorMenu = new ControladorMenu(inicio);
        controladorMenu.menuInicial.setVisible(true);
    }
}
