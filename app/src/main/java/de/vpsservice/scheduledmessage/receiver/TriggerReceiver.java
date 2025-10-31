package de.vpsservice.scheduledmessage.receiver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import android.net.Uri;
import java.util.List;

import de.vpsservice.scheduledmessage.model.ScheduleMessage;
import de.vpsservice.scheduledmessage.service.WhatsAppAccessibilityService;
import de.vpsservice.scheduledmessage.storage.MessageStore;

/**
 * BroadcastReceiver que se activa cuando la alarma programada suena.
 * Marca el mensaje como enviado y lanza el servicio de accesibilidad si es necesario.
 */

public class TriggerReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String messageText = intent.getStringExtra("messageText");
        long sendTimeMillis = intent.getLongExtra("sendTimeMillis", -1);
        String phoneNumber = intent.getStringExtra("phoneNumber");

        if (messageText == null || sendTimeMillis == -1 || phoneNumber == null) {
            Toast.makeText(context, "Error al recuperar el mensaje programado", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage("com.whatsapp");
        if (launchIntent == null) {
            Toast.makeText(context, "WhatsApp no está instalado", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "https://wa.me/" + phoneNumber + "?text=" + Uri.encode(messageText);
        launchIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        launchIntent.setPackage("com.whatsapp");
        launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(launchIntent);

        MessageStore store = new MessageStore(context);
        List<ScheduleMessage> messages = store.getAllMessages();

        messages.forEach(message -> {
            if (message.sendTimeMillis == sendTimeMillis && message.messageText.equals(messageText))
                message.markAsSent();
        });

        store.overwriteMessages(messages);

        Intent serviceIntent = new Intent(context, WhatsAppAccessibilityService.class);
        serviceIntent.putExtra("messageText", messageText);
        context.startService(serviceIntent);

        Toast.makeText(context, "Mensaje activado: " + messageText, Toast.LENGTH_SHORT).show();
    }
}
