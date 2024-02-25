package com.example.guser.controller;

import com.example.guser.models.Code_promo;
import com.example.guser.services.CodeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.sql.SQLException;
import java.util.List;

public class AfficherCode {

    @FXML
    private ListView<String> listeCode;

 /*   @FXML
    void initialize() {
        CodeService codeservice = new CodeService();
        try {
            listeCode.getItems().addAll(codeservice.recuperer());
        } catch (SQLException e) {
            // Handle the exception (log or show an error message)
            e.printStackTrace();
        }

    }*/
 @FXML
 void initialize() {
     CodeService codeService= new CodeService();
     try {
         List<Code_promo> codePromos = codeService.recuperer();

         // Create an ObservableList to store the event data
         ObservableList<String> codeDataList = FXCollections.observableArrayList();

         // Add column titles
         String columnTitles = String.format("%-20s %-20s %-20s %-20s %-20s", "Code", "Date d'expiration", "Active", "ID User", "User");
         codeDataList.add(columnTitles);

         // Iterate through the list of events and add their details to the eventDataList
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

         // Set custom ListCell to format the data in columns
         listeCode.setCellFactory(listeCode -> new ColumnListViewCell());

     } catch (SQLException e) {
         System.err.println(e.getMessage());
     }
 }
}
