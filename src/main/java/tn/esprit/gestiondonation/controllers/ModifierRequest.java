package tn.esprit.gestiondonation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.gestiondonation.models.Request;
import tn.esprit.gestiondonation.service.RequestService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ModifierRequest {

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
        }}

    private Request selectedRequest;



    public void setSelectedRequest(Request selectedRequest) {
        this.selectedRequest = selectedRequest;
        // Assuming you have appropriate getter methods in the Request class
        formationTF.setText(selectedRequest.getFormation_souhaitée());
        emailTF.setText(selectedRequest.getEmail());

        // Format the date using SimpleDateFormat
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(selectedRequest.getDate_limite());
        datelimiteTF.setText(formattedDate);

        lieuTF.setText(selectedRequest.getLieu_de_résidence());
        descriptionTF.setText(selectedRequest.getDescription());
    }

    @FXML
    void modifierAction(ActionEvent event) {
        String modifiedFormation = formationTF.getText();
        String modifiedEmail = emailTF.getText();
        String modifiedDatelimite = datelimiteTF.getText();
        String modifiedLieu = lieuTF.getText();
        String modifiedDescription = descriptionTF.getText();

        RequestService requestService = new RequestService();
        Request request = selectedRequest; // Use the selectedRequest object

        // Check if any required field is empty
        if (modifiedFormation.isEmpty() || modifiedDatelimite.isEmpty() || modifiedLieu.isEmpty() || modifiedDescription.isEmpty()) {
            afficherErreur("Erreur de saisie", "Veuillez remplir tous les champs.");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(modifiedDatelimite);
            request.setDate_limite(date);
        } catch (ParseException e) {
            afficherErreur("Erreur de saisie", "Le format de la date est incorrect. Utilisez le format YYYY-MM-DD.");
            return;
        }

        // Check if email is empty
        if (modifiedEmail.isEmpty()) {
            afficherErreur("Erreur de saisie", "Veuillez saisir une adresse e-mail.");
            return;
        }

        request.setFormation_souhaitée(modifiedFormation);
        request.setEmail(modifiedEmail);
        request.setLieu_de_résidence(modifiedLieu);
        request.setDescription(modifiedDescription);

        try {
            requestService.modifier(request);
            afficherConfirmation("Demande modifiée avec succès");
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
}
