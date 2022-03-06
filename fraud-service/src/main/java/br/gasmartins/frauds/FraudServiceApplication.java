package br.gasmartins.frauds;

import br.gasmartins.frauds.infra.gui.config.FraudServiceGuiApplication;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FraudServiceApplication {

	public static void main(String[] args) {
		Application.launch(FraudServiceGuiApplication.class, args);
	}

}
