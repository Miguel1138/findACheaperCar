package br.com.miguelsantos.findACheaperCar.service;

public interface IConvertData {

    <T> T convertDataFrom(String Json, Class<T> clazz);

}
