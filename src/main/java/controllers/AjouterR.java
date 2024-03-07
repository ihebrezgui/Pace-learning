package controllers;

import entities.recrutments;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.ServiceRecrutements;

import java.io.IOException;
import java.sql.SQLException;

public class AjouterR {

    @FXML
    private Button AfficherR;

    @FXML
    private Button AjouterR;

    @FXML
    private TextField discription;

    @FXML
    private TextField poste;

    @FXML
    private TextField salaire;


    private final ServiceRecrutements ps = new ServiceRecrutements();

    private boolean isEmpty(TextField textField) {
        return textField.getText().trim().isEmpty();
    }
    private boolean isAgeValid(TextField ageField) {
        try {
            int age = Integer.parseInt(ageField.getText());
            return age >= 600;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    @FXML
    void AfficherR(ActionEvent event) {
         FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/afficherR.fxml"));
        try {
            Parent root = loader1.load();
            AfficherR controller = loader1.getController();
            salaire.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void AjouterR(ActionEvent event) {
        if (isEmpty(poste) || !isAgeValid(salaire) || isEmpty(salaire) || isEmpty(discription) ) {
            // Show an error message if an input field is empty
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Tous les champs de saisie sont obligatoires et le salaire doit être d'au moins 600");
            alert.showAndWait();
        } else {
            try {
                ps.ajouter(new recrutments(poste.getText(), discription.getText(), Integer.parseInt(salaire.getText())));
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setHeaderText("offre de emploie ajouté avec succées");
                successAlert.showAndWait();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                alert.setContentText((e.getMessage()));
                alert.showAndWait();
            }
        }
    }

}
