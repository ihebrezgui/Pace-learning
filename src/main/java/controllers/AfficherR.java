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

public class AfficherR {

    @FXML
    private Label back;

    @FXML
    private Button backp;

    @FXML
    private Label next;

    @FXML
    private Button afficherR;

    @FXML
    private ListView<String> recrutmentListView;

    private final ServiceRecrutements ps = new ServiceRecrutements();
    @FXML
    void afficherR(ActionEvent event) {


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
            back.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void modifierR(ActionEvent event) {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/modifierR.fxml"));
        try {
            Parent root = loader1.load();
            ModifierR controller = loader1.getController();
            next.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void supprimerR(ActionEvent actionEvent) {
        String selectedItem = recrutmentListView.getSelectionModel().getSelectedItem();

// Get the selected Formation object from the ListView
        if (selectedItem != null) {
            // Parse the ID from the selected item
            int id = parseIdFromSelectedItem(selectedItem);

            try {
                // Delete the Formation from the database
                ps.supprimer(new recrutments(id, null, null, 0)); // Create a temporary Formation object with only the ID
                // Refresh the ListView
                afficherR(actionEvent);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                // Handle the exception appropriately
            }
        } else {
            System.out.println("No item selected.");
        }
    }
    private int parseIdFromSelectedItem(String selectedItem) {
        // Assuming your string representation is in the format "Formation{idFormation=<id>, ...}"
        int startIndex = selectedItem.indexOf("-") + "-".length();
        int endIndex = selectedItem.indexOf("|", startIndex);
        return Integer.parseInt(selectedItem.substring(startIndex, endIndex));
    }
}

