package controllers;

import entities.partnerships;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.ServicePartnerships;

import java.io.IOException;
import java.sql.SQLException;

public class ModifierP {

    @FXML
    private TextField NomP;

    @FXML
    private Button back1;

    @FXML
    private TextField choix;

    @FXML
    private TextField domaine;

    @FXML
    private TextField idP;

    private final ServicePartnerships ps = new ServicePartnerships();

    @FXML
    void back1(ActionEvent event) {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/partnerships.fxml"));
        try {
            Parent root = loader1.load();
            Partnerships controller = loader1.getController();
            choix.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void modifierP(ActionEvent actionEvent) {
        try {
            // Create a new Categorie object with the updated name
            partnerships updatedpartnership = new partnerships(Integer.parseInt(idP.getText()),NomP.getText(),domaine.getText(),Integer.parseInt(choix.getText()));
            // Call the modifier method in your ServiceCategorie class
            ps.modifier(updatedpartnership);
            // Refresh the ListView
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            // Handle the exception appropriately
        }
    }

}

