package org.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 900);

        stage.setMinWidth(1000);
        stage.setMinHeight(625);

        stage.setTitle("Sistema de Gestión de Empleados");
        stage.setScene(scene);
        stage.show();
    }
}
