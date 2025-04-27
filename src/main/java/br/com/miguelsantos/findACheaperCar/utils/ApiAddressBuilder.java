package br.com.miguelsantos.findACheaperCar.utils;

import static br.com.miguelsantos.findACheaperCar.utils.VehicleType.*;

public class ApiAddressBuilder {
    private final String BASE_URL = "https://parallelum.com.br/fipe/api/v1/";
    private final String BRAND = "/marcas/";
    private final String MODELS = "/modelos/";
    private final String YEARS = "/anos/";
    private String address;

    public String getBrandsNamesByVehicle(int type) {
        resetAddress();
        address = switch (type) {
            case 1 -> BASE_URL + CAR + BRAND;
            case 2 -> BASE_URL + MOTORCYCLE + BRAND;
            case 3 -> BASE_URL + TRUCK + BRAND;
            default -> throw new IllegalStateException("Valor Inválido: " + type);
        };

        return address;
    }

    public String getModelsNamesByVehicle(int id) {
        if (address.contains(MODELS)) address = removeDuplicatedPatternFrom(MODELS);

        address += id + MODELS;
        return address;

    }

    public String getVehicleDateBy(int id) {
        if (address.contains(YEARS)) address = removeDuplicatedPatternFrom(YEARS);

        address += id + YEARS;
        return address;
    }

    public String getVehicleInfoBy(String id) {
        String cleanAddress = address.replaceAll("/anos/.*$", "/anos/" + id);
        return cleanAddress;
    }

    private void resetAddress() {
        address = "";
    }

    private String removeDuplicatedPatternFrom(String duplicateStr) {
        int duplicateStringIndex = address.indexOf(duplicateStr);
        int duplicateIDIndex = address.lastIndexOf("/", duplicateStringIndex - 1) + 1;
        return address.substring(0, duplicateIDIndex);
    }

}
