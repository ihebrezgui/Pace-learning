package com.example.guser.controller;

import com.example.guser.models.Utilisateur;
import com.example.guser.services.UtilisateurService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.converter.StringConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.TextFieldListCell;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AfficherUtilisateur {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listView;
    @FXML
    void modifierU(ActionEvent event) throws IOException {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            TextInputDialog dialog = new TextInputDialog(listView.getSelectionModel().getSelectedItem());
            dialog.setTitle("Modify Item");
            dialog.setHeaderText(null);
            dialog.setContentText("Enter the modified value:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(modifiedValue -> {
                // Modify the selected item in the ListView
                listView.getItems().set(selectedIndex, modifiedValue);
            });
        } else {
            // Show an alert if no item is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select an item to modify.");
            alert.showAndWait();
        }
    }

    @FXML
    void supprimerU(ActionEvent event) throws IOException {

    listView.getItems().removeAll(listView.getSelectionModel().getSelectedItems());

    }

    @FXML
    void initialize() {
        UtilisateurService utilisateurservice = new UtilisateurService();
        try {
            List<Utilisateur> utilisateur = utilisateurservice.recuperer();
            ObservableList<String> userDataList = FXCollections.observableArrayList();
            for (Utilisateur utilisateurs : utilisateur) {
                String utilisateurData = String.format("%-20s %-20s %-20s %-20s %-20s %-20s",
                        utilisateurs.getNom(),
                        utilisateurs.getPrenom(),
                        utilisateurs.getDate_nais(),
                        utilisateurs.getNum_tel(),
                        utilisateurs.getEmail(),
                        utilisateurs.getSexe()
                );
                userDataList.add(utilisateurData);
            }

            listView.setItems(userDataList);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}


