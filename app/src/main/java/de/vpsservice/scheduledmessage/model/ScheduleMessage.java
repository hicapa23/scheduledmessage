package de.vpsservice.scheduledmessage.model;

import java.io.Serializable;

/**
 * Representa un mensaje programado por el usuario.
 * Contiene el texto del mensaje, la hora de envío, y el estado de envío.
 */
public class ScheduleMessage implements Serializable {

    public String messageText;

    public long sendTimeMillis;

    public boolean sent;

    public String phoneNumber;



    public ScheduleMessage(String messageText, long sendTimeMillis, String phoneNumber){
        this.messageText = messageText;
        this.sendTimeMillis = sendTimeMillis;
        this.phoneNumber = phoneNumber;
        this.sent = false;
    }

    public void markAsSent() {
        this.sent = true;
    }

    @Override
    public String toString() {
        return "[" + sendTimeMillis + "]" + messageText + " - " + (sent ? "Enviado" : "Pendiente") + " " + phoneNumber;
    }
}
