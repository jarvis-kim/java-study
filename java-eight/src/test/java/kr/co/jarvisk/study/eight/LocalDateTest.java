package kr.co.jarvisk.study.eight;

import org.junit.Test;

import java.time.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;

public class LocalDateTest {

    @Test
    public void testDate() {
        LocalDate localDate = LocalDate.of(2016, Month.DECEMBER, 1);

        assertEquals(localDate.getYear(), 2016);
        assertEquals(localDate.getMonthValue(), 12);
        assertEquals(localDate.getDayOfMonth(), 1);

        LocalDate plusDay = localDate.plusDays(5);
        assertNotSame(plusDay, localDate);

        assertEquals(LocalDate.of(2016, Month.DECEMBER, 6), plusDay);

        ZonedDateTime seoulTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        assertEquals("+09:00", seoulTime.getOffset().toString());

        ZonedDateTime utcTime = ZonedDateTime.now(ZoneId.of("UTC"));
        assertEquals("Z", utcTime.getOffset().toString());

        ZonedDateTime laTime = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
        assertEquals("-08:00", laTime.getOffset().toString());

        ZonedDateTime zonedUtcTime = ZonedDateTime.of(2016, 12, 1, 10, 0, 0, 0, ZoneId.of("UTC"));
        ZonedDateTime zonedSeoulTime = zonedUtcTime.withZoneSameInstant(ZoneId.of("Asia/Seoul"));

        System.out.println("UTC Zone : " + zonedUtcTime);
        System.out.println("Asia/Seoul : " + zonedSeoulTime);

        assertNotEquals(zonedUtcTime.getHour(), zonedSeoulTime.getHour());
        assertEquals(zonedUtcTime, zonedSeoulTime.withZoneSameInstant(ZoneId.of("UTC")));
    }
}
