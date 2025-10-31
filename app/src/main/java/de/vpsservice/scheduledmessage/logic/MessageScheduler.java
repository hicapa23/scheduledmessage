package de.vpsservice.scheduledmessage.logic;


import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.RequiresPermission;

import de.vpsservice.scheduledmessage.model.ScheduleMessage;
import de.vpsservice.scheduledmessage.receiver.TriggerReceiver;

/**
 * Clase responsable de programar alarmas para enviar mensajes en el momento indicado.
 * Utiliza AlarmManager para activar un BroadcastReceiver cuando llega la hora.
 */
public class MessageScheduler {

    /**
     * Programa una alarma para enviar el mensaje en el momento especificado.
     *
     * @param context             Contexto de la aplicación
     * @param message             Instancia de ScheduledMessage
     */
    @RequiresPermission(Manifest.permission.SCHEDULE_EXACT_ALARM)
    public static void schedule(Context context, ScheduleMessage message, String phoneNumber) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent( context, TriggerReceiver.class);
        intent.putExtra("messageText", message.messageText);
        intent.putExtra("sendTimeMillis", message.sendTimeMillis);
        intent.putExtra("selectedPhoneNumber", phoneNumber);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context,
                (int) message.sendTimeMillis,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                message.sendTimeMillis,
                pendingIntent
        );

    }
}
