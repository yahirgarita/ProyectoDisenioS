package logicadeaccesoadatos;

import static java.lang.Integer.parseInt;
import java.sql.*;
import java.sql.ResultSet;

/**
 *
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 * 
 * @version 1.0
 */
public class ConexionBD {
    
    Connection conexionBD = null;
    String usuario = "sqlserver";
    String contrasenia = "proyecto1234";
    String daseDatos ="PROYECTODISENO";
    String puerto = "1433";
    String database = "proyectoBanco";
    
    public Connection conexionDataBase(){
         try {
             String dbURL = "jdbc:sqlserver://DESKTOP-MASRC1K\\SQLEXPRESS:" + puerto + ";" + "database=" +database;
             conexionBD = DriverManager.getConnection(dbURL, usuario, contrasenia);
        } catch (SQLException e) {
               e.printStackTrace();
        } 
        return conexionBD;
   }
    public void salirBD(){
        try{
            conexionBD.close();
        }catch(Exception e){
            e.printStackTrace();
            /*JOptionPane.showMessageDialog(null, "Se ha presentado un error;" + e.toString());*/
        }
    }
    /**
     * 
     * @param pTexto
     * @return nulo
     */
    public ResultSet inquiry(String pTexto){
        try{
            PreparedStatement declaracion = conexionBD.prepareStatement(pTexto);
            ResultSet resultado = declaracion.executeQuery();
            return resultado;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public int ejecutarSentSQL(String pTexto){
        try{
            PreparedStatement declaracion = conexionBD.prepareStatement(pTexto);
            declaracion.execute();
            return 1;
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
            
    }
}
