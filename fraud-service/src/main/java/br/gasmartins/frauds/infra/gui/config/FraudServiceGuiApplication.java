package br.gasmartins.frauds.infra.gui.config;

import br.gasmartins.frauds.FraudServiceApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class FraudServiceGuiApplication extends Application {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() throws Exception {
        this.applicationContext = SpringApplication.run(FraudServiceApplication.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        applicationContext.close();
        Platform.exit();
    }
}
