package com.example.guser.controller;

import com.example.guser.test.mainFX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class InterfacePrincipale {


    @FXML
    void afficherU(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(mainFX.class.getResource("/com/example/guser/AfficherUtilisateur.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Afficher Utilisateur");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void ajouterU(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(mainFX.class.getResource("/com/example/guser/AjouterUtilisateur.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Ajouter Utilisateur");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void modifierU(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(mainFX.class.getResource("/com/example/guser/ModifierUtilisateur.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Modifier Utilisateur");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void supprimerU(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(mainFX.class.getResource("/com/example/guser/SupprimerUtilisateur.fxml"));
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
