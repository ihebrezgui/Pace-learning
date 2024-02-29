package controllers;

import entities.enseignant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.ServiceEnseignant;

import java.io.IOException;
import java.sql.SQLException;

public class ModifierE {
    @FXML
    private Button BACKAFFICHE;

    @FXML
    private TextField idE;

    @FXML
    private TextField ageM;

    @FXML
    private TextField competenceM;

    @FXML
    private TextField emailM;

    @FXML
    private TextField langueM;

    @FXML
    private TextField matierM;

    @FXML
    private TextField nomM;

    @FXML
    private TextField prenomM;

    @FXML
    private Label next;

    private final ServiceEnseignant ps = new ServiceEnseignant();
    @FXML
    void ModifierE(ActionEvent actionEvent) {

        try {
            // Create a new Categorie object with the updated name
            enseignant updatedenseignant = new enseignant(Integer.parseInt(idE.getText()),Integer.parseInt(ageM.getText()),emailM.getText(),matierM.getText(),nomM.getText(), prenomM.getText(),competenceM.getText(),langueM.getText());
            // Call the modifier method in your ServiceCategorie class
            ps.modifier(updatedenseignant);
            // Refresh the ListView
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            // Handle the exception appropriately
        }

    }

    @FXML
    void BACKAFFICHE(ActionEvent event) {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/afficherE.fxml"));
        try {
            Parent root = loader1.load();
            AfficherE controller = loader1.getController();
            next.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }    }

}
