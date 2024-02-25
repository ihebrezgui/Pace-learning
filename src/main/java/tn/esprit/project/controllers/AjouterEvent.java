package tn.esprit.project.controllers;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.project.models.Events;
import tn.esprit.project.services.EventService;
import tn.esprit.project.test.HelloApplication;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class AjouterEvent {

    @FXML
    private TextField nomTF;

    @FXML
    private TextField dateTF;

    @FXML
    private TextField Place_numTF;

    @FXML
    private TextField DescriptionTF;

    public void ajouterEvent(ActionEvent actionEvent) throws SQLException, IOException {



        EventService eventService = new EventService();
        Events event = new Events () ;
        event.setNom(nomTF.getText());
        event.setDate_event(Date.valueOf(dateTF.getText()));
        event.setNbr_place(Integer.parseInt(Place_numTF.getText()));
        event.setDescription(DescriptionTF.getText());


        try {
            eventService.ajouter(event);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("Event ajoutée");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void afficherEvent(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/project/AfficherEvent.fxml"));
        Parent root = fxmlLoader.load();

        // Create the scene
        Scene scene = new Scene(root);

        // Create a new stage
        Stage stage = new Stage();
        stage.setTitle("Afficher Event");
        stage.setScene(scene);

        // Show the stage
        stage.show();
    }
}
