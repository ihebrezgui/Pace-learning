package com.example.guser.controller;

import com.example.guser.models.Code_promo;
import com.example.guser.models.Utilisateur;
import com.example.guser.services.CodeService;
import com.example.guser.services.UtilisateurService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class SupprimerCode {
    @FXML
    private TextField id_promoTF;

    @FXML
    void supprimerCode(ActionEvent event) {
        CodeService codeservice = new CodeService();
        Code_promo codePromo = new Code_promo();
        codePromo.setId_promo(Integer.parseInt(id_promoTF.getText()));

        try {
            codeservice.supprimer(codePromo.getId_promo());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("Code supprimé");
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


