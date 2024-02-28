
package tn.esprit.gestiondonation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InterfacePrincipale {

    @FXML
    void giveAction(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tn/esprit/gestiondonation/AjouterGive.fxml"));
        try {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(new Scene(fxmlLoader.load()));
            currentStage.setTitle("Ajouter Give");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void requestAction(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tn/esprit/gestiondonation/AjouterRequest.fxml"));
        try {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(new Scene(fxmlLoader.load()));
            currentStage.setTitle("Ajouter Request");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }}

