package br.com.miguelsantos.findACheaperCar.principal;

import br.com.miguelsantos.findACheaperCar.controller.ConvertDataController;
import br.com.miguelsantos.findACheaperCar.controller.VehicleController;
import br.com.miguelsantos.findACheaperCar.handlers.VehicleInputHandler;
import br.com.miguelsantos.findACheaperCar.model.Vehicle;
import br.com.miguelsantos.findACheaperCar.model.VehicleBaseData;
import br.com.miguelsantos.findACheaperCar.model.VehicleModelData;
import br.com.miguelsantos.findACheaperCar.utils.ErrorMessages;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private final Scanner scanner = new Scanner(System.in);
    private final VehicleController vehicleController = new VehicleController();
    private final ConvertDataController convertDataController = new ConvertDataController();

    private int type;
    private int threshold = 0;
    private VehicleModelData modelData;

    public void showMenu() {
        openDialog();
        showBrandlistDialog();
        openModelistDialog();
        showModelListSelectedDialog();
        showVehicleValuesByYearDialog();
    }

    private void showModelListSelectedDialog() {
        System.out.println("Insira o nome da marca de veículo que deseja: ");
        String brand = scanner.nextLine().toLowerCase();
        try {
            VehicleModelData filteredModels = vehicleController.filterByBrand(brand, modelData);
            if (filteredModels != null && !filteredModels.models().isEmpty()) {
                filteredModels.models().stream()
                        .sorted((a, b) -> {
                            try {
                                return Integer.compare(
                                        Integer.parseInt(a.id()),
                                        Integer.parseInt(b.id())
                                );
                            } catch (NumberFormatException e) {
                                return a.id().compareTo(b.id());
                            }
                        })
                        .forEach(System.out::println);
            } else {
                System.out.println("Não foi possível encontrar marca com o nome: " + brand);
            }
        } catch (NullPointerException e) {
            System.out.println("Erro ao processar dados: " + e.getMessage());
        }
    }

    private void showVehicleValuesByYearDialog() {
        System.out.println("Digite o cód1igo do modelo para consultar valores:");
        int id = Integer.parseInt(scanner.nextLine());
        String json = vehicleController.getVehicleYearAddress(id);
        List<VehicleBaseData> years = convertDataController.convertListFrom(json);
        List<Vehicle> vehicles = new ArrayList<>();

        for(int i = 0; i < years.size(); i++ ) {
            String vehicleAddress = vehicleController.getPerYearVehicleInfoBy(years.get(i).id());
            System.out.println(vehicleAddress);
            Vehicle vehicle  = convertDataController.convertVehicleFrom(vehicleAddress);
            vehicles.add(vehicle);
        }
        vehicles.forEach(System.out::println);

    }

    private void openModelistDialog() {
        System.out.println("Informe o código da marca para consulta:");
        do {
            try {
                int id = Integer.parseInt(scanner.nextLine());
                String json = vehicleController.getVehicleModelAddress(id);
                while (json.contains(ErrorMessages.DEFAULT_MESSAGE)) {
                    System.out.println("Erro ao obter os modelos de veículos. Por favor, tente novamente.");
                    id = Integer.parseInt(scanner.nextLine());
                    json = vehicleController.getVehicleModelAddress(id);
                    threshold++;
                    if(threshold == 3) {
                        finalizarPorExcessoTentativas();
                    }
                }
                modelData = convertDataController.convertDataFrom(json);
                modelData.models().stream()
                        .sorted(Comparator.comparing(VehicleBaseData::id))
                        .forEach(System.out::println);
                threshold = 0;
                break;

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número válido.");
                threshold++;
            }
        } while (threshold < 3);

        if (threshold == 3) {
           finalizarPorExcessoTentativas();
        } else threshold = 0;

    }

    private void showBrandlistDialog() {
        String json = vehicleController.getVehicleBrandAddress(type);
        List<VehicleBaseData> brands = convertDataController.convertListFrom(json);
        brands.stream()
                .sorted(Comparator.comparing(v -> Integer.parseInt(v.id())))
                .forEach(System.out::println);
    }

    private void openDialog() {
        VehicleInputHandler handler = new VehicleInputHandler();

        System.out.println("*************************************\n" +
                "BEM VINDO A FIND A CHEaPER VEHICLE! \n" +
                "*************************************\n" +
                "ESCOLHA O TIPO DE VEÍCULO QUE PROCURA: \n" +
                "- Carros\n- Motos\n- Caminhoes");
        System.out.println("Digite uma das opções para consultar valores:");
        do {
            type = handler.checkValidOption(scanner.nextLine());
            threshold++;
            if (threshold == 3) {
               finalizarPorExcessoTentativas();
            }
        } while (type == -1);
        threshold = 0;
    }
    //TODO : Pegar o valor do Json e montar na classe Vehicle e lista de veículos para amostragem.
    // Apresentar a lista de veículos para o usuário escolher qual véiculo quer ter mais detalhes.

    private void finalizarPorExcessoTentativas() {
        System.out.println(ErrorMessages.TOO_MANY_TRIES);
        System.exit(0);
    }


}
