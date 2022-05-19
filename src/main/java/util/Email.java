package util;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.SendFailedException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jimmy
 */
public class Email {
   
    public static void enviarEmail(String correo,String mensaje) throws MessagingException {
        String host = "smtp.gmail.com";
        //String contrasena = "rapiexpress1234";
        String contrasena = "proyecto1234";
        //String correoEmisor = "rapiexpressprueba@gmail.com";
        String correoEmisor = "proyectodisenio13@gmail.com";
        String correoDestinatario = correo;
        // Get system properties
        Properties props = System.getProperties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        Session session = Session.getInstance(props, null);
        MimeMessage message = new MimeMessage(session);
        
        message.setFrom(new InternetAddress(correoEmisor));
        message.setRecipients(Message.RecipientType.TO, correoDestinatario);
        message.setSubject("Información inportante del banco");
   
        message.setText(mensaje);
        try {
            Transport tr = session.getTransport("smtps");
            tr.connect(host, correoEmisor, contrasena);
            tr.sendMessage(message, message.getAllRecipients());
            System.out.println("Mensaje se ha mandado");
            tr.close();
        } 
        catch (SendFailedException e) {
            System.out.println(e);
        }
    }

}
    