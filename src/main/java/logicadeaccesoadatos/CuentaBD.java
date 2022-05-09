package logicadeaccesoadatos;

import logicadenegocios.CuentaBancaria;
import util.Encriptar;
/**
 *
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 * 
 * @version 1.0
 */
public class CuentaBD {
    private static final ConexionBD conexionBD = new ConexionBD();
    
    public static void registrarCuentaEnBD(CuentaBancaria pCuenta){
        conexionBD.conexionDataBase();
        conexionBD.ejecutarSentSQL("insert into Cuenta values (" + "'" + Encriptar.cifrar(Integer.toString(pCuenta.getNumCuenta()))
            + "','"+ pCuenta.getFechaCreacion().toString() + "','" + Encriptar.cifrar(Double.toString(pCuenta.getSaldo())) + "','" 
            + pCuenta.getEstatus() + "','" + Encriptar.cifrar(pCuenta.getPin()) + "')" );
        conexionBD.ejecutarSentSQL("inset into PersonaCuenta values (" + "'" + pCuenta.getDuenio().getCodigo() + "','" 
                + Encriptar.cifrar(Integer.toString(pCuenta.getNumCuenta())) + "')");
        conexionBD.salirBD();
    }
}
