package tn.esprit.gestiondonation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.gestiondonation.models.Give;
import tn.esprit.gestiondonation.models.Request;
import tn.esprit.gestiondonation.service.GiveService;
import tn.esprit.gestiondonation.service.RequestService;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
public class ModifierGive {

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
    void afficherAction(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tn/esprit/gestiondonation/AfficherGive.fxml"));
        try {
            Parent afficherRequestRoot = fxmlLoader.load();

            // Create a new stage for the AfficherRequest scene
            Stage afficherRequestStage = new Stage();
            afficherRequestStage.setTitle("Afficher Request");
            afficherRequestStage.setScene(new Scene(afficherRequestRoot));

            // Close the current stage (AjouterRequest interface)
            Stage currentStage = (Stage) dateTF.getScene().getWindow();
            currentStage.close();

            // Show the AfficherRequest stage
            afficherRequestStage.show();
        } catch (IOException e) {
            afficherErreur("Erreur", e.getMessage());
        }}


    private Give selectedGive;



        public void setSelectedGive(Give selectedGive) {
            this.selectedGive = selectedGive;
            // Assuming you have appropriate getter methods in the Give class
            dateTF.setText(selectedGive.getDate_de_naissance().toString());
            emailTF.setText(selectedGive.getEmail());
            methodeTF.setText(selectedGive.getMéthode_paiement());
            montantTF.setText(String.valueOf(selectedGive.getMontant()));
            numeroTF.setText(String.valueOf(selectedGive.getNum_tlf()));
            professionTF.setText(selectedGive.getProfession());
            statutTF.setText(selectedGive.getStatut());
        }

    @FXML
    void modifierAction(ActionEvent event) throws SQLException {
        String modifiedDate = dateTF.getText();
        String modifiedEmail = emailTF.getText();
        String modifiedMethode = methodeTF.getText();
        double modifiedMontant = Double.parseDouble(montantTF.getText());
        String modifiedNumero = numeroTF.getText();
        String modifiedProfession = professionTF.getText();
        String modifiedStatut = statutTF.getText();

        GiveService giveService = new GiveService();
        Give give = selectedGive;

        if (modifiedDate.isEmpty() || modifiedEmail.isEmpty() || modifiedMethode.isEmpty() || modifiedNumero.isEmpty()
                || modifiedProfession.isEmpty() || modifiedStatut.isEmpty()) {
            afficherErreur("Erreur de saisie", "Veuillez remplir tous les champs.");
            return;
        }

        give.setDate_de_naissance(modifiedDate);
        give.setEmail(modifiedEmail);
        give.setMéthode_paiement(modifiedMethode);
        give.setMontant((int) modifiedMontant);
        give.setNum_tlf(Integer.parseInt(modifiedNumero));
        give.setProfession(modifiedProfession);
        give.setStatut(modifiedStatut);


        giveService.modifier(give);

        try {
            giveService.modifier(give);
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
