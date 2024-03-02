package com.example.guser.controller;

import com.example.guser.models.Utilisateur;
import com.example.guser.services.UtilisateurService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class Authentifier {

    @FXML
    private TextField emailTF;

    @FXML
    private TextField passTF;

    @FXML
    void FPass(ActionEvent event) {

    }

    @FXML
    void inscrire(ActionEvent event) {

    }

    @FXML
    void login(ActionEvent event) {
        String email = emailTF.getText();
        String password = passTF.getText();

        // Check if email and password are not empty (add additional validation if needed)
        if (!email.isEmpty() && !password.isEmpty()) {
            // Call your authentication service to check credentials
            UtilisateurService utilisateurService = new UtilisateurService();
            Utilisateur utilisateur = utilisateurService.authenticateUser(email, password);

            if (utilisateur != null) {
                showAlert("Login Successful!");
            } else {
                showAlert("Invalid email or password.");
            }
        } else {
            showAlert("Please enter both email and password.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Authentication Result");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void initialize() {

    }

}
