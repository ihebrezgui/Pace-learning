package controllers;

import entities.enseignant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import services.ServiceEnseignant;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AfficherE {
    //////////

    ///////////
    @FXML
    private ListView<String> EnseignantListView;

    @FXML
    private Button modifierEN;

    @FXML
    private Label next;



    @FXML
    private Label next2;
    @FXML
    private Button BACKAJOUTE;

    private List<enseignant> enseignants;



    private final ServiceEnseignant ps = new ServiceEnseignant();

    @FXML
    void AfficherDB(ActionEvent event) {
        try {
            List<enseignant> enseignants = ps.afficher();

            // Clear any existing items in the ListView
            EnseignantListView.getItems().clear();

            // Add each Formation object to the ListView
            for (enseignant enseignant : enseignants) {
                EnseignantListView.getItems().add(enseignant.toString());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void deleteE(ActionEvent actionEvent) {
        String selectedItem = EnseignantListView.getSelectionModel().getSelectedItem();

// Get the selected Formation object from the ListView
        if (selectedItem != null) {
            // Parse the ID from the selected item
            int id = parseIdFromSelectedItem(selectedItem);

            try {
                // Delete the Formation from the database
                ps.supprimer(new enseignant(id, 0, null, null, null, null, null, null)); // Create a temporary Formation object with only the ID
                // Refresh the ListView
                AfficherDB(actionEvent);
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

    @FXML
    void modifierE(ActionEvent event) {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/modifierE.fxml"));
        try {
            Parent root = loader1.load();
            ModifierE controller = loader1.getController();
            next.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    @FXML
    void BACKAJOUTE(ActionEvent event) {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/ajouterE.fxml"));
        try {
            Parent root = loader1.load();
            AjouterE controller = loader1.getController();
            next2.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    void trierE(ActionEvent event) {
        try {
            List<enseignant> enseignants = ps.afficher1();

            // Clear any existing items in the ListView
            EnseignantListView.getItems().clear();

            // Add each Formation object to the ListView
            for (enseignant enseignant : enseignants) {
                EnseignantListView.getItems().add(enseignant.toString());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    }






