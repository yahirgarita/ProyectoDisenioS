/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import validaciones.ValidarTipoDeDato;
/**
 *
 * @author Jimmy
 */
public class RegistrarClienteCLI {
    
    public void registrarCliente(String primerApellido,String segundoApellido,String nombre,String identificacion,
            String fechaNacimiento, String numeroTelefonico, String correoElectronico) throws IOException{
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ValidarTipoDeDato validar = new ValidarTipoDeDato();
        System.out.println("Sistema de Registro de Cliente");
        System.out.println("Por favor ingresar su primer apellido: ");
        primerApellido = reader.readLine();
        System.out.println("Por favor ingresar su segundo apellido: ");
        segundoApellido = reader.readLine();
        System.out.println("Por favor ingresar su nombre: ");
        nombre = reader.readLine();
        System.out.println("Por favor ingresar el numero de identificacion: ");
        identificacion = reader.readLine();
        System.out.println("Por favor ingresar la fecha de nacimiento (DD/MM/AAAA): ");
        fechaNacimiento = reader.readLine();
        System.out.println("Por favor ingresar su numero telefonico: ");
        numeroTelefonico = reader.readLine();
        System.out.println("Por favor ingresar el correo electronico: ");
        correoElectronico = reader.readLine();
        

    }

}
