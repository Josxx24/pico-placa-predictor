package com.pico.cli;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import com.pico.domain.models.Plate;
import com.pico.domain.predictor.PicoPlacaPredictor;
import com.pico.domain.rules.PicoPlacaSchedule;
import com.pico.domain.validation.InputValidator;

public class Main {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("=== PICO Y PLACA PREDICTOR ===");

            System.out.print("Ingrese la placa (Ej: ABC-1234): ");
            String plateStr = sc.nextLine();
            InputValidator.validatePlate(plateStr);
            Plate plate = new Plate(plateStr);

            System.out.print("Ingrese la fecha (dd-MM-yyyy): ");
            String dateStr = sc.nextLine();
            LocalDate date = InputValidator.validateDate(dateStr);

            System.out.print("Ingrese la hora (HH:mm): ");
            String timeStr = sc.nextLine();
            LocalTime time = InputValidator.validateTime(timeStr);

            PicoPlacaPredictor predictor = new PicoPlacaPredictor(new PicoPlacaSchedule());

            PicoPlacaPredictor.PredictionResult result = predictor.getPrediction(plate, date, time);

            System.out.println("\n==== RESULTADO ===");
            System.out.println(result.message);

        } catch (Exception e) {
            System.out.println("\nError: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
