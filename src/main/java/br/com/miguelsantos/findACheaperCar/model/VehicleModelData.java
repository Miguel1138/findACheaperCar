package br.com.miguelsantos.findACheaperCar.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VehicleModelData(
       @JsonAlias("modelos") List<VehicleBrandData> models
) {
}
