package tn.esprit.devformation.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import tn.esprit.devformation.models.Formation;
import tn.esprit.devformation.services.FormationService;


import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class AddFormation {
    @FXML

    private TextField IdFormation;

    @FXML
    private TextField typeF;



        @FXML
        void add(ActionEvent event) {
            FormationService Fs = new FormationService();
            Formation F = new Formation();
            F.setIdFormation(Integer.parseInt(IdFormation.getText()));
            F.setTypeF(typeF.getText());
            try {
                Fs.add(F);
                Alert Alert = new Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
                Alert.setTitle("Success");
                Alert.setContentText("Formation added");
                Alert.showAndWait();

            } catch (Exception e) {
                Alert Alert = new Alert(javafx.scene.control.Alert.AlertType.ERROR);
                Alert.setTitle("Erreur");
                Alert.setContentText(e.getMessage());
                Alert.showAndWait();
                System.err.println((e.getMessage()));
                throw new RuntimeException(e);
            }
        }

        @FXML
        void getAll(ActionEvent event) {

        }
}
