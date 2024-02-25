package com.example.guser.controller;

import com.example.guser.test.mainFX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class InterfaceCode {
    @FXML
    void AfficherC(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(mainFX.class.getResource("/com/example/guser/AfficherCode.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Afficher Utilisateur");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void ajouterC(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(mainFX.class.getResource("/com/example/guser/AjouterCode.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Ajouter Utilisateur");
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    void modifierC(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(mainFX.class.getResource("/com/example/guser/ModifierCode.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Modifier Utilisateur");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void supprimerC(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(mainFX.class.getResource("/com/example/guser/SupprimerCode.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Supprimer Utilisateur");
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    void initialize() {

    }

}
