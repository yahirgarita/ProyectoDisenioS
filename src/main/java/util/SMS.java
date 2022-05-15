/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
 
import com.twilio.Twilio; 
import com.twilio.rest.api.v2010.account.Message; 
import com.twilio.type.PhoneNumber;
import java.util.Random;
/**
 *
 * @author Jimmy
 */
public class SMS {
    
    private static final String ACCOUNT_SID = "AC7322a8cae254e2177d28f29cb19bf7be"; 
    private static final String AUTH_TOKEN = "d637752ec7a56dddc0a0dba8696eb2e0"; 
    private static final String numYAbecedario = "abcdefghijklmnopqrstuvwxyz1234567890";
 
    public static String enviarSMS(String destinario) { 
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN); 
        String palabraMsgTel = crearPalabra();
        Message message = Message.creator(new PhoneNumber("+506" + destinario), 
                new PhoneNumber("+19704504353"), palabraMsgTel).create();
        return palabraMsgTel;
    } 
    private static String crearPalabra(){
        Random random = new Random();
            StringBuilder palabra = new StringBuilder();
            for(int i = 0; i<7; i++){
                int numRandom = random.nextInt(numYAbecedario.length());
                char cadenaRandom = numYAbecedario.charAt(numRandom );
                palabra.append(cadenaRandom);
            }
            return palabra.toString();
    }
}
