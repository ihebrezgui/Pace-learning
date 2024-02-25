package com.example.guser.controller;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import com.example.guser.models.Utilisateur;
import com.example.guser.services.UtilisateurService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
public class AfficherUtilisateur {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listView;
    @FXML
    void initialize() {
        UtilisateurService utilisateurservice = new UtilisateurService();
        try {
            List<Utilisateur> utilisateur = utilisateurservice.recuperer();

            // Create an ObservableList to store the event data
            ObservableList<String> userDataList = FXCollections.observableArrayList();

            // Add column titles
            String columnTitles = String.format("%-20s %-20s %-20s %-20s %-20s %-20s", "Nom", "Prenom", "Date de naissance", "Numéro de téléphone", "E-mail", "Sexe");
            userDataList.add(columnTitles);

            // Iterate through the list of events and add their details to the eventDataList
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

            // Set custom ListCell to format the data in columns
            listView.setCellFactory(listView -> new ColumnListViewCell());

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}


