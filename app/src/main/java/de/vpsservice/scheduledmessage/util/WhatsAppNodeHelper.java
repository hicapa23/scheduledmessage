/*
package de.vpsservice.mensajeplan.util;

import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

*/
/**
 * Utilidad para buscar nodos de interfaz en WhatsApp de forma robusta.
 * Permite encontrar campos de texto y botones de enviar usando múltiples estrategias.
 *//*

public class WhatsAppNodeHelper {

    */
/**
     * Crea un Bundle con el texto a insertar en un campo de texto.
     * @param text Texto que se desea insertar
     * @return Bundle con argumento de texto
     *//*

    public static Bundle createTextBundle(String text) {
        Bundle args = new Bundle();
        args.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, text);
        return args;
    }

    */
/**
     * Busca el campo de entrada de texto en WhatsApp.
     * @param root Nodo raíz de la ventana activa
     * @return Nodo del campo de texto o null si no se encuentra
     *//*

    public static AccessibilityNodeInfo findInputField(AccessibilityNodeInfo root) {
        if (root == null) return null;

        // Buscar por ID
        List<AccessibilityNodeInfo> byId = root.findAccessibilityNodeInfosByViewId("com.whatsapp:id/entry");
        if (!byId.isEmpty()) return byId.get(0);

        // Buscar por texto visible
        List<AccessibilityNodeInfo> byText = root.findAccessibilityNodeInfosByText("Escribe un mensaje");
        if (!byText.isEmpty()) return byText.get(0);

        // Buscar por clase
        return findFirstByClass(root, "android.widget.EditText");
    }

    */
/**
     * Busca el botón de enviar en WhatsApp.
     * @param root Nodo raíz de la ventana activa
     * @return Nodo del botón de enviar o null si no se encuentra
     *//*

    public static AccessibilityNodeInfo findSendButton(AccessibilityNodeInfo root) {
        if (root == null) return null;

        // Buscar por ID
        List<AccessibilityNodeInfo> byId = root.findAccessibilityNodeInfosByViewId("com.whatsapp:id/send");
        if (!byId.isEmpty()) return byId.get(0);

        // Buscar por texto visible
        List<AccessibilityNodeInfo> byText = root.findAccessibilityNodeInfosByText("Enviar");
        if (!byText.isEmpty()) return byText.get(0);

        // Buscar por clase
        return findFirstByClass(root, "android.widget.ImageButton");
    }

    */
/**
     * Busca el primer nodo que coincida con una clase específica.
     * @param root Nodo raíz
     * @param className Nombre de la clase (ej. android.widget.EditText)
     * @return Nodo encontrado o null
     *//*

    private static AccessibilityNodeInfo findFirstByClass(AccessibilityNodeInfo root, String className) {
        if (root.getClassName() != null && root.getClassName().equals(className)) {
            return root;
        }

        for (int i = 0; i < root.getChildCount(); i++) {
            AccessibilityNodeInfo child = root.getChild(i);
            AccessibilityNodeInfo result = findFirstByClass(child, className);
            if (result != null) return result;
        }

        return null;
    }
}*/
