package controllers;

import entities.recrutments;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.ServiceRecrutements;

import java.io.IOException;
import java.sql.SQLException;

public class ModifierR {



    @FXML
    private Button back1;

    @FXML
    private TextField discription;

    @FXML
    private TextField poste;

    @FXML
    private TextField idR;

    @FXML
    private TextField salaire;

    private final ServiceRecrutements ps = new ServiceRecrutements();

    @FXML
    void back1(ActionEvent event) {
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
    void modifierR(ActionEvent event) {
        try {
            // Create a new Categorie object with the updated name
            recrutments updatedrecructment = new recrutments(Integer.parseInt(idR.getText()),poste.getText(),discription.getText(),Integer.parseInt(salaire.getText()));
            // Call the modifier method in your ServiceCategorie class
            ps.modifier(updatedrecructment);
            // Refresh the ListView
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            // Handle the exception appropriately
        }
    }

}

