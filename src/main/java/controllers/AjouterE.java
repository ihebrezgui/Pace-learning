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



    @FXML
     void AjouterE(ActionEvent event) {
        try {
            ps.ajouter(new enseignant(Integer.parseInt(age.getText()),email.getText(),matier.getText(),nom.getText(), prenom.getText(),competence.getText(),langue.getText()));
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Success");
            successAlert.setHeaderText("Enseignant added successfully");
            successAlert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText((e.getMessage()));
            alert.showAndWait();
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
