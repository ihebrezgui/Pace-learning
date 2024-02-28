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
import tn.esprit.gestiondonation.models.Give;
import tn.esprit.gestiondonation.service.GiveService;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class AjouterGive {

    @FXML
    private TextField dateTF;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField methodeTF;

    @FXML
    private TextField montantTF;

    @FXML
    private TextField numeroTF;

    @FXML
    private TextField professionTF;

    @FXML
    private TextField statutTF;



    @FXML
    void afficherAction(ActionEvent event)  {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tn/esprit/gestiondonation/AfficherGive.fxml"));
        try {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(new Scene(fxmlLoader.load()));
            currentStage.setTitle("Afficher Give");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    void ajouterAction(ActionEvent event) {
        String statut = statutTF.getText();
        String montantText = montantTF.getText();
        String dateText = dateTF.getText();
        String email = emailTF.getText();
        String numeroText = numeroTF.getText();
        String profession = professionTF.getText();
        String methodePaiement = methodeTF.getText();

        // Vérifier si tous les champs requis sont remplis
        if (montantText.isEmpty() || dateText.isEmpty() || email.isEmpty()
                || numeroText.isEmpty() || profession.isEmpty() || methodePaiement.isEmpty()) {
            afficherErreur("Erreur de saisie", "Veuillez remplir tous les champs.");
            return;
        }

        try {
            int montant = Integer.parseInt(montantText);
            Date dateNaissance = Date.valueOf(dateText);
            int numeroTelephone = Integer.parseInt(numeroText);

            GiveService giveService = new GiveService();
            Give giveDonation = new Give();

            if (statut.isEmpty()) {
                giveDonation.setStatut("N/A"); // Valeur par défaut si le statut est vide
            } else {
                giveDonation.setStatut(statut);
            }

            giveDonation.setMontant(montant);
            giveDonation.setDate_de_naissance(dateNaissance);
            giveDonation.setEmail(email);
            giveDonation.setNum_tlf(numeroTelephone);
            giveDonation.setProfession(profession);
            giveDonation.setMéthode_paiement(methodePaiement);

            giveService.ajouter(giveDonation);
            afficherConfirmation("Donation attribuée avec succès");
        } catch (NumberFormatException e) {
            afficherErreur("Erreur de saisie", "Veuillez saisir des valeurs numériques valides pour les champs montant et numéro de téléphone.");
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


