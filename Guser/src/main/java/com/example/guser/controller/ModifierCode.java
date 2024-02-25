package com.example.guser.controller;

import com.example.guser.models.Code_promo;
import com.example.guser.services.CodeService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.sql.SQLException;

public class ModifierCode {
    @FXML
    private TextField activeTF;

    @FXML
    private TextField codeTF;

    @FXML
    private TextField date_expirationTF;

    @FXML
    private TextField idUserTF;

    @FXML
    private TextField id_promoTF;

    @FXML
    void modifierCode(ActionEvent event) {
        CodeService codeservice = new CodeService();
        Code_promo codePromo = new Code_promo();

        // Validate id_promoTF
        String idPromoText = id_promoTF.getText().trim();
        if (!idPromoText.matches("\\d+")) {
            showAlert("Erreur", "ID promo invalide", "Veuillez entrer une valeur numérique pour le champ ID promo.");
            return;
        }
        codePromo.setId_promo(Integer.parseInt(idPromoText));

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
            showAlert("Erreur", "Statut actif invalide", "Veuillez entrer une valeur numérique pour le champ Statut actif.");
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
            codeservice.modifier(codePromo);
            showAlert("Success", "Code modifié", "Code modifié avec succès.");
        } catch (SQLException e) {
            showAlert("Erreur", "Erreur lors de la modification du code", e.getMessage());
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
