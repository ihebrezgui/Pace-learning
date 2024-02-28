package com.example.guser.controller;

import com.example.guser.models.Code_promo;
import com.example.guser.services.CodeService;
import com.example.guser.test.mainFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AfficherCode {

    @FXML
    private ListView<String> listeCode;


    @FXML
    void modifierC(ActionEvent event) throws IOException {
        int selectedIndex = listeCode.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            TextInputDialog dialog = new TextInputDialog(listeCode.getSelectionModel().getSelectedItem());
            dialog.setTitle("Modify Item");
            dialog.setHeaderText(null);
            dialog.setContentText("Enter the modified value:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(modifiedValue -> {
                // Modify the selected item in the ListView
                listeCode.getItems().set(selectedIndex, modifiedValue);
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
    void supprimerC(ActionEvent event) throws IOException {
        listeCode.getItems().removeAll(listeCode.getSelectionModel().getSelectedItems());


    }
 @FXML
 void initialize() {
     CodeService codeService= new CodeService();
     try {
         List<Code_promo> codePromos = codeService.recuperer();
         ObservableList<String> codeDataList = FXCollections.observableArrayList();
         for (Code_promo codes : codePromos) {
             String codeData = String.format("%-20s %-20s %-20s %-20s %-20s",
                     codes.getCode(),
                     codes.getDate_expiration(),
                     codes.getActive(),
                     codes.getIdUser(),
                     codes.getUser()

             );
             codeDataList.add(codeData);
         }

         listeCode.setItems(codeDataList);


     } catch (SQLException e) {
         System.err.println(e.getMessage());
     }
 }
}
