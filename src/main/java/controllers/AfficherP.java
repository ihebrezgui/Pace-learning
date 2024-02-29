package controllers;

import entities.partnerships;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import services.ServicePartnerships;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AfficherP {
    @FXML
    private Button backp;

    @FXML
    private ListView<String> partnershipsListView;

    @FXML
    private Label back;
    private final ServicePartnerships ps = new ServicePartnerships();



    @FXML
    void Afficherp(ActionEvent event) {
        try {
            List<partnerships> partnerships1 = ps.afficher();

            // Clear any existing items in the ListView
            partnershipsListView.getItems().clear();

            // Add each Formation object to the ListView
            for (partnerships partnerships2 : partnerships1) {
                partnershipsListView.getItems().add(partnerships2.toString());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void backp(ActionEvent event) {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/partnerships.fxml"));
        try {
            Parent root = loader1.load();
            Partnerships controller = loader1.getController();
            back.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void modifierp(ActionEvent event) {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/modifierP.fxml"));
        try {
            Parent root = loader1.load();
            ModifierP controller = loader1.getController();
            back.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void supprimerp(ActionEvent actionEvent) {
        String selectedItem = partnershipsListView.getSelectionModel().getSelectedItem();

// Get the selected Formation object from the ListView
        if (selectedItem != null) {
            // Parse the ID from the selected item
            int id = parseIdFromSelectedItem(selectedItem);

            try {
                // Delete the Formation from the database
                ps.supprimer(new partnerships(id, null, null, 0)); // Create a temporary Formation object with only the ID
                // Refresh the ListView
                Afficherp(actionEvent);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                // Handle the exception appropriately
            }
        } else {
            System.out.println("No item selected.");
        }
    }

    // Helper method to parse the ID from the string representation of a Formation object
    private int parseIdFromSelectedItem(String selectedItem) {
        // Assuming your string representation is in the format "Formation{idFormation=<id>, ...}"
        int startIndex = selectedItem.indexOf("-") + "-".length();
        int endIndex = selectedItem.indexOf("|", startIndex);
        return Integer.parseInt(selectedItem.substring(startIndex, endIndex));
    }

}
