package com.pico.domain.validation;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;

public class InputValidatorTest {
    
    @Test
    void testValidPlate(){
        assertDoesNotThrow(() -> InputValidator.validatePlate("ABC-1234"));
    }

    @Test
    void testInvalidPlate() {
        assertThrows(IllegalArgumentException.class,
            () -> InputValidator.validatePlate("AB-1234"));

        assertThrows(IllegalArgumentException.class,
            () -> InputValidator.validatePlate("ABCDE-12"));
        
        assertThrows(IllegalArgumentException.class,
            () -> InputValidator.validatePlate("abc-1234")); 
    }

    @Test
    void testValidDate() {
        LocalDate date = InputValidator.validateDate("10-02-2025");
        assertEquals(2025, date.getYear());
        assertEquals(2, date.getMonthValue());
        assertEquals(10, date.getDayOfMonth());
    }

    @Test
    void testInvalidDate(){
        assertThrows(IllegalArgumentException.class,
            () -> InputValidator.validateDate("2025-10-02"));
        
        assertThrows(IllegalArgumentException.class,
            () -> InputValidator.validateDate("32-10-2025"));
    }

    @Test
    void testValidTime(){
        LocalTime time = InputValidator.validateTime("08:30");
        assertEquals(8, time.getHour());
        assertEquals(30, time.getMinute());
    }

    @Test
    void testInvalidTime(){
        assertThrows(IllegalArgumentException.class,
            () -> InputValidator.validateTime("8:30"));

        assertThrows(IllegalArgumentException.class,
            () -> InputValidator.validateTime("25:00"));
        
        assertThrows(IllegalArgumentException.class,
            () -> InputValidator.validateTime("aa:bb")); 
    }

}
