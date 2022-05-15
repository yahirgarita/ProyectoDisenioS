package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TipoCambio es una clase que se comunica con el WebService del BCCR para obtener
 * el tipo de cambio en tiempo real. Se comunica mediante el protocolo HTTP GET
 * Sólo funciona con la moneda USD
 * @author Jimmy 
 * @version 1.0
 */
public class TipoCambio {

  private int indicador = 0; //317: Compra, 318: Venta
  private String fechaInicio;
  private String fechaFinal;
  private final String nombre = "Jimmy";
  private final String HOST = "https://gee.bccr.fi.cr/Indicadores/Suscripciones/WS/wsindicadoreseconomicos.asmx/ObtenerIndicadoresEconomicosXML";
  private String url;
  private final String VALUE_TAG = "NUM_VALOR";
  
  public TipoCambio(){
    setFecha();
  }
  
  /**
   * Devuelve el valor de <strong>COMPRA</strong> USD del BCCR
   * @return <code>Double</code> con el valor del precio de compra.
   */
  public double getCompra(){
    setCompra();
    
    double valor = Double.parseDouble(getValue());
    return valor;
  }
  
  /**
   * Devuelve el valor de <strong>VENTA</strong> USD del BCCR
   * @return <code>Double</code> con el valor del precio de venta.
   */
  public double getVenta(){
    setVenta();
    
    double valor = Double.parseDouble(getValue());
    return valor;
  }
  
  /**
   * Obtiene el XML del WebService del BCCR y parsea el documento para obtener el valor (dado por VALUE_TAG)
   * @return String
   */
  private String getValue(){
    try {
      setUrl(); //Set del Url con los Parámetros
      
      //Obtiene y Parsea el XML
      String data = GetRequest.getHTML(url);
      XmlParser xml = new XmlParser(data);
      
      //Retorna el valor del tag
      return xml.getValue(VALUE_TAG);
    } catch (Exception e) {
      System.out.println("Error al obtener el valor del BCCR.");
      return "0";
    }
  }
  
  private void setUrl(){
    String params = "Indicador=" + this.indicador + "&FechaInicio=" + this.fechaInicio + "&FechaFinal=" + this.fechaFinal + ""
            + "&Nombre=" + this.nombre + "&SubNiveles=N&CorreoElectronico=jimmytsafg@gmail.com&Token=ME04SMSAMS";
    this.url = HOST+"?"+params;
  }
  
  private void setFecha(){
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    this.fechaInicio = sdf.format(date);
    this.fechaFinal = fechaInicio;
  }
  
  private void setCompra(){
    this.indicador = 317;
  }
  
  private void setVenta(){
    this.indicador = 318;
  }
  
  public static void main(String[] args){
      TipoCambio nuevo = new TipoCambio();
      System.out.println(nuevo.getCompra());
      System.out.println(nuevo.getVenta());
  }
}