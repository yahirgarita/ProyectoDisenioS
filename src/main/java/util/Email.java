package util;

import javax.mail.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

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
   
    public void enviar(String correo,String mensaje) throws MessagingException {
        String host = "smtp.gmail.com";
        String contrasena = "rapiexpress1234";
        String correoEmisor = "rapiexpressprueba@gmail.com";
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
        message.setSubject("Imformación importante del banco");
   
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
    