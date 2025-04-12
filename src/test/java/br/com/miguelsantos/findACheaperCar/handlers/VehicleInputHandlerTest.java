package br.com.miguelsantos.findACheaperCar.handlers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import java.io.*;

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
        int result = handler.checkIntInRange("1");
        assertEquals(1, result);
        assertTrue(outContent.toString().isEmpty(),
                "Não deve imprimir mensagem para entrada válida");
    }

    @Test
    @DisplayName("Quando a entrada é '3', deve retornar 3 sem mensagem de erro")
    void testValidInputUpperBoundary() {
        int result = handler.checkIntInRange("3");
        assertEquals(3, result);
        assertTrue(outContent.toString().isEmpty(),
                "Não deve imprimir mensagem para entrada válida");
    }

    @Test
    @DisplayName("Quando a entrada for número fora do intervalo, retorna -1 e imprime mensagem de opção inválida")
    void testOutOfRangeInput() {
        int result = handler.checkIntInRange("5");
        assertEquals(-1, result);
        String printed = outContent.toString();
        assertTrue(printed.contains("Opção inválida! Digite 1, 2 ou 3"),
                "Deve alertar sobre opção inválida");
    }

    @Test
    @DisplayName("Quando a entrada não for número, retorna -1 e imprime mensagem de comando inválido")
    void testNonNumericInput() {
        int result = handler.checkIntInRange("abc");
        assertEquals(-1, result);
        String printed = outContent.toString();
        assertTrue(printed.contains("Comando inválido! Digite novamente"),
                "Deve alertar sobre comando inválido");
    }

    @Test
    @DisplayName("Verifica que sleepRetry é chamado em caso de erro (medindo tempo mínimo)")
    void testSleepRetryDelay() {
        long start = System.currentTimeMillis();
        handler.checkIntInRange("xyz");  // vai retornar -1 e dormir ~1000 ms
        long elapsed = System.currentTimeMillis() - start;
        assertTrue(elapsed >= 900,
                "O método deve pausar por pelo menos ~1 segundo antes de retornar");
    }

}

