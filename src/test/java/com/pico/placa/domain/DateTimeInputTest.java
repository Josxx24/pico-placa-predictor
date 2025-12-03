package com.pico.placa.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.pico.domain.models.*;

public class DateTimeInputTest {
    @Test
    void shouldParseValidDateAndTime() {
        DateTimeInput input = new DateTimeInput("10-02-2025","07:30");

        assertEquals(LocalDate.of(2025,2,10), input.getDate());
        assertEquals(LocalTime.of(07,30), input.getTime());
    }

    @Test 
    void shouldThrowExceptionForInvalidDate() {
        assertThrows(IllegalArgumentException.class, () -> {
            new DateTimeInput("2025-02-10", "07:30"); //Wrong format
        }); 
    }

    @Test 
    void shouldThrowExceptionForInvalidTime() {
        assertThrows(IllegalArgumentException.class, () -> {
            new DateTimeInput("10-02-2025", "07:30 PM"); //Wrong format
        }); 
    }

    @Test
    void shouldReturnCorrectlyDayOfWeek() {
        //10 de Feb de 2025 LUNES -> 1
        DateTimeInput input = new DateTimeInput("10-02-2025", "08:30");
        assertEquals(1, input.getDayOfWeek());
    }
}
