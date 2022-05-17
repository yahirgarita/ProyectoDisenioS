/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplBanco;


import cli.MenuCLI;
import controladorescli.ControladorMenuCLI;
import java.io.IOException;
import java.sql.SQLException;
import javax.mail.MessagingException;

/**
 *
 * @author Jimmy
 */
public class mainBancoCLI {
    public static void main(String[] args) throws IOException, SQLException, MessagingException{
        MenuCLI vistaMenu = new MenuCLI();
        ControladorMenuCLI menu = new ControladorMenuCLI(vistaMenu);
        menu.listarMenu();
    }
}
