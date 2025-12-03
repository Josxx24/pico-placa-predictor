package domain.models;


public class Vehicle {
    private final Plate plate;

    public Vehicle(Plate plate){
        if(plate==null){
            throw new IllegalArgumentException("Plate cannot be null");
        }
        this.plate=plate;
    }

    public Plate getPlate(){
        return plate;
    }

    @Override
    public String toString(){
        return "Vehicle{plate=" + plate.getValue() + "}";
    }
}
