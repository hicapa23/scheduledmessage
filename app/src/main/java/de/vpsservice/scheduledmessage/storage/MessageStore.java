package de.vpsservice.scheduledmessage.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

//import de.vpsservice.mensajeplan.model.AutoReplyRule;
import de.vpsservice.scheduledmessage.model.ScheduleMessage;

/**
 * Clase responsable de almacenar y recuperar mensajes programados.
 * Utiliza SharedPreferences y Gson para persistencia local.
 */
public class MessageStore {

    private static final String PREFS_NAME = "scheduled_messages";
    private static final String Key_Messages = "messages";
    private SharedPreferences prefs;
    private Gson gson;

    /**
     * Constructor principal.
     * @param context Contexto de la aplicación
     */
    public MessageStore(Context context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    /**
     * Guarda un nuevo mensaje programado.
     * @param message Instancia de ScheduledMessage
     */
    public void saveMessage(ScheduleMessage message) {
        List<ScheduleMessage> messages = getAllMessages();
        messages.add(message);
        String json = gson.toJson(messages);
        prefs.edit().putString(Key_Messages, json).apply();
    }

    /**
     * Recupera todos los mensajes programados.
     * @return Lista de ScheduledMessage
     */
    public List<ScheduleMessage> getAllMessages() {
        String json = prefs.getString(Key_Messages, null);
        if (json == null)
            return new ArrayList<>();
        Type type = new TypeToken<List<ScheduleMessage>>() {}.getType();
        return  gson.fromJson(json, type);
    }

    /**
     * Actualiza la lista completa de mensajes.
     * @param messages Lista nueva a guardar
     */
    public void overwriteMessages (List<ScheduleMessage> messages) {
        String json = gson.toJson(messages);
        prefs.edit().putString(Key_Messages, json).apply();
    }

    /**
     * Recupera todas las reglas de respuesta automática almacenadas.
     * Utiliza Gson para deserializar la lista desde SharedPreferences.
     *
     * @return Lista de reglas de tipo AutoReplyRule. Si no hay reglas guardadas, devuelve una lista vacía.
     */
/*    public List<AutoReplyRule> getAllRules() {
        String json = prefs.getString("rules", null);
        if (json == null) return new ArrayList<>();

        Type type = new TypeToken<List<AutoReplyRule>>() {}.getType();
        return gson.fromJson(json, type);

    }*/

    /**
     * Elimina todos los mensajes programados.
     */
    public void clearmessage() {
        prefs.edit().remove(Key_Messages).apply();
    }

}
