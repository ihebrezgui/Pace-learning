package tn.esprit.project.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import tn.esprit.project.models.Planning;
import tn.esprit.project.services.PlanningService;

import java.sql.Date;
import java.sql.SQLException;

public class ModifierPlanning {
    @FXML
    private TextField dateFX;

    @FXML
    private TextField idTF;

    @FXML
    private TextField lieuTF;

    @FXML
    private TextField titreTF;

    public void modifierPlanning (ActionEvent actionEvent) {



        PlanningService planningService = new PlanningService();
        Planning planning = new Planning () ;
        planning.setId_planning(Integer.parseInt(idTF.getText()));
        planning.setTitre(titreTF.getText());
        planning.setDate(Date.valueOf(dateFX.getText()));
        planning.setLieu(String.valueOf(lieuTF.getText()));


        try {
            planningService.modifier(planning);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("Planning modifié");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
