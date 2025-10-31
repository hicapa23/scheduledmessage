package de.vpsservice.scheduledmessage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import android.view.ViewGroup;
import android.widget.*;

import java.sql.Date;
import java.util.List;

import de.vpsservice.scheduledmessage.logic.MessageScheduler;
import de.vpsservice.scheduledmessage.model.ScheduleMessage;
import de.vpsservice.scheduledmessage.storage.MessageStore;
import de.vpsservice.scheduledmessage.util.ContactPickerHelper;
import de.vpsservice.scheduledmessage.util.DateTimePickerHelper;

/**
 * Actividad principal que permite programar mensajes y ver el historial.
 */
public class MainActivity extends AppCompatActivity {

    private EditText messageInput;
    private Button timeButton;
    private LinearLayout messageListLayout;
    private long selectedTimeMillis = -1;
    private MessageStore store;

    private String selectedPhoneNumber = null;

    /**
     * Método de ciclo de vida que se ejecuta al iniciar la actividad.
     * Inicializa los componentes de UI, listeners y carga los mensajes guardados.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        store = new MessageStore(this);

        messageInput = findViewById(R.id.messageInput);
        Button contactButton = findViewById(R.id.contactButton);
        timeButton = findViewById(R.id.timeButton);
        Button scheduleButton = findViewById(R.id.scheduleButton);
        messageListLayout = findViewById(R.id.messageListLayout);

        contactButton.setOnClickListener(v -> ContactPickerHelper.launchPicker(this));
        timeButton.setOnClickListener(v -> showDateTimePicker());
        scheduleButton.setOnClickListener(v -> scheduleMessage());

        refreshMessageList();
    }

    private void showDateTimePicker() {
        DateTimePickerHelper.show(this, timeButton, (dateTime, millis) -> {
            selectedTimeMillis = millis;
        });
    }

    /**
     * Programa un nuevo mensaje si el texto y la hora están definidos.
     * Guarda el mensaje, lo agenda con MessageScheduler y actualiza la lista.
     */
    @SuppressLint("SetTextI18n")
    private void scheduleMessage() {
        String text = messageInput.getText().toString().trim();
        if (text.isEmpty() || selectedTimeMillis == -1 || selectedPhoneNumber == null) {
            Toast.makeText(this, "Completa el mensaje, la hora y el contacto", Toast.LENGTH_SHORT).show();
            return;
        }

        ScheduleMessage msg = new ScheduleMessage(text, selectedTimeMillis, selectedPhoneNumber);
        List<ScheduleMessage> messages = store.getAllMessages();
        messages.add(msg);
        store.overwriteMessages(messages);

        MessageScheduler.schedule(this, msg, selectedPhoneNumber);
        Toast.makeText(this, "Mensaje programado", Toast.LENGTH_SHORT).show();

        messageInput.setText("");
        timeButton.setText("Seleccionar hora");
        selectedPhoneNumber = null;
        selectedTimeMillis = -1;
        refreshMessageList();
    }

    /**
     * Refresca la lista visual de mensajes programados.
     * Crea dinámicamente un TextView por cada mensaje guardado.
     */
    @SuppressLint("SetTextI18n")
    private void refreshMessageList() {
        messageListLayout.removeAllViews();
        List<ScheduleMessage> messages = store.getAllMessages();

        for (int i = 0; i < messages.size(); i++) {
            ScheduleMessage msg = messages.get(i);

            LinearLayout itemLayout = new LinearLayout(this);
            itemLayout.setOrientation(LinearLayout.HORIZONTAL);
            itemLayout.setPadding(8, 8, 8, 8);

            TextView itemText = new TextView(this);
            itemText.setText(msg.messageText + " → " + new Date(msg.sendTimeMillis) + msg.s);
            itemText.setTextSize(16);
            itemText.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));

            Button deleteButton = new Button(this);
            deleteButton.setText("🗑️");
            deleteButton.setOnClickListener(v -> {
                messages.remove(msg);
                store.overwriteMessages(messages);
                refreshMessageList();
            });

            itemLayout.addView(itemText);
            itemLayout.addView(deleteButton);
            messageListLayout.addView(itemLayout);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ContactPickerHelper.CONTACT_PICK_REQUEST && resultCode == RESULT_OK) {
            selectedPhoneNumber = ContactPickerHelper.extractPhoneNumber(this, data);
            if (selectedPhoneNumber != null) {
                Toast.makeText(this, "Número seleccionado: " + selectedPhoneNumber, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
