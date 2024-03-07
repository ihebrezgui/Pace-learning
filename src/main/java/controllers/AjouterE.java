package controllers;

import entities.enseignant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.ServiceEnseignant;

import java.io.IOException;
import java.sql.SQLException;


public class AjouterE {

    @FXML
    private Button AfficherE;

    @FXML
    private TextField age;

    @FXML
    private TextField competence;

    @FXML
    private TextField email;

    @FXML
    private TextField langue;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField matier;

    @FXML
    private Button AccedezP;


    private final ServiceEnseignant ps = new ServiceEnseignant();


    private boolean isEmpty(TextField textField) {
        return textField.getText().trim().isEmpty();
    }
    private boolean isAgeValid(TextField ageField) {
        try {
            int age = Integer.parseInt(ageField.getText());
            return age >= 18;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    @FXML
    void AjouterE(ActionEvent event) {
        if (isEmpty(age) || !isAgeValid(age) || isEmpty(email) || isEmpty(matier) || isEmpty(nom) || isEmpty(prenom) || isEmpty(competence) || isEmpty(langue)) {
            // Show an error message if an input field is empty
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Tous les champs de saisie sont obligatoires et l'âge doit être d'au moins 18 ans");
            alert.showAndWait();
        } else {
            try {
                ps.ajouter(new enseignant(Integer.parseInt(age.getText()), email.getText(), matier.getText(), nom.getText(), prenom.getText(), competence.getText(), langue.getText()));
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setHeaderText("Enseignant ajouté avec succées");
                successAlert.showAndWait();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                alert.setContentText((e.getMessage()));
                alert.showAndWait();
            }
        }
    }
    @FXML
    void AfficherE(ActionEvent event) {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/afficherE.fxml"));
        try {
            Parent root = loader1.load();
            AfficherE controller = loader1.getController();
            matier.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void AccedezP(ActionEvent event) {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/partnerships.fxml"));
        try {
            Parent root = loader1.load();
            Partnerships controller = loader1.getController();
            competence.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
