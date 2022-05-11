package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * GetMethod realiza comunicaciones HTTP mediante el protocolo GET.
 * @version 1.0
 */
public class GetRequest {
  /**
   * Devuelve un documento HTML a partir de una solicitud HTTP GET
   * @param urlToRead con el URL a donde enviar la solicitud (incluido parámetros)
   * @return String con el HTML dado por la solicitud HTTP GET
   * @throws Exception
   */
  protected static String getHTML(String urlToRead) throws Exception {
    StringBuilder result = new StringBuilder();
    URL url = new URL(urlToRead);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    
    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    String line;
    while ((line = rd.readLine()) != null) {
       result.append(line);
    }
    rd.close();
    return result.toString();
  }
  
}