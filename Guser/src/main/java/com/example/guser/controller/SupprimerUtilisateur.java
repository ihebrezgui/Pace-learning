package com.example.guser.controller;

import com.example.guser.models.Utilisateur;
import com.example.guser.services.UtilisateurService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class SupprimerUtilisateur {
    @FXML
    private TextField idTF;

    @FXML
    void supprimerUtilisateur(ActionEvent event) {
        UtilisateurService utilisateurService = new UtilisateurService();
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(Integer.parseInt(idTF.getText()));

        try {
            utilisateurService.supprimer(utilisateur.getId());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("Utilisateur supprimé");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }


    @FXML
    void initialize() {

    }
}
