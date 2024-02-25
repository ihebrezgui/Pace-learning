package tn.esprit.project.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import tn.esprit.project.models.Planning;
import tn.esprit.project.services.PlanningService;

import java.sql.SQLException;

public class AfficherPlanning {
    @FXML
    private ListView<Planning> listPlan;

    @FXML
    void initialize() {
        PlanningService planningService = new PlanningService();
        try{
            listPlan.getItems().addAll(planningService.recuperer());

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
