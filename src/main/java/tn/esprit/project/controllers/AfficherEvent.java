package tn.esprit.project.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import tn.esprit.project.models.Events;
import tn.esprit.project.services.EventService;

import java.sql.SQLException;

public class AfficherEvent {
    @FXML
    private ListView<Events> listTF;

    @FXML
    void initialize() {
        EventService eventService = new EventService();
        try{
            listTF.getItems().addAll(eventService.recuperer());

        }catch (SQLException e) {
            e.printStackTrace();
        }



    }




}
