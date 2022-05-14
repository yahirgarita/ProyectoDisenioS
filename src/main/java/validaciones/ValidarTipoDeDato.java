
package validaciones;

import java.util.regex.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author yahir
 */
public class ValidarTipoDeDato {
    
    
     public static boolean validarFormatoPIN(String pPin){
        String simbolosPin = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])([A-Za-z\\d$@$!%*?&]|[^ ]){6}$";
        Pattern patron = Pattern.compile(simbolosPin);
        Matcher matcher = patron.matcher(pPin);
        return matcher.matches();
    }
    
    public static boolean validarEsEntero(String numero){
        try{
            int num = Integer.parseInt(numero);
            return true;
            
        }catch(Exception e){
            return false;
        }
    }
    
    public static boolean validarFormatoCorreo(String pCorreo){
        String simbolosCorreo = "^(.+)@(.+)$";
        Pattern patron = Pattern.compile(simbolosCorreo);
        Matcher matcher = patron.matcher(pCorreo);
        return matcher.matches();
    }
    
    public static boolean validarFormatoFechaCLI(String pFecha){
        Date date = null;
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            date = formato.parse(pFecha);
            if (!pFecha.equals(formato.format(date))) {
                date = null;
            }
        } catch (Exception e) {
        }
        return date != null;
    }
    
    public static LocalDate corregirFormatoFechaCLI(String pFecha){
        Date date = null;
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            date = formato.parse(pFecha);
            if (!pFecha.equals(formato.format(date))) {
                date = null;
            }
        } catch (Exception ex) {
        }
        return corregirFormatoFecha(date);
    }
    
    public static LocalDate corregirFormatoFecha(Date fecha){
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        String fechaFormatoCorrecto = formato.format(fecha);
        LocalDate nuevaFecha = LocalDate.parse(fechaFormatoCorrecto, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return nuevaFecha;
    }
}
