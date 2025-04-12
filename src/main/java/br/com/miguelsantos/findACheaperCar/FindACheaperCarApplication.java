package br.com.miguelsantos.findACheaperCar;

import br.com.miguelsantos.findACheaperCar.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FindACheaperCarApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FindACheaperCarApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal();
        principal.showMenu();
    }

}
