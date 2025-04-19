package br.com.miguelsantos.findACheaperCar.controller;

import br.com.miguelsantos.findACheaperCar.service.ApiConsumer;
import br.com.miguelsantos.findACheaperCar.utils.ApiAddressBuilder;
import br.com.miguelsantos.findACheaperCar.utils.ErrorMessages;

public class VehicleController {

    private final ApiConsumer apiConsumer = new ApiConsumer();
    private final ApiAddressBuilder addressBuilder = new ApiAddressBuilder();

    public String getVehicleBrandAddress(int type) {
        return isInteger(type)
                ? apiConsumer.obtainData(addressBuilder.getBrandsNamesByVehicle(type))
                : ErrorMessages.DEFAULT_MESSAGE;
    }

    public String getVehicleModelAddress(int id) {
        return isInteger(id)
                ? apiConsumer.obtainData(addressBuilder.getModelsNamesByVehicle(id))
                : ErrorMessages.DEFAULT_MESSAGE;
    }

    public String getVehicleYearAddress(int id) {
        return isInteger(id)
                ? apiConsumer.obtainData(addressBuilder.getVehicleDateBy(id))
                : ErrorMessages.DEFAULT_MESSAGE;
    }

    private boolean isInteger(Object obj) {
        if (obj == null) {
            return false;
        }
        return obj instanceof Integer;
    }

}
