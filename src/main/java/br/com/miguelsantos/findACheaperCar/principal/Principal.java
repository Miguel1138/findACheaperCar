package br.com.miguelsantos.findACheaperCar.principal;

import br.com.miguelsantos.findACheaperCar.controller.ConvertDataController;
import br.com.miguelsantos.findACheaperCar.controller.VehicleController;
import br.com.miguelsantos.findACheaperCar.handlers.VehicleInputHandler;
import br.com.miguelsantos.findACheaperCar.model.VehicleBrandData;
import br.com.miguelsantos.findACheaperCar.model.VehicleModelData;
import br.com.miguelsantos.findACheaperCar.utils.ErrorMessages;

import java.util.Comparator;
import java.util.Scanner;

public class Principal {
    private final Scanner scanner = new Scanner(System.in);
    private final VehicleController vehicleController = new VehicleController();
    private final ConvertDataController convertDataController = new ConvertDataController();
    private int type;
    private int threshold = 0;

    public void showMenu() {
        showOpenDialog();
        showBrandlistDialog();
        showModelListDialog();
        showVehiclesByYearDialog();
    }

    // TODO: mostrar lista de anos para esoclha.
    // TODO: mostrar valor do veículo no ano solicitado.
    private void showVehiclesByYearDialog() {
        System.out.println("Insira o Id do veículo que deseja: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println(vehicleController.getVehicleYearAddress(id));
    }

    private void showModelListDialog() {
        System.out.println("Informe o código da marca desejada para consulta");
        // O(n^2)
        do {
            try {
                int id = Integer.parseInt(scanner.nextLine());
                String json = vehicleController.getVehicleModelAddress(id);
                while (json.contains(ErrorMessages.DEFAULT_MESSAGE)) {
                    System.out.println("Erro ao obter os modelos de veículos. Por favor, tente novamente.");
                    id = Integer.parseInt(scanner.nextLine());
                    json = vehicleController.getVehicleModelAddress(id);
                }

                VehicleModelData modelData = convertDataController.convertDataFrom(json);
                modelData.models().stream()
                        .sorted(Comparator.comparing(VehicleBrandData::id))
                        .forEach(System.out::println);
                break;

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número válido.");
                threshold++;
            }
        } while (threshold < 3);

        if (threshold == 3) {
            System.out.println(ErrorMessages.TOO_MANY_TRIES);
            System.exit(0);
        } else threshold = 0;

    }

    private void showBrandlistDialog() {
        String json = vehicleController.getVehicleBrandAddress(type);
        var brands = convertDataController.convertListFrom(json);
        brands.stream()
                .sorted(Comparator.comparing(VehicleBrandData::id))
                .forEach(System.out::println);
    }

    private void showOpenDialog() {
        VehicleInputHandler handler = new VehicleInputHandler();

        System.out.println("*************************************\n" +
                "BEM VINDO A FIND A CHEaPER VEHICLE! \n" +
                "*************************************\n" +
                "ESCOLHA O TIPO DE VEÍCULO QUE PROCURA: \n" +
                "- Carros\n- Motos\n- Caminhoes");
        do {
            type = handler.checkValidOption(scanner.nextLine());
            threshold++;
            if (threshold == 3) {
                System.out.println(ErrorMessages.TOO_MANY_TRIES);
                System.exit(0);
            }
        } while (type == -1);
        threshold = 0;
    }

    //TODO : Pegar o valor do Json e montar na classe Vehicle e lista de veículos para amostragem.
    // Apresentar a lista de veículos para o usuário escolher qual véiculo quer ter mais detalhes.

}
