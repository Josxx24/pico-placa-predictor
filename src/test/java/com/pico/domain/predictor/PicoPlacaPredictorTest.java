package com.pico.domain.predictor;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import com.pico.domain.models.Plate;
import com.pico.domain.rules.PicoPlacaSchedule;

import com.pico.domain.predictor.PicoPlacaPredictor;

public class PicoPlacaPredictorTest {

    private final PicoPlacaPredictor predictor =
            new PicoPlacaPredictor(new PicoPlacaSchedule());

    @Test
    void testPlateAllowed() {
        Plate plate = new Plate("ABC-1234");
        LocalDate sunday = LocalDate.of(2025, 2, 9);

        assertTrue(predictor.canDrive(plate, sunday, LocalTime.of(10, 0)));
    }

    @Test
    void testPlateRestricted() {
        Plate plate = new Plate("ABC-1231");
        LocalDate monday = LocalDate.of(2025, 2, 10);

        assertFalse(predictor.canDrive(plate, monday, LocalTime.of(8, 0)));
    }

    @Test
    void testBoundaryTimes() {
        Plate plate = new Plate("ABC-1231");
        LocalDate monday = LocalDate.of(2025, 2, 10);

        // Horas límite, aún restringidas
        assertFalse(predictor.canDrive(plate, monday, LocalTime.of(7, 0)));
        assertFalse(predictor.canDrive(plate, monday, LocalTime.of(9, 30)));
    }

    @Test
    void testPredictionMessage() {
        Plate plate = new Plate("ABC-1231");
        LocalDate monday = LocalDate.of(2025, 2, 10);

        PicoPlacaPredictor.PredictionResult result =
                predictor.getPrediction(plate, monday, LocalTime.of(8, 0));

        assertFalse(result.canDrive);
        assertEquals("El vehículo NO puede circular por Pico y Placa.", result.message);
    }
}
