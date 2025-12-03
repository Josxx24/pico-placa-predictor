package com.pico.placa.domain;

import org.junit.jupiter.api.Test;

import com.pico.domain.models.Vehicle;

import static org.junit.jupiter.api.Assertions.*;

import com.pico.domain.models.*;

public class VehicleTest {
    
    @Test
    void shouldCreateVehicleWithValidPlate() {
        Plate plate = new Plate("PQR-5678");
        Vehicle vehicle = new Vehicle(plate);

        assertEquals("PQR-5678", vehicle.getPlate().getValue());
    }

    @Test
    void shouldThrowExceptionWhenPlateIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Vehicle(null);
        });
    }

    @Test
    void shouldReturnCorrectLastDigitFromVehiclePlate(){
        Vehicle vehicle = new Vehicle(new Plate("XYZ-4321"));
        assertEquals(1, vehicle.getPlate().getLastDigit());
    }
}
