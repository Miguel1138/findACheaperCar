package br.com.miguelsantos.findACheaperCar.controller;

import br.com.miguelsantos.findACheaperCar.model.VehicleBrandData;
import br.com.miguelsantos.findACheaperCar.model.VehicleModelData;
import br.com.miguelsantos.findACheaperCar.service.ApiConsumer;
import br.com.miguelsantos.findACheaperCar.utils.ApiAddressBuilder;
import br.com.miguelsantos.findACheaperCar.utils.ErrorMessages;

import java.util.List;

public class VehicleController {
    private final ApiConsumer apiConsumer = new ApiConsumer();
    private final ApiAddressBuilder addressBuilder = new ApiAddressBuilder();

    public String getVehicleBrandAddress(int type) {
        if(!isInteger(type)) return ErrorMessages.DEFAULT_MESSAGE;
        
        String response = apiConsumer.obtainData(addressBuilder.getBrandsNamesByVehicle(type));

        return response.contains(ErrorMessages.BAD_RESPONSE)
                ? ErrorMessages.DEFAULT_MESSAGE
                : response;
    }

    public String getVehicleModelAddress(int id) {
        if (!isInteger(id)) return ErrorMessages.DEFAULT_MESSAGE;

        String response = apiConsumer.obtainData(addressBuilder.getModelsNamesByVehicle(id));
        return response.contains(ErrorMessages.BAD_RESPONSE)
                ? ErrorMessages.DEFAULT_MESSAGE
                : response;
    }

    public String getVehicleYearAddress(int id) {
        if (!isInteger(id)) return ErrorMessages.DEFAULT_MESSAGE;

        String response = apiConsumer.obtainData(addressBuilder.getVehicleDateBy(id));
        return response.contains(ErrorMessages.BAD_RESPONSE)
                ? ErrorMessages.DEFAULT_MESSAGE
                : response;
    }

    public String getVehicleInfoBy(String id) {
        return !id.isEmpty() || !id.isBlank() ?
                apiConsumer.obtainData(addressBuilder.getVehicleInfoBy(id)) :
                ErrorMessages.DEFAULT_MESSAGE;
    }

    private boolean isInteger(Object obj) {
        if (obj == null) {
            return false;
        }
        return obj instanceof Integer;
    }

    public VehicleModelData filterByBrand(String brand, VehicleModelData modelData) throws NullPointerException {
        if (brand == null || brand.isBlank() || modelData == null) {
            throw new NullPointerException();
        }

        if (modelData.models().isEmpty()) {
            throw new NullPointerException(ErrorMessages.EMPTY_LIST);
        }

        List<VehicleBrandData> filteredModels = modelData.models().stream()
                .filter(vehicleBrandData -> vehicleBrandData.name().toLowerCase().startsWith(brand))
                .toList();

        return new VehicleModelData(filteredModels);
    }
}
