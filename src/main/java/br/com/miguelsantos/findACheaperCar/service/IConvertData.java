package br.com.miguelsantos.findACheaperCar.service;

import java.util.List;

public interface IConvertData {

    <T> T convertDataFrom(String Json, Class<T> clazz);

    <T> List<T> convertListFrom(String Json, Class<T> clazz);

}
