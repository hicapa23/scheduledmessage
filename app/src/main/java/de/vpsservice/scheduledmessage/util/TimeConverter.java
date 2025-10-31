package de.vpsservice.scheduledmessage.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

public final class TimeConverter {

    public static long dateAndTime(int year, int month, int day, int hours, int minutes) {
        LocalDateTime dt = LocalDateTime.of(year, month, day, hours, minutes);

        return dt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

    }

    public static long toMillis(LocalDateTime dt) {
        return dt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }


}
