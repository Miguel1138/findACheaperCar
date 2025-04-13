package br.com.miguelsantos.findACheaperCar.controller;

import br.com.miguelsantos.findACheaperCar.service.ApiConsumer;

public class ApiAddressBuilder {
    private final String BASE_URL = "https://parallelum.com.br/fipe/api/v1/";
    private final String BRAND = "/marcas";
    private final String MODELS = "/modelos";
    private final String YEARS = "/anos";
    private final ApiConsumer consumer = new ApiConsumer();

    public String getBrandsNamesByVehicle(int type) {
        String json = switch (type) {
            case 1 -> BASE_URL + "carros" + BRAND;
            case 2 -> BASE_URL + "motos" + BRAND;
            case 3 -> BASE_URL + "caminhoes" + BRAND;
            default -> throw new IllegalStateException("Valor Inválido: " + type);
        };
        return consumer.obtainData(json);
    }


}
