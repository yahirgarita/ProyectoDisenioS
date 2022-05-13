package util;

/**
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 * 
 * @version 1.0
 */
public class Encriptar {
  public static String cifrar(String txtACifrar){
      StringBuilder msgCifrado = new StringBuilder();
      char[] caracter = txtACifrar.toCharArray();
      for(char i: caracter){
          i += 5;
          msgCifrado.append(i);
      }
      return msgCifrado.toString();
  }
  public static String descifrar(String txtCifrado){
      StringBuilder msgDescifrado = new StringBuilder();
      char[] caracter = txtCifrado.toCharArray();
      for(char i: caracter){
          i -= 5;
          msgDescifrado.append(i);
      }
      return msgDescifrado.toString();
  }
            
}
