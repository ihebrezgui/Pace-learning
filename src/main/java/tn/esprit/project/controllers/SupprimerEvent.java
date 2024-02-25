package tn.esprit.project.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import tn.esprit.project.models.Events;
import tn.esprit.project.services.EventService;

import java.sql.SQLException;

public class SupprimerEvent {

    @FXML
    private TextField idTF;

    @FXML
    void supprimerEvent(ActionEvent event) {
        EventService eventService = new EventService();
        Events events = new Events () ;
        events.setId_event(Integer.parseInt(idTF.getText()));

        try {
            eventService.supprimer(events.getId_event());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("Event supprimé");
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
