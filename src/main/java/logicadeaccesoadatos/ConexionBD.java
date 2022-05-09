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
    
    public Connection conexionDataBase(){
         try {
             String dbURL = "jdbc:sqlserver://34.133.118.143;databaseName=PROYECTODISENO";
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
    /*
    public static void importarBD(){
      try {
        String SQL = "SELECT * FROM Cuenta";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        while (rs.next()) {
            Cuenta escuela = new Cuenta(rs.getString("nombre"),rs.getString("codigo"));
            escuelas.add(escuela);
        }
        SQL = "SELECT * FROM Operacion";
        stmt = conn.createStatement();
        rs = stmt.executeQuery(SQL); 
        while (rs.next()) {
            Curso curso = new Curso(rs.getString("nombre"),rs.getString("codigoUnico"),rs.getString("codigoEscuela"),parseInt(rs.getString("cantidadCreditos")),parseInt(rs.getString("cantidadHoras")),rs.getString("numSemestre"));
            cursos.add(curso);
        }
         SQL = "SELECT * FROM Persona";
         stmt = conn.createStatement();
         rs = stmt.executeQuery(SQL);
         while (rs.next()) {
            PlanDeEstudio planDeEstudio = new PlanDeEstudio(Integer.parseInt(rs.getString("numeroDePlan")),rs.getString("fechaDeVigencia"), rs.getString("codigoEscuela"));
            planesDeEstudios.add(planDeEstudio);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
      }
    }*/
}
