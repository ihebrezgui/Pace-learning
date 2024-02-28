package tn.esprit.gestiondonation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.gestiondonation.models.Request;
import tn.esprit.gestiondonation.service.RequestService;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import tn.esprit.gestiondonation.test.HelloApplication;

public class AjouterRequest {

    @FXML
    private TextField datelimiteTF;

    @FXML
    private TextField descriptionTF;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField formationTF;

    @FXML
    private TextField lieuTF;


    @FXML
    void afficherAction(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tn/esprit/gestiondonation/AfficherRequest.fxml"));
        try {
            Parent afficherRequestRoot = fxmlLoader.load();

            // Create a new stage for the AfficherRequest scene
            Stage afficherRequestStage = new Stage();
            afficherRequestStage.setTitle("Afficher Request");
            afficherRequestStage.setScene(new Scene(afficherRequestRoot));

            // Close the current stage (AjouterRequest interface)
            Stage currentStage = (Stage) datelimiteTF.getScene().getWindow();
            currentStage.close();

            // Show the AfficherRequest stage
            afficherRequestStage.show();
        } catch (IOException e) {
            afficherErreur("Erreur", e.getMessage());
        }

    }

    @FXML
    void ajouterAction(ActionEvent event) {
        String formation = formationTF.getText();
        String email = emailTF.getText();
        String dateStr = datelimiteTF.getText();
        String lieu = lieuTF.getText();
        String description = descriptionTF.getText();

        // Vérification des champs vides
        if (formation.isEmpty() || dateStr.isEmpty() || lieu.isEmpty() || description.isEmpty()) {
            afficherErreur("Erreur de saisie", "Veuillez remplir tous les champs.");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            afficherErreur("Erreur de saisie", "Le format de la date est incorrect. Utilisez le format YYYY-MM-DD.");
            return;
        }

        // Check if email is empty
        if (email.isEmpty()) {
            afficherErreur("Erreur de saisie", "Veuillez saisir une adresse e-mail.");
            return;
        }

        Request request = new Request(formation, email, date, lieu, description);
        RequestService requestService = new RequestService();

        try {
            requestService.ajouter(request);
            afficherConfirmation("Requête ajoutée avec succès");
        } catch (SQLException e) {
            afficherErreur("Erreur", e.getMessage());
        }
    }


    private void afficherErreur(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void afficherConfirmation(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Succès");
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void precedentAction(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tn/esprit/gestiondonation/InterfacePrincipale.fxml"));
        try {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(new Scene(fxmlLoader.load()));
            currentStage.setTitle("Interface Principale");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




