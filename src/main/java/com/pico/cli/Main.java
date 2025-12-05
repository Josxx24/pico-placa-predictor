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
        boolean continuar = true;

        while (continuar) {
            try {
                System.out.println("\n=== PICO Y PLACA PREDICTOR ===");

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

                // Preguntar si desea continuar
                continuar = preguntarContinuar(sc);

            } catch (Exception e) {
                System.out.println("\n⚠️  Error: " + e.getMessage());
                System.out.println("Por favor, intente nuevamente...");
            }
        }

        System.out.println("\n¡Gracias por usar Pico y Placa Predictor!");
        sc.close();
    }

    private static boolean preguntarContinuar(Scanner sc) {
        while (true) {
            System.out.print("\n¿Desea consultar otra placa? (s/n): ");
            String respuesta = sc.nextLine().trim().toLowerCase();
            
            if (respuesta.equals("s") || respuesta.equals("si")) {
                return true;
            } else if (respuesta.equals("n") || respuesta.equals("no")) {
                return false;
            } else {
                System.out.println("Respuesta inválida. Ingrese 's' para sí o 'n' para no.");
            }
        }
    }
}
