package br.com.miguelsantos.findACheaperCar.handlers;

public class VehicleInputHandler  {

    public int checkValidOption(String input) {
        int num = checkOption(input.toLowerCase());
        if (num == -1) {
            System.out.println("Opção inválida! Digite Novamente:\n");
            sleepRetry();
            return num;
        }

        return num;
    }

    private int checkOption(String input) {
        if (input.contains("carr")) {
            return 1;
        } else if (input.contains("mot")) {
            return 2;
        } else if (input.contains("cam")) {
            return 3;
        }
        return -1;
    }

    // Adiciona delay para apresentar novamente a tela.
    private void sleepRetry() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
}
