package br.com.miguelsantos.findACheaperCar.handlers;

public class VehicleInputHandler {

    public int checkValidOption(String vehicle) {
        int vehicleTypeIndex = checkType(vehicle.toLowerCase());
        if (vehicleTypeIndex == -1) {
            System.out.println("Opção inválida! Digite Novamente:\n");
            sleepRetry();
            return vehicleTypeIndex;
        }
        return vehicleTypeIndex;
    }

    private int checkType(String vehicle) {
        if (vehicle.contains("car")) {
            return 1;
        } else if (vehicle.contains("mot")) {
            return 2;
        } else if (vehicle.contains("cam")) {
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
