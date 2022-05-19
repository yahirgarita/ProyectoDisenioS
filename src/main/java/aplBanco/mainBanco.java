package aplBanco;
import gui.Menu;
import controladores.ControladorMenu;
/**
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 * 
 * @version 1.0
 */
public class mainBanco {
    public static void main(String[] arg){
        /*RegistroCliente inicio = new RegistroCliente();
        ControladorRegistroClientes controladorReg = new ControladorRegistroClientes(inicio);
        controladorReg.registrarCliente.setVisible(true);*/
        Menu inicio = new Menu();
        ControladorMenu controladorMenu = new ControladorMenu(inicio);
        controladorMenu.menuInicial.setVisible(true);
    }
}
