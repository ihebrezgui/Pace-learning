package tn.esprit.project.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import tn.esprit.project.models.Events;
import tn.esprit.project.services.EventService;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class ModifierEvent {

    @FXML
    private TextField idTF;

    @FXML
    private TextField nomTF;

    @FXML
    private TextField dateTF;

    @FXML
    private TextField Place_numTF;

    @FXML
    private TextField DescriptionTF;

    public void ModifierEvent(ActionEvent actionEvent) {



        EventService eventService = new EventService();
        Events event = new Events () ;
        event.setId_event(Integer.parseInt(idTF.getText()));
        event.setNom(nomTF.getText());
        event.setDate_event(Date.valueOf(dateTF.getText()));
        event.setNbr_place(Integer.parseInt(Place_numTF.getText()));
        event.setDescription(DescriptionTF.getText());


        try {
            eventService.modifier(event);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("Event modifié");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

}

