package br.com.miguelsantos.findACheaperCar.controller;

public class ApiAddressBuilder {
    private static final String BASE_URL = "https://parallelum.com.br/fipe/api/v1/";
    private static final String BRAND = "/marcas";

    public static String buildVehicleTypeAddress(int type) {
        return switch (type) {
            case 1 -> BASE_URL + "carros" + BRAND;
            case 2 -> BASE_URL + "motos" + BRAND;
            case 3 -> BASE_URL + "caminhoes" + BRAND;
            default -> throw new IllegalStateException("Valor Inválido: " + type);
        };
    }
}
