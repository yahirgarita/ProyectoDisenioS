/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
 
import com.twilio.Twilio; 
import com.twilio.rest.api.v2010.account.Message; 
/**
 *
 * @author Jimmy
 */
public class SMS {
    
    private static final String ACCOUNT_SID = "AC6ae52385045589d885e786247b2b2634"; 
    private static final String AUTH_TOKEN = "1060dd966cdc4b16522992c3d0052e03"; 
 
    public void enviarSMS(String destinario, String mensaje) { 
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN); 
        Message message = Message.creator( 
                new com.twilio.type.PhoneNumber("+506" + destinario),  
                "MGbda8d6e4be5c75bc732e620fc50d7cb1", 
                mensaje)      
            .create(); 
 
        System.out.println(message.getSid()); 
    } 
}
