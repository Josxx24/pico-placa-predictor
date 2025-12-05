package com.pico.domain.predictor;

import java.time.LocalDate;
import java.time.LocalTime;

import com.pico.domain.models.Plate;
import com.pico.domain.rules.PicoPlacaSchedule;


public class PicoPlacaPredictor {
    private final PicoPlacaSchedule schedule;

    public PicoPlacaPredictor(PicoPlacaSchedule schedule){
        this.schedule = schedule;
    }

    public boolean canDrive(Plate plate, LocalDate date, LocalTime time){

        if(plate == null || date == null || time == null){
            throw new IllegalArgumentException("Plate, date and time must not be null");
        }

        int lastDigit = plate.getLastDigit();

        boolean restricted = schedule.isPlateRestricted(lastDigit, date, time);

        return !restricted;
    }

    public PredictionResult getPrediction(Plate plate, LocalDate date, LocalTime time) {

        boolean canDrive = canDrive(plate, date, time);

        String message = canDrive
                ? "El vehículo SÍ puede circular."
                : "El vehículo NO puede circular por Pico y Placa.";

        return new PredictionResult(canDrive, message);
    }

    public static class PredictionResult{
        public final boolean canDrive;
        public final String message;

        public PredictionResult(boolean canDrive, String message){
            this.canDrive = canDrive;
            this.message = message;
        }
    }
}
