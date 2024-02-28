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



        // Validate input for event ID
        if (!isValidId(idTF.getText())) {
            showAlert("Erreur", "ID d'événement invalide. Veuillez saisir un nombre entier positif.");
            return;
        }

        // Validate input for event name
        if (!isValidText(nomTF.getText())) {
            showAlert("Erreur", "Nom d'événement invalide. Veuillez saisir un texte non vide.");
            return;
        }

        // Validate input for event date
        if (!isValidDate(dateTF.getText())) {
            showAlert("Erreur", "Format de date invalide. Utilisez le format YYYY-MM-DD.");
            return;
        }

        // Validate input for place number
        if (!isValidNumber(Place_numTF.getText())) {
            showAlert("Erreur", "Nombre de places invalide. Veuillez saisir un nombre entier positif.");
            return;
        }

        // Validate input for event description (assuming it can be any text)
        if (!isValidText(DescriptionTF.getText())) {
            showAlert("Erreur", "Description invalide. Veuillez saisir un texte non vide.");
            return;
        }

        // All input is valid, proceed to modify the event
        EventService eventService = new EventService();
        Events event = new Events();
        event.setId_event(Integer.parseInt(idTF.getText()));
        event.setNom(nomTF.getText());
        event.setDate_event(Date.valueOf(dateTF.getText()));
        event.setNbr_place(Integer.parseInt(Place_numTF.getText()));
        event.setDescription(DescriptionTF.getText());

        try {
            eventService.modifier(event);
            showAlert("Success", "Event modifié");
        } catch (SQLException e) {
            showAlert("Erreur", e.getMessage());
        }
    }

    private boolean isValidId(String id) {
        try {
            int eventId = Integer.parseInt(id);
            return eventId > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidText(String text) {
        return !text.trim().isEmpty();
    }

    private boolean isValidDate(String date) {
        try {
            Date.valueOf(date);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private boolean isValidNumber(String number) {
        try {
            int num = Integer.parseInt(number);
            return num > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }


}

