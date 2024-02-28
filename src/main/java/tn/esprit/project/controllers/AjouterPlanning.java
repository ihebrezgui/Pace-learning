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
        // Validate input for event ID
        if (!isValidId(idFX.getText())) {
            showAlert("Erreur", "ID d'événement invalide. Veuillez saisir un nombre entier positif.");
            return;
        }

        // Validate input for planning title
        if (!isValidText(titreTF.getText())) {
            showAlert("Erreur", "Titre de planning invalide. Veuillez saisir un texte non vide.");
            return;
        }

        // Validate input for planning lieu
        if (!isValidText(lieuTF.getText())) {
            showAlert("Erreur", "Lieu de planning invalide. Veuillez saisir un texte non vide.");
            return;
        }

        // Validate input for planning date
        if (!isValidDate(dateFX.getText())) {
            showAlert("Erreur", "Format de date invalide. Utilisez le format YYYY-MM-DD.");
            return;
        }

        // All input is valid, proceed to add the planning
        PlanningService planningService = new PlanningService();
        Planning planning = new Planning();
        planning.setId_event(Integer.parseInt(idFX.getText()));
        planning.setTitre(titreTF.getText());
        planning.setLieu(lieuTF.getText());
        planning.setDate(Date.valueOf(dateFX.getText()));

        try {
            planningService.ajouter(planning);
            showAlert("Success", "Planning ajouté");
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

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    }



