package br.com.miguelsantos.findACheaperCar.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiAddressBuilderTest {

    // Constantes esperadas para facilitar leitura nos asserts
    private static final String BASE_URL = "https://parallelum.com.br/fipe/api/v1/";
    private static final String BRAND    = "/marcas";

    @Nested
    @DisplayName("📌 Casos válidos para buildVehicleTypeAddress")
    class ValidCases {

        @Test
        @DisplayName("Tipo 1 → URL de carros")
        void whenTypeIs1_thenReturnCarrosUrl() {
            String expected = BASE_URL + "carros" + BRAND;
            String actual   = ApiAddressBuilder.buildVehicleTypeAddress(1);
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Tipo 2 → URL de motos")
        void whenTypeIs2_thenReturnMotosUrl() {
            String expected = BASE_URL + "motos" + BRAND;
            String actual   = ApiAddressBuilder.buildVehicleTypeAddress(2);
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Tipo 3 → URL de caminhões")
        void whenTypeIs3_thenReturnCaminhoesUrl() {
            String expected = BASE_URL + "caminhoes" + BRAND;
            String actual   = ApiAddressBuilder.buildVehicleTypeAddress(3);
            assertEquals(expected, actual);
        }
    }

    @Nested
    @DisplayName("🚨 Casos inválidos para buildVehicleTypeAddress")
    class InvalidCases {

        @Test
        @DisplayName("Tipo 0 → lança IllegalStateException")
        void whenTypeIs0_thenThrowException() {
            IllegalStateException ex = assertThrows(
                    IllegalStateException.class,
                    () -> ApiAddressBuilder.buildVehicleTypeAddress(0)
            );
            assertTrue(ex.getMessage().contains("Valor Inválido: 0"));
        }

        @Test
        @DisplayName("Tipo negativo → lança IllegalStateException")
        void whenTypeIsNegative_thenThrowException() {
            int invalid = -1;
            IllegalStateException ex = assertThrows(
                    IllegalStateException.class,
                    () -> ApiAddressBuilder.buildVehicleTypeAddress(invalid)
            );
            assertTrue(ex.getMessage().contains("Valor Inválido: " + invalid));
        }

        @Test
        @DisplayName("Tipo maior que 3 → lança IllegalStateException")
        void whenTypeIsGreaterThan3_thenThrowException() {
            int invalid = 10;
            IllegalStateException ex = assertThrows(
                    IllegalStateException.class,
                    () -> ApiAddressBuilder.buildVehicleTypeAddress(invalid)
            );
            assertTrue(ex.getMessage().contains("Valor Inválido: " + invalid));
        }
    }
}

