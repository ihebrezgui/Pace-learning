package com.example.guser.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class mainFX extends Application {
    @Override
    public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(mainFX.class.getResource("/com/example/guser/Authentifier.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 480);
        stage.setTitle("FX");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}