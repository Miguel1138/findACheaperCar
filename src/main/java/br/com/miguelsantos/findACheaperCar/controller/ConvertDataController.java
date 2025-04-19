package br.com.miguelsantos.findACheaperCar.controller;

import br.com.miguelsantos.findACheaperCar.model.VehicleBrandData;
import br.com.miguelsantos.findACheaperCar.model.VehicleModelData;
import br.com.miguelsantos.findACheaperCar.service.ConvertData;

import java.util.List;

public class ConvertDataController {

    private final ConvertData converter = new ConvertData();

    public VehicleModelData convertDataFrom(String json) {
        return converter.convertDataFrom(json, VehicleModelData.class);
    }

    public List<VehicleBrandData> convertListFrom(String json) {
        return converter.convertListFrom(json, VehicleBrandData.class);
    }

}
