package com.pico.domain.models;

public class Plate {
    private final String value;

    private static final String PLATE_PATTERN = "^[A-Z]{3}-\\d{4}$";

    public Plate(String value){
        if (value == null || value.isBlank()){
            throw new IllegalArgumentException("Plate cannot be null or empty");
        }

        if (!value.matches(PLATE_PATTERN)){
            throw new IllegalArgumentException("Invalid plate format. Expected format ABC-1234");
        }

        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public int getLastDigit(){
        return Character.getNumericValue(value.charAt(value.length()-1));
    }

    @Override
    public String toString(){
        return value;
    }
}
