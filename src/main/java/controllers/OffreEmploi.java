package controllers;

import entities.recrutments;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import services.ServiceRecrutements;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class OffreEmploi {

    @FXML
    private Label back;

    @FXML
    private Button backp;

    @FXML
    private Label next;

    @FXML
    private ListView<String> recrutmentListView;

    private final ServiceRecrutements ps = new ServiceRecrutements();

    @FXML
    void afficherOE(ActionEvent event) {
        try {
            List<recrutments> recrutments1 = ps.afficher();

            // Clear any existing items in the ListView
            recrutmentListView.getItems().clear();

            // Add each Formation object to the ListView
            for (recrutments recrutments2 : recrutments1) {
                recrutmentListView.getItems().add(recrutments2.toString());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }





    @FXML
    void backp(ActionEvent event) {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/ajouterR.fxml"));
        try {
            Parent root = loader1.load();
            AjouterR controller = loader1.getController();
            next.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void location(ActionEvent event) {

    }

    @FXML
    void postuler(ActionEvent event) {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/apply.fxml"));
        try {
            Parent root = loader1.load();
            Apply controller = loader1.getController();
            back.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
