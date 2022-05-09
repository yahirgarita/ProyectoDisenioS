package logicadenegocios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import logicadeaccesoadatos.ConexionBD;
import logicadeaccesoadatos.PersonaBD;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 * 
 * @version 1.0
 */
public class Persona {
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String correoPersona;
    private String codigo;
    private String rol;
    
    private int idPersona;
    private int numTelefonico;
    private static final ConexionBD conexionBD = new ConexionBD();
    private int cantidadUsuarios = 0;
    
    private LocalDate fechaNacimiento;
    private ArrayList<CuentaBancaria> misCuentas;


    /**
     * Constructor para la clase Persona
     *  
     * @param pNombre               El nombre de la persona
     * @param pPrimeroApellido      El primer apellido de la persona
     * @param pSegundoApellido      El segundo apellido de la persona
     * @param pCorreoPersona        El correo electronico de la persona
     * @param pIdPersona            El número de identificación de la persona
     * @param pNumTelefonico        El numéro telefonico de la persona
     * @param pFechaNacimiento      La fecha de nacimiento de la persona
     */
    public Persona(String pPrimerApellido, String pSegundoApellido, String pNombre, int pIdPersona, LocalDate pFechaNacimiento,
            int pNumTelefonico, String pCorreoPersona) throws SQLException{
            conexionBD.conexionDataBase();
            ResultSet resultado = conexionBD.inquiry("SELECT * FROM Persona")  ;
            while(resultado.next()){
                cantidadUsuarios++;
            }
            this.primerApellido = pPrimerApellido;
            this.segundoApellido = pSegundoApellido;
            this.nombre = pNombre;
            this.idPersona = pIdPersona;
            this.numTelefonico= pNumTelefonico;
            this.correoPersona = pCorreoPersona;
            this.codigo = "CIF_" + cantidadUsuarios; 
            this.fechaNacimiento = pFechaNacimiento;
            this.misCuentas = new ArrayList<>();
    }
   
              
        /**
         * Conocer la información que contiene el objeto de tipo Persona.
         * 
         * @return la información que contiene el objeto.
         */
        
        public String toString(){
            String info = "";
            info += "\n Nombre completo: " + getNombreCompleto();
            info += "\n Identificación: " + this.idPersona;
            info += "\n Fecha de nacimiento: " + this.fechaNacimiento;
            info += "\n Teléfono: " + this.numTelefonico;
            info += "\n Correo Electrónico" + this.correoPersona;
            info += "\n Código de usuario" + this.codigo + "\n";
            return info;
        }

    public String getNombre() {
        return nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public int getNumTelefonico() {
        return numTelefonico;
    }

    public String getCorreoPersona() {
        return correoPersona;
    }

    public String getCodigo() {
        return codigo;
    }
    
    public ArrayList<CuentaBancaria> getMisCuentas(){
        return misCuentas;
    }
    
    public void aniadirCuentaBancariaCliente(CuentaBancaria pCuenta){
        this.misCuentas.add(pCuenta);
    }
    
    /**
    * String que permite enviar un mensaje de creación del usuario
    * 
    * @return msg
    * 
    */
    public String mensajeCreacion(){
        String msg = "";
        msg += "Se ha creado un nuevo cliente el sistema los datos que " +
        "\n Nombre cliente: "+ this.getNombreCompleto() + "\n Código: " + this.codigo + "\n Identifiación: " + this.idPersona +
        "\n Fecha de nacimiento: " + this.fechaNacimiento.toString() + " \n Número telefónico: +506 " + this.numTelefonico;
        return msg;
    }
        
    public String getNombreCompleto(){
        return this.nombre + " " + this.primerApellido + " " + this.segundoApellido;
    }
}
