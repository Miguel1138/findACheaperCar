package br.com.miguelsantos.findACheaperCar.handlers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VehicleInputHandlerTest {

    private VehicleInputHandler handler;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        handler = new VehicleInputHandler();
        // Redireciona o System.out para capturar as mensagens
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        // Restaura o System.out original
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Quando a entrada é '1', deve retornar 1 sem mensagem de erro")
    void testValidInputLowerBoundary() {
        int result = handler.checkValidOption("1");
        assertEquals(1, result);
        assertTrue(outContent.toString().isEmpty(),
                "Não deve imprimir mensagem para entrada válida");
    }

    @Test
    @DisplayName("Quando a entrada é '3', deve retornar 3 sem mensagem de erro")
    void testValidInputUpperBoundary() {
        int result = handler.checkValidOption("3");
        assertEquals(3, result);
        assertTrue(outContent.toString().isEmpty(),
                "Não deve imprimir mensagem para entrada válida");
    }

    @Test
    @DisplayName("Quando a entrada for número fora do intervalo, retorna -1 e imprime mensagem de opção inválida")
    void testOutOfRangeInput() {
        int result = handler.checkValidOption("5");
        assertEquals(-1, result);
        String printed = outContent.toString();
        assertTrue(printed.contains("Opção inválida! Digite 1, 2 ou 3"),
                "Deve alertar sobre opção inválida");
    }

    @Test
    @DisplayName("Quando a entrada não for número, retorna -1 e imprime mensagem de comando inválido")
    void testNonNumericInput() {
        int result = handler.checkValidOption("abc");
        assertEquals(-1, result);
        String printed = outContent.toString();
        assertTrue(printed.contains("Comando inválido! Digite novamente"),
                "Deve alertar sobre comando inválido");
    }

    @Test
    @DisplayName("Verifica que sleepRetry é chamado em caso de erro (medindo tempo mínimo)")
    void testSleepRetryDelay() {
        long start = System.currentTimeMillis();
        handler.checkValidOption("xyz");  // vai retornar -1 e dormir ~1000 ms
        long elapsed = System.currentTimeMillis() - start;
        assertTrue(elapsed >= 900,
                "O método deve pausar por pelo menos ~1 segundo antes de retornar");
    }

}

