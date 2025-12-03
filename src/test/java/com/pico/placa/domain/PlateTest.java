package com.pico.placa.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.pico.domain.models.*;

public class PlateTest {
    
    @Test
    void shouldCreatePlateWithValidFormat() {
        
        //Arrage
        Plate plate = new Plate("PBX-1234");

        //Act no Actions

        //Assert
        assertEquals("PBX-1234", plate.getValue());
    }

    @Test
    void shouldExtractLastDigitCorrectly(){
        //Arragge
        Plate plate = new Plate("ABC-9876");

        //Act
        int lastDigit = plate.getLastDigit();

        //Assert
        assertEquals(6, lastDigit);
    }

    @Test
    void shouldThrowExceptionForInvalidFormat(){
        //Arrage don´t need previous object
        
        //Act & Assert
        assertThrows(IllegalArgumentException.class, () ->{
            new Plate("INVALID");
        });
    }

    @Test
    void shouldThrowExceptionForNullOrEmpty() {
        //Arrage don´t need previous object
        
        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Plate(null));
        assertThrows(IllegalArgumentException.class, () -> new Plate(""));
    }
}
