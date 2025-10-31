/*
package de.vpsservice.scheduledmessage.model;

import de.vpsservice.scheduledmessage.util.TimeConverter;

import junit.framework.TestCase;

public class ScheduleMessageTest extends TestCase {


    public void testMarkAsSent() {

        //subject
        ScheduleMessage subject = new ScheduleMessage("Hola buenos dias", TimeConverter.dateAndTime(2025, 11, 5, 15, 30));
        subject.markAsSent();

        //expected
        ScheduleMessage expected = new ScheduleMessage("Hola buenos dias", 1762353000000L);

        expected.markAsSent();

        //result
        assertEquals(expected.messageText, subject.messageText);
        assertEquals(expected.sendTimeMillis, subject.sendTimeMillis);
        assertEquals(expected.sent, subject.sent);
    }

    public void testNonMarkAsSent() {

        //subject
        ScheduleMessage subject = new ScheduleMessage("Hola buenos dias", TimeConverter.dateAndTime(2025, 11, 5, 15, 30));

        //expected
        ScheduleMessage expected = new ScheduleMessage("Hola buenos dias", 1762353000000L);

        //result
        assertEquals(expected.messageText, subject.messageText);
        assertEquals(expected.sendTimeMillis, subject.sendTimeMillis);
        assertEquals(expected.sent, subject.sent);

    }
}
*/


