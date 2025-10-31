/*
package de.vpsservice.mensajeplan.model;

import java.io.Serializable;

*/
/**
 * Representa una regla de respuesta automática.
 * Define condiciones como palabras clave y horarios, y el texto de respuesta.
 *//*

public class AutoReplyRule implements Serializable {

    */
/** Palabra clave que debe estar presente en el mensaje recibido *//*

    public String keyword;

    */
/** Texto que se enviará como respuesta automática *//*

    public String replyText;

    */
/** Hora de inicio en milisegundos (opcional) *//*

    public long startTimeMillis;

    */
/** Hora de fin en milisegundos (opcional) *//*

    public long endTimeMillis;

    */
/**
     * Constructor principal.
     * @param keyword Palabra clave que activa la regla
     * @param replyText Texto de respuesta automática
     * @param startTimeMillis Hora de inicio (opcional)
     * @param endTimeMillis Hora de fin (opcional)
     *//*

    public AutoReplyRule(String keyword, String replyText, long startTimeMillis, long endTimeMillis) {
        this.keyword = keyword;
        this.replyText = replyText;
        this.startTimeMillis = startTimeMillis;
        this.endTimeMillis = endTimeMillis;
    }

    */
/**
     * Verifica si la regla está activa en el momento actual.
     * @param currentTimeMillis Tiempo actual en milisegundos
     * @return true si está dentro del rango horario
     *//*

    public boolean isActive(long currentTimeMillis) {
        return currentTimeMillis >= startTimeMillis && currentTimeMillis <= endTimeMillis;
    }
}
*/
