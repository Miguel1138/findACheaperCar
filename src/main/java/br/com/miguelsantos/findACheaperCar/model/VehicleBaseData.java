package br.com.miguelsantos.findACheaperCar.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record VehicleBaseData(
        @JsonAlias("codigo") String id,
        @JsonAlias("nome") String name
) {

    @Override
    public String toString() {
        return "Cod: " + id + " Descricao: " + name;
    }

}
