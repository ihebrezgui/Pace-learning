package com.example.guser.controller;

import com.example.guser.services.UtilisateurService;
import com.example.guser.test.mainFX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import com.example.guser.models.Utilisateur;
import javafx.stage.Stage;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Properties;


public class AjouterUtilisateur {


    @FXML
    private TextField date_naisTF;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField nomTF;

    @FXML
    private TextField num_telTF;

    @FXML
    private TextField prenomTF;

    @FXML
    private TextField sexeTF;

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
            Date dateNais = Date.valueOf(date_naisTF.getText());
            utilisateur.setDate_nais(dateNais);
        } catch (IllegalArgumentException e) {
            showErrorAlert("Invalid date_nais");
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



        // Validate and set the sexe field
        String sexe = sexeTF.getText();
        if (isValidSexe(sexe)) {
            utilisateur.setSexe(sexe);
        } else {
            showErrorAlert("Invalid sexe");
            return; // Stop further processing
        }

        try {
            utilisateurService.ajouter(utilisateur);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("Utilisateur ajoutée");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    // Validation method for nom field
    private boolean isValidNom(String nom) {
        // Add your validation rules for the "nom" field
        // For example, check if it contains only alphabets
        return nom.matches("[a-zA-Z]+");
    }

    // Validation method for prenom field
    private boolean isValidPrenom(String prenom) {
        // Add your validation rules for the "prenom" field
        // For example, check if it contains only alphabets
        return prenom.matches("[a-zA-Z]+");
    }

    // Validation method for num_tel field
    private boolean isValidNumTel(String numTel) {
        // Add your validation rules for the "num_tel" field
        // For example, check if it contains only digits
        return numTel.matches("\\d+");
    }

    // Validation method for email field
    private boolean isValidEmail(String email) {
        // Add your validation rules for the "email" field
        // For example, use a regular expression for basic email validation
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    // Validation method for sexe field
    private boolean isValidSexe(String sexe) {

        return sexe.equals("M") || sexe.equals("F");
    }

    // Helper method to show error alert
    private void showErrorAlert(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

}
