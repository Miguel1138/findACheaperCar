package br.com.miguelsantos.findACheaperCar.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record VehicleBrandData(
        @JsonAlias("codigo") int id,
        @JsonAlias("nome") String name
) {

    @Override
    public String toString() {
        return "Nome da Marca: " + name + "\nid: " + id;
    }

}
