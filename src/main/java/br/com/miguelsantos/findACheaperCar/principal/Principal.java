package br.com.miguelsantos.findACheaperCar.principal;

import br.com.miguelsantos.findACheaperCar.controller.ApiAddressBuilder;
import br.com.miguelsantos.findACheaperCar.handlers.VehicleInputHandler;
import br.com.miguelsantos.findACheaperCar.service.ApiConsumer;

import java.util.Scanner;

public class Principal {
    private final Scanner scanner = new Scanner(System.in);
    private final ApiConsumer consumer = new ApiConsumer();

    public void showMenu() {
        showOpenDialog();
    }

    private void showOpenDialog() {
        int type;
        VehicleInputHandler handler = new VehicleInputHandler();
        System.out.println("*************************************\n" +
                "BEM VINDO A FIND A CHEaPER VEHICLE! \n" +
                "*************************************\n" +
                "ESCOLHA O TIPO DE VEÍCULO QUE PROCURA: \n" +
                "1 - Carros\n2 - Motos\n3 - Caminhoes");
        do {
            type = handler.checkIntInRange(scanner.nextLine());
        } while (type == -1);
        vehicleTypeChoice(type);
    }

    //TODO : Pegar o valor do Json e montar na classe Vehicle e lista de veículos para amostragem.
    // Apresentar a lista de veículos para o usuário escolher qual véiculo quer ter mais detalhes.

    public void vehicleTypeChoice(int type) {
        String addres = ApiAddressBuilder.buildVehicleTypeAddress(type);
        String json = consumer.obtainData(addres);
        // Retorna json com os campos coidog e nome das marcas do tipo de veículo.
        System.out.println(json);
    }

}
