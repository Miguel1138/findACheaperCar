package br.com.miguelsantos.findACheaperCar.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.net.ConnectException;
import java.util.List;

public class ConvertData implements IConvertData {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T convertDataFrom(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> convertListFrom(String json, Class<T> clazz) {
        CollectionType collection = mapper.getTypeFactory()
                .constructCollectionType(List.class, clazz);
        try {
            return mapper.readValue(json, collection);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
