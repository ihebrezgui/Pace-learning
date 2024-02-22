package controllers;

import entities.enseignant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import services.ServiceEnseignant;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AfficherE {
    @FXML
    private ListView<String> EnseignantListView;

    @FXML
    private Button modifierEN;

    @FXML
    private Label next;


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
// Get the selected Formation object from the ListView
        String selectedItem = EnseignantListView.getSelectionModel().getSelectedItem();
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
        int startIndex = selectedItem.indexOf("idE=") + "idE=".length();
        int endIndex = selectedItem.indexOf(",", startIndex);
        return Integer.parseInt(selectedItem.substring(startIndex, endIndex));
    }

    @FXML
    void modifierE(ActionEvent actionEvent) {
        // Get the selected Categorie object from the ListView
        String selectedItem = EnseignantListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Parse the ID from the selected item
            int id = parseIdFromSelectedItem(selectedItem);

            // Show a dialog to prompt the user for the new category name
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Modifier enseignant");
            dialog.setHeaderText("Modifier le nom de la enseignant");
            dialog.setContentText("Nouveau nom:");

            // Get user input
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(newNom -> {
                try {
                    // Create a new Categorie object with the updated name
                    enseignant updatedenseignant = new enseignant(id,0,"gfhgjh@gmail.com","info",newNom, "chaabi","jawi behi","3arbi");
                    // Call the modifier method in your ServiceCategorie class
                    ps.modifier(updatedenseignant);
                    // Refresh the ListView
                    AfficherDB(actionEvent);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    // Handle the exception appropriately
                }
            });
        } else {
            System.out.println("No item selected.");
        }
        }

    }






