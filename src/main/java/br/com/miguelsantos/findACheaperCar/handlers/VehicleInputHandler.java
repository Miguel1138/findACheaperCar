package br.com.miguelsantos.findACheaperCar.handlers;

public class VehicleInputHandler {
    public int checkIntInRange(String input) {
        try {
            int num = Integer.parseInt(input);
            if (num < 1 || num > 3) {
                System.out.println("Opção inválida! Digite 1, 2 ou 3:\n");
                sleepRetry();
                return -1;
            }
            return num;
        } catch (NumberFormatException e) {
            System.out.println("Comando inválido! Digite novamente: \n");
            sleepRetry();
            return -1;
        }
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
