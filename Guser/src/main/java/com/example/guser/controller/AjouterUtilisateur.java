package com.example.guser.controller;

import com.example.guser.models.Utilisateur;
import com.example.guser.services.UtilisateurService;
import com.example.guser.test.mainFX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;


public class AjouterUtilisateur {


    @FXML
    private DatePicker date_naisTF;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField nomTF;

    @FXML
    private TextField num_telTF;

    @FXML
    private TextField prenomTF;
    @FXML
    private ChoiceBox<Utilisateur.Role> roleCB;

    @FXML
    private ChoiceBox <String> sexeTF;

    @FXML
    void afficherUtilisateur(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(mainFX.class.getResource("/com/example/guser/AfficherUtilisateur.fxml"));
        Parent root = fxmlLoader.load();

        // Create the scene
        Scene scene = new Scene(root);

        // Create a new stage
        Stage stage = new Stage();
        stage.setTitle("Afficher Utilisateur");
        stage.setScene(scene);

        // Show the stage
        stage.show();


    }

    public void ajouterUtilisateur(ActionEvent actionEvent) {
        UtilisateurService utilisateurService = new UtilisateurService();
        Utilisateur utilisateur = new Utilisateur();


        // Validate and set the nom field
        String nom = nomTF.getText();
        if (isValidNom(nom)) {
            utilisateur.setNom(nom);
        } else {
            showErrorAlert("Invalid nom");
            return; // Stop further processing
        }
        String sexe = sexeTF.getValue(); // Assuming ChoiceBox<String> for gender
        if (isValidSexe(sexe)) {
            utilisateur.setSexe(sexe);
        } else {
            showErrorAlert("Invalid gender");
            return; // Stop further processing
        }


        // Validate and set the prenom field
        String prenom = prenomTF.getText();
        if (isValidPrenom(prenom)) {
            utilisateur.setPrenom(prenom);
        } else {
            showErrorAlert("Invalid prenom");
            return; // Stop further processing
        }

        // Validate and set the date_nais field
        try {
            LocalDate dateNais = date_naisTF.getValue();
            if (dateNais == null) {
                showErrorAlert("Please select a date of birth");
                return;
            }

            utilisateur.setDate_nais(Date.valueOf(dateNais));
        } catch (DateTimeException e) {
            showErrorAlert("Invalid date format");
            return; // Stop further processing
        }
        // Validate and set the num_tel field
        String numTel = num_telTF.getText();
        if (isValidNumTel(numTel)) {
            utilisateur.setNum_tel(Integer.parseInt(numTel));
        } else {
            showErrorAlert("Invalid num_tel");
            return; // Stop further processing
        }

        // Validate and set the email field
        String email = emailTF.getText();
        if (isValidEmail(email)) {
            utilisateur.setEmail(email);
        } else {
            showErrorAlert("Invalid email");
            return; // Stop further processing
        }
    }

    // Validation method for nom field
    private boolean isValidNom(String nom) {
        return nom.matches("[a-zA-Z]+");
    }

    // Validation method for prenom field
    private boolean isValidPrenom(String prenom) {
        return prenom.matches("[a-zA-Z]+");
    }

    // Validation method for num_tel field
    private boolean isValidNumTel(String numTel) {
        return numTel.matches("\\d+");
    }

    // Validation method for email field
    private boolean isValidEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }


    // Validation method for sexe field
    private boolean isValidSexe(String sexe) {
        return sexe != null && (sexe.equals("M") || sexe.equals("F"));
    }


    // Helper method to show error alert
    private void showErrorAlert(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }
    @FXML
    void initialize() {
        roleCB.getItems().setAll(Utilisateur.Role.values());

    }


}
