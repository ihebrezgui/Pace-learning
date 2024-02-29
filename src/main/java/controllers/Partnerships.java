package controllers;

import entities.partnerships;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.ServicePartnerships;

import java.io.IOException;
import java.sql.SQLException;

public class Partnerships {
    @FXML
    private TextField NomP;

    @FXML
    private Button afficherP;

    @FXML
    private TextField choix;

    @FXML
    private TextField domaine;

    @FXML
    private Button AccedezE;

    private final ServicePartnerships ps = new ServicePartnerships();



    @FXML
    void AjouterP(ActionEvent event) {
        try {
            ps.ajouter(new partnerships(NomP.getText(),domaine.getText(),Integer.parseInt(choix.getText())));
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Success");
            successAlert.setHeaderText("Partner added successfully");
            successAlert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText((e.getMessage()));
            alert.showAndWait();
        }
    }

    @FXML
    void afficherp(ActionEvent event) {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/afficherP.fxml"));
        try {
            Parent root = loader1.load();
            AfficherP controller = loader1.getController();
            choix.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void AccedezE(ActionEvent event) {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/ajouterE.fxml"));
        try {
            Parent root = loader1.load();
            AjouterE controller = loader1.getController();
            choix.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
