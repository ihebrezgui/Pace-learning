package tn.esprit.project.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import tn.esprit.project.models.Events;
import tn.esprit.project.models.Planning;
import tn.esprit.project.services.EventService;
import tn.esprit.project.services.PlanningService;

import java.sql.Date;
import java.sql.SQLException;

public class AjouterPlanning {
    @FXML
    private TextField idFX;

    @FXML
    private TextField dateFX;

    @FXML
    private TextField lieuTF;

    @FXML
    private TextField titreTF;

    @FXML
    void ajouterPlanning(ActionEvent event) {
        PlanningService planningService = new PlanningService();
        Planning planning = new Planning () ;
        planning.setId_event(Integer.parseInt(idFX.getText()));
        planning.setTitre(titreTF.getText());
        planning.setLieu(lieuTF.getText());
        planning.setDate(Date.valueOf(dateFX.getText()));



        try {
            planningService.ajouter(planning);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("Planning ajoutée");
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
