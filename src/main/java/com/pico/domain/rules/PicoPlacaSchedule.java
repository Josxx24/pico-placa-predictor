package com.pico.domain.rules;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PicoPlacaSchedule {

    // Horarios oficiales
    private static final LocalTime MORNING_START = LocalTime.of(7, 0);
    private static final LocalTime MORNING_END = LocalTime.of(9, 30);

    private static final LocalTime AFTERNOON_START = LocalTime.of(16, 0);
    private static final LocalTime AFTERNOON_END = LocalTime.of(19, 30);

    // Restricción por día
    private static final Map<DayOfWeek, Set<Integer>> RESTRICTION_MAP;

    static {
        Map<DayOfWeek, Set<Integer>> m = new HashMap<>();
        m.put(DayOfWeek.MONDAY, Set.of(1, 2));
        m.put(DayOfWeek.TUESDAY, Set.of(3, 4));
        m.put(DayOfWeek.WEDNESDAY, Set.of(5, 6));
        m.put(DayOfWeek.THURSDAY, Set.of(7, 8));
        m.put(DayOfWeek.FRIDAY, Set.of(9, 0));

        RESTRICTION_MAP = Collections.unmodifiableMap(m);
    }

    public boolean isRestrictedHour(LocalTime time) {
        if (time == null) return false;

        boolean isMorning = !time.isBefore(MORNING_START) && !time.isAfter(MORNING_END);
        boolean isAfternoon = !time.isBefore(AFTERNOON_START) && !time.isAfter(AFTERNOON_END);

        return isMorning || isAfternoon;
    }

    public Set<Integer> getRestrictedDigitsByDay(DayOfWeek day) {
        if (day == null) return Collections.emptySet();
        return RESTRICTION_MAP.getOrDefault(day, Collections.emptySet());
    }

    public boolean isPlateRestricted(int lastDigit, LocalDate date, LocalTime time) {
        if (date == null || time == null) return false;

        Set<Integer> restrictedDigits = getRestrictedDigitsByDay(date.getDayOfWeek());

        // Si el día no tiene restricciones (fines de semana)
        if (restrictedDigits.isEmpty()) return false;

        // Si la hora no está dentro del horario restringido
        if (!isRestrictedHour(time)) return false;

        // Si el último dígito coincide con los restringidos
        return restrictedDigits.contains(lastDigit);
    }
}
