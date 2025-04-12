package br.com.miguelsantos.findACheaperCar.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.*;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

class ApiConsumerTest {

    private MockWebServer mockWebServer;
    private ApiConsumer apiConsumer;

    @BeforeEach
    void setUp() throws IOException {
        // Inicia o servidor HTTP simulado
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        apiConsumer = new ApiConsumer();
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    @DisplayName("Quando o servidor retorna 200 e um corpo, obtainData devolve o corpo")
    void testObtainDataSuccess() {
        // Arrange: preparamos o MockWebServer para retornar "Hello, world!"
        String expectedBody = "Hello, world!";
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(expectedBody));

        String url = mockWebServer.url("/test").toString();

        // Act: chamamos o método
        String actualBody = apiConsumer.obtainData(url);

        // Assert: validamos que o corpo retornado é o mesmo
        assertEquals(expectedBody, actualBody);
    }

    @Test
    @DisplayName("Quando a conexão falha, obtainData lança RuntimeException")
    void testObtainDataConnectionFailure() {
        // Arrange: usamos uma porta onde não há servidor ouvindo
        String badUrl = "http://localhost:9999/nonexistent";

        // Act & Assert: esperamos RuntimeException
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            apiConsumer.obtainData(badUrl);
        });
        // Opcional: garantir que a causa seja IOException
        assertTrue(ex.getCause() instanceof IOException);
    }
}
