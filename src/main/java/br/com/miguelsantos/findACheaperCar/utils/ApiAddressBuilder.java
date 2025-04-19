package br.com.miguelsantos.findACheaperCar.utils;

import static br.com.miguelsantos.findACheaperCar.utils.VehicleType.*;

public class ApiAddressBuilder {
    private final String BASE_URL = "https://parallelum.com.br/fipe/api/v1/";
    private final String BRAND = "/marcas/";
    private final String MODELS = "/modelos/";
    private final String YEARS = "/anos";
    private String address;

    public String getBrandsNamesByVehicle(int type) {
        address = switch (type) {
            case 1 -> BASE_URL + CAR + BRAND;
            case 2 -> BASE_URL + MOTORCYCLE + BRAND;
            case 3 -> BASE_URL + TRUCK + BRAND;
            default -> throw new IllegalStateException("Valor Inválido: " + type);
        };

        return address;
    }

    public String getModelsNamesByVehicle(int id) {
        address += id + MODELS;
        return address;
    }

    public String getVehicleDateBy(int id) {
        address += id + YEARS;
        return address;
    }

}
