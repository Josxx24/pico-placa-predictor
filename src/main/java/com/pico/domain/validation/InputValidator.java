package com.pico.domain.validation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class InputValidator {

    private static final String PLATE_REGEX = "^[A-Z]{3}-\\d{4}$";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    public static void validatePlate(String plate){
        if(plate == null || !plate.matches(PLATE_REGEX)){
            throw new IllegalArgumentException("Formato de placa inválido. Debe ser ABC-1234.");
        }
    }

    public static LocalDate validateDate(String date){
        try {
            return LocalDate.parse(date, DATE_FORMAT);
        } catch (DateTimeParseException e){
            throw new IllegalArgumentException("Formato de fecha inválido. Use dd-MM-yyyy.");
        }
    }

    public static LocalTime validateTime(String time) {
        try {
            return LocalTime.parse(time, TIME_FORMAT);
        } catch(DateTimeParseException e){
            throw new IllegalArgumentException("Formato de hora inválido. Use HH:mm.");
        }
    }
}