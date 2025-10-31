package de.vpsservice.scheduledmessage.service;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.os.Handler;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;
/**
 * Servicio de accesibilidad que simula el envío de mensajes en WhatsApp.
 * Se activa cuando el sistema lanza un Intent con el texto del mensaje.
 */

public class WhatsAppAccessibilityService extends AccessibilityService {

    private String pendingMessage = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        pendingMessage = intent.getStringExtra("messageText");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        new Handler().postDelayed(() -> {
            AccessibilityNodeInfo root = getRootInActiveWindow();
            if (root == null || pendingMessage == null) return;

            // Buscar campo de texto
            List<AccessibilityNodeInfo> inputFields = root.findAccessibilityNodeInfosByViewId("com.whatsapp:id/entry");
            if (!inputFields.isEmpty()) {
                AccessibilityNodeInfo input = inputFields.get(0);
                input.performAction(AccessibilityNodeInfo.ACTION_FOCUS);
                input.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, createTextBundle(pendingMessage));
            }

            // Buscar botón de enviar
            List<AccessibilityNodeInfo> sendButtons = root.findAccessibilityNodeInfosByViewId("com.whatsapp:id/send");
            if (!sendButtons.isEmpty()) {
                sendButtons.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }

            pendingMessage = null;
        }, 5000); // espera 5 segundos
    }

    @Override
    public void onInterrupt() {
        // No se requiere manejo especial
    }

    private android.os.Bundle createTextBundle(String text) {
        android.os.Bundle args = new android.os.Bundle();
        args.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, text);
        return args;
    }
}
