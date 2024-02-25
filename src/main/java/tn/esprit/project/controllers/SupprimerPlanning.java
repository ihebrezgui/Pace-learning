package tn.esprit.project.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import tn.esprit.project.models.Planning;
import tn.esprit.project.services.PlanningService;

import java.sql.SQLException;

public class SupprimerPlanning {
    @FXML
    private TextField idTF;

    @FXML
    void supprimerPlanning(ActionEvent event) {
        PlanningService planningService = new PlanningService();
        Planning planning = new Planning () ;
        planning.setId_planning(Integer.parseInt(idTF.getText()));

        try {
            planningService.supprimer(planning.getId_planning());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("Planning supprimé");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

    @FXML
    void initialize() {

    }

}
