package com.pico.placa.rules;


import org.junit.jupiter.api.Test;

import com.pico.domain.rules.PicoPlacaSchedule;

import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class PicoPlacaScheduleTest {
    
    private final PicoPlacaSchedule schedule = new PicoPlacaSchedule();

    @Test
    void testRestrictedDigitsByDay() {
        assertTrue(schedule.getRestrictedDigitsByDay(DayOfWeek.MONDAY).contains(1));
        assertTrue(schedule.getRestrictedDigitsByDay(DayOfWeek.MONDAY).contains(2));

        assertTrue(schedule.getRestrictedDigitsByDay(DayOfWeek.TUESDAY).contains(3));
        assertTrue(schedule.getRestrictedDigitsByDay(DayOfWeek.TUESDAY).contains(4));

        assertTrue(schedule.getRestrictedDigitsByDay(DayOfWeek.SATURDAY).isEmpty());
        assertTrue(schedule.getRestrictedDigitsByDay(DayOfWeek.SUNDAY).isEmpty());
    }

    @Test
    void testIsRestrictedHour(){
        //Morning
        assertTrue(schedule.isRestrictedHour(LocalTime.of(7,0)));
        assertTrue(schedule.isRestrictedHour(LocalTime.of(8,15)));
        assertTrue(schedule.isRestrictedHour(LocalTime.of(9,30)));

        //Afternoon
        assertTrue(schedule.isRestrictedHour(LocalTime.of(16,0)));
        assertTrue(schedule.isRestrictedHour(LocalTime.of(18,45)));
        assertTrue(schedule.isRestrictedHour(LocalTime.of(19,30)));

    }

    @Test
    void testIsPlateRestrictedCombinesEverything(){
        LocalDate monday = LocalDate.of(2025,2,10);
        assertTrue(schedule.isPlateRestricted(1, monday, LocalTime.of(8, 0)));
        assertTrue(schedule.isPlateRestricted(2, monday, LocalTime.of(7, 0)));


        LocalDate friday = LocalDate.of(2025, 2, 14); // Friday
        assertTrue(schedule.isPlateRestricted(9, friday, LocalTime.of(17, 30)));
        assertTrue(schedule.isPlateRestricted(0, friday, LocalTime.of(16, 0)));

        LocalDate sunday = LocalDate.of(2025, 2, 9); // Sunday
        assertFalse(schedule.isPlateRestricted(1, sunday, LocalTime.of(8, 0)));
        assertFalse(schedule.isPlateRestricted(9, sunday, LocalTime.of(17, 0)));
    }
}
