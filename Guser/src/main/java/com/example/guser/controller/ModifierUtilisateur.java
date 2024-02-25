package com.example.guser.controller;

import com.example.guser.models.Utilisateur;
import com.example.guser.services.UtilisateurService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.sql.SQLException;

public class ModifierUtilisateur {
    @FXML
    private TextField idTF;

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
    void modifierUtilisateur(ActionEvent event) {
        UtilisateurService utilisateurService = new UtilisateurService();
        Utilisateur utilisateur = new Utilisateur();

        // Validate idTF
        String idText = idTF.getText().trim();
        if (!idText.matches("\\d+")) {
            showAlert("Erreur", "ID utilisateur invalide", "Veuillez entrer une valeur numérique pour le champ ID utilisateur.");
            return;
        }
        utilisateur.setId(Integer.parseInt(idText));

        // Validate nomTF
        String nom = nomTF.getText().trim();
        if (nom.isEmpty()) {
            showAlert("Erreur", "Nom invalide", "Veuillez entrer un nom valide.");
            return;
        }
        utilisateur.setNom(nom);

        // Validate prenomTF
        String prenom = prenomTF.getText().trim();
        if (prenom.isEmpty()) {
            showAlert("Erreur", "Prénom invalide", "Veuillez entrer un prénom valide.");
            return;
        }
        utilisateur.setPrenom(prenom);

        // Validate date_naisTF
        String dateNaisText = date_naisTF.getText().trim();
        if (dateNaisText.isEmpty()) {
            showAlert("Erreur", "Date de naissance invalide", "Veuillez entrer une date de naissance valide.");
            return;
        }
        try {
            Date dateNais = Date.valueOf(dateNaisText);
            utilisateur.setDate_nais(dateNais);
        } catch (IllegalArgumentException e) {
            showAlert("Erreur", "Date de naissance invalide", "Veuillez entrer une date de naissance valide au format YYYY-MM-DD.");
            return;
        }

        // Validate num_telTF
        String numTelText = num_telTF.getText().trim();
        if (!numTelText.matches("\\d+")) {
            showAlert("Erreur", "Numéro de téléphone invalide", "Veuillez entrer une valeur numérique pour le champ Numéro de téléphone.");
            return;
        }
        utilisateur.setNum_tel(Integer.parseInt(numTelText));

        // Validate emailTF
        String email = emailTF.getText().trim();
        // You can add more sophisticated email validation logic if needed
        if (email.isEmpty()) {
            showAlert("Erreur", "Email invalide", "Veuillez entrer une adresse email valide.");
            return;
        }
        utilisateur.setEmail(email);

        // Validate sexeTF
        String sexe = sexeTF.getText().trim();
        if (sexe.isEmpty()) {
            showAlert("Erreur", "Sexe invalide", "Veuillez entrer une valeur pour le champ Sexe.");
            return;
        }
        utilisateur.setSexe(sexe);

        try {
            utilisateurService.modifier(utilisateur);
            showAlert("Success", "Utilisateur modifié", "Utilisateur modifié avec succès.");
        } catch (SQLException e) {
            showAlert("Erreur", "Erreur lors de la modification de l'utilisateur", e.getMessage());
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
