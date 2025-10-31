/*
package de.vpsservice.mensajeplan.logic;

import android.content.Context;

import java.util.List;

import de.vpsservice.mensajeplan.model.AutoReplyRule;
import de.vpsservice.mensajeplan.storage.MessageStore;

*/
/**
 * Motor de evaluación de reglas de respuesta automática.
 * Verifica si un mensaje recibido activa alguna regla definida.
 *//*

public class RuleEngine {

    private final MessageStore store;

    public RuleEngine(Context context) {
        this.store = new MessageStore(context);
    }

    */
/**
     * Evalúa si el mensaje recibido activa alguna regla.
     * @param incomingMessage Texto del mensaje recibido
     * @param currentTimeMillis Hora actual en milisegundos
     * @return Texto de respuesta si aplica alguna regla, o null si no aplica ninguna
     *//*

    public String evaluate(String incomingMessage, long currentTimeMillis) {
        List<AutoReplyRule> rules = store.getAllRules();

        for (AutoReplyRule rule : rules) {
            if (incomingMessage.toLowerCase().contains(rule.keyword.toLowerCase())
                    && rule.isActive(currentTimeMillis)) {
                return rule.replyText;
            }
        }

        return null;
    }
}
*/
