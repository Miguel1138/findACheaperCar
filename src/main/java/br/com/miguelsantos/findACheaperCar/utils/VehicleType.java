package br.com.miguelsantos.findACheaperCar.utils;

public enum VehicleType {
    CAR("carros"),
    MOTORCYCLE("motos"),
    TRUCK("caminhoes");

    private String type;
    VehicleType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
g
}
