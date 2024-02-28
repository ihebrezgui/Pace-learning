package com.example.guser.controller;

import com.example.guser.models.Code_promo;
import com.example.guser.services.CodeService;

import com.example.guser.test.mainFX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class AjouterCode {
    @FXML
    private TextField activeTF;

    @FXML
    private TextField codeTF;

    @FXML
    private TextField date_expirationTF;

    @FXML
    private TextField idUserTF;

    @FXML
    void afficherCode(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(mainFX.class.getResource("/com/example/guser/AfficherCode.fxml"));
        Parent root = fxmlLoader.load();

        // Create the scene
        Scene scene = new Scene(root);

        // Create a new stage
        Stage stage = new Stage();
        stage.setTitle("Afficher les codes");
        stage.setScene(scene);

        // Show the stage
        stage.show();


    }

    @FXML
    void ajouterCode(ActionEvent event) {
        CodeService codeservice = new CodeService();
        Code_promo codePromo = new Code_promo();

        // Validate codeTF
        String code = codeTF.getText().trim();
        if (code.isEmpty()) {
            showAlert("Erreur", "Code invalide", "Veuillez entrer un code valide.");
            return;
        }
        codePromo.setCode(code);

        // Validate date_expirationTF
        String dateExpirationText = date_expirationTF.getText().trim();
        if (dateExpirationText.isEmpty()) {
            showAlert("Erreur", "Date d'expiration invalide", "Veuillez entrer une date d'expiration valide.");
            return;
        }
        try {
            Date dateExpiration = Date.valueOf(dateExpirationText);
            codePromo.setDate_expiration(dateExpiration);
        } catch (IllegalArgumentException e) {
            showAlert("Erreur", "Date d'expiration invalide", "Veuillez entrer une date d'expiration valide au format YYYY-MM-DD.");
            return;
        }

        // Validate activeTF
        String activeText = activeTF.getText().trim();
        if (!activeText.matches("\\d+")) {
            showAlert("Erreur", "Active invalide", "Veuillez entrer une valeur numérique pour le champ Active.");
            return;
        }
        codePromo.setActive(Integer.parseInt(activeText));

        // Validate idUserTF
        String idUserText = idUserTF.getText().trim();
        if (!idUserText.matches("\\d+")) {
            showAlert("Erreur", "ID utilisateur invalide", "Veuillez entrer une valeur numérique pour le champ ID utilisateur.");
            return;
        }
        codePromo.setIdUser(Integer.parseInt(idUserText));

        try {
            codeservice.ajouter(codePromo);
            showAlert("Success", "Code ajouté", "Code ajouté avec succès.");
        } catch (SQLException e) {
            showAlert("Erreur", "Erreur lors de l'ajout du code", e.getMessage());
        }
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();

    }

    @FXML
    void initialize() {


    }

}
