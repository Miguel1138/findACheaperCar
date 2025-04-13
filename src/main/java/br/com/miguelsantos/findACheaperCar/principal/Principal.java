package br.com.miguelsantos.findACheaperCar.principal;

import br.com.miguelsantos.findACheaperCar.handlers.VehicleInputHandler;
import br.com.miguelsantos.findACheaperCar.model.VehicleBrandData;
import br.com.miguelsantos.findACheaperCar.model.VehicleModelData;
import br.com.miguelsantos.findACheaperCar.service.ApiConsumer;
import br.com.miguelsantos.findACheaperCar.service.ConvertData;
import br.com.miguelsantos.findACheaperCar.utils.ApiAddressBuilder;

import java.util.Comparator;
import java.util.Scanner;

public class Principal {
    private final ApiAddressBuilder addressBuilder = new ApiAddressBuilder();
    private final Scanner scanner = new Scanner(System.in);
    private final ConvertData convertData = new ConvertData();
    private final ApiConsumer apiConsumer = new ApiConsumer();
    private int type;

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
        System.out.println(getVehicleYearAddress(id));
    }

    private void showModelListDialog() {
        System.out.println("Informe o código da marca desejada para consulta");
        int id = Integer.parseInt(scanner.nextLine());
        String json = getVehicleModelAddress(id);
        VehicleModelData modelData = convertData.convertDataFrom(json, VehicleModelData.class);
        modelData.models().stream()
                .sorted(Comparator.comparing(VehicleBrandData::id))
                .forEach(System.out::println);
    }

    private void showBrandlistDialog() {
        String json = getVehicleBrandAddress(type);
        var brands = convertData.convertListFrom(json, VehicleBrandData.class);
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
        } while (type == -1);
    }

    //TODO : Pegar o valor do Json e montar na classe Vehicle e lista de veículos para amostragem.
    // Apresentar a lista de veículos para o usuário escolher qual véiculo quer ter mais detalhes.

    public String getVehicleBrandAddress(int type) {
        return apiConsumer.obtainData(addressBuilder.getBrandsNamesByVehicle(type));
    }

    public String getVehicleModelAddress(int id) {
        return apiConsumer.obtainData(addressBuilder.getModelsNamesByVehicle(id));
    }

    public String getVehicleYearAddress(int id) {
        return apiConsumer.obtainData(addressBuilder.getVehicleDateBy(id));
    }

}
