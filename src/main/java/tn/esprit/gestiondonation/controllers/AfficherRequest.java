package tn.esprit.gestiondonation.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import tn.esprit.gestiondonation.models.Request;
import tn.esprit.gestiondonation.service.RequestService;
import tn.esprit.gestiondonation.test.HelloApplication;

public class AfficherRequest {

   @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Request> listview;



        @FXML
        void ajouterAction(ActionEvent event) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tn/esprit/gestiondonation/AjouterRequest.fxml"));
            try {
                Stage currentStage = (Stage) listview.getScene().getWindow();
                currentStage.setScene(new Scene(fxmlLoader.load()));
                currentStage.setTitle("Ajouter Request");
            } catch (IOException e) {
                afficherErreur("Erreur", e.getMessage());
            }
        }


    private void afficherErreur(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void modifierAction(ActionEvent event) {
        // Get the selected request from the ListView
        Request selectedRequest = listview.getSelectionModel().getSelectedItem();

        if (selectedRequest == null) {
            // No request is selected, display an error message or handle it appropriately
            afficherErreur("Erreur", "Veuillez sélectionner une demande à modifier.");
            return;
        }

        // Load the "Modifier" interface
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tn/esprit/gestiondonation/ModifierRequest.fxml"));
        try {
            Parent modifierRequestRoot = fxmlLoader.load();

            // Pass the selected request to the ModifierRequestController
            ModifierRequest modifierRequest = fxmlLoader.getController();
            modifierRequest.setSelectedRequest(selectedRequest);

            // Create a new stage for the ModifierRequest scene
            Stage modifierRequestStage = new Stage();
            modifierRequestStage.setTitle("Modifier Request");
            modifierRequestStage.setScene(new Scene(modifierRequestRoot));

            // Close the current stage (AfficherRequest interface)
            Stage currentStage = (Stage) listview.getScene().getWindow();
            currentStage.close();

            // Show the ModifierRequest stage
            modifierRequestStage.show();
        } catch (IOException e) {
            afficherErreur("Erreur", e.getMessage());
        }
    }

    @FXML
    void supprimerAction(ActionEvent event) {
        Request selectedRequest = listview.getSelectionModel().getSelectedItem();

        if (selectedRequest == null) {
            afficherErreur("Erreur", "Veuillez sélectionner une demande à supprimer.");
            return;
        }

        RequestService requestService = new RequestService();

        try {
            requestService.supprimer(selectedRequest.getIdDonataire());
            afficherConfirmation("Demande supprimée avec succès");

            listview.getItems().remove(selectedRequest);
        } catch (SQLException e) {
            afficherErreur("Erreur", e.getMessage());
        }
    }
    private void afficherConfirmation(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Succès");
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
        public void initialize() throws SQLException {

            RequestService requestService = new RequestService();

            List<Request> requests = requestService.recuperer();


            listview.setItems(FXCollections.observableArrayList(requests));
        }
    }

