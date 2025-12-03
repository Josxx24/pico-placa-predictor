package domain.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeInput {

    private final LocalDate date;
    private final LocalTime time;

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    public DateTimeInput(String dateString, String timeString) {
        if (dateString == null || dateString.isBlank()) {
            throw new IllegalArgumentException("Date cannot be null or empty.");
        }
        if (timeString == null || timeString.isBlank()) {
            throw new IllegalArgumentException("Time cannot be null or empty.");
        }

        try {
            this.date = LocalDate.parse(dateString, DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Date format must be dd-MM-yyyy");
        }

        try {
            this.time = LocalTime.parse(timeString, TIME_FORMAT);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Time format must be HH:mm");
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public int getDayOfWeek() {
        return date.getDayOfWeek().getValue(); // Monday = 1 ... Sunday = 7
    }
}
