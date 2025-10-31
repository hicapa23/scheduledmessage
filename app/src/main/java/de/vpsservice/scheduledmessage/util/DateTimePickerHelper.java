package de.vpsservice.scheduledmessage.util;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.Button;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimePickerHelper {
    public interface OnDateTimeSelected {
        void onSelected(LocalDateTime dateTime, long millis);
    }

    public static void show(Context context, Button targetButton, OnDateTimeSelected callback) {
        LocalDateTime now = LocalDateTime.now();

        new DatePickerDialog(context, (view, year, month, day) -> {
            new TimePickerDialog(context, (view1, hour, minute) -> {
                LocalDateTime selectedDateTime = LocalDateTime.of(year, month + 1, day, hour, minute);
                long millis = TimeConverter.toMillis(selectedDateTime);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
                targetButton.setText(selectedDateTime.format(formatter));

                callback.onSelected(selectedDateTime, millis);
            }, now.getHour(), now.getMinute(), true).show();
        }, now.getYear(), now.getMonthValue() - 1, now.getDayOfMonth()).show();
    }

}
