package esprit.tn.formation.controllers;

import esprit.tn.formation.HelloApplication;
import esprit.tn.formation.models.Cours;
import esprit.tn.formation.models.Formation;
import esprit.tn.formation.services.CoursService;
import esprit.tn.formation.services.FormationService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.io.IOException;
import java.util.List;

public class ListCours {
    @FXML
    private ListView<Cours> listView;

    @FXML
    private TextField searchField;

    private CoursService cs;

    @FXML
    void initialize() {
        cs = new CoursService();
        refreshListView();
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            List<Cours> searchResults = cs.search(newValue);
            refreshListView(searchResults);
        });
        // Set custom cell factory
        listView.setCellFactory(list -> new CoursList());
    }

    @FXML
    private void refreshListView() {
        List<Cours> coursList = cs.getAll();
        ObservableList<Cours> observableList = FXCollections.observableArrayList(coursList);
        listView.setItems(observableList);
    }

    @FXML
    void sort(ActionEvent event) {
        List<Cours> courss = cs.getAll();
        cs.sortByPrice(courss);
        ObservableList<Cours> observableList = FXCollections.observableArrayList(courss);
        listView.setItems(observableList);
    }

    @FXML
    void search(ActionEvent event) {
        String searchTerm = searchField.getText();
        List<Cours> searchResults = cs.search(searchTerm);
        refreshListView(searchResults);
    }

    private void refreshListView(List<Cours> coursList) {
        ObservableList<Cours> observableList = FXCollections.observableArrayList(coursList);
        listView.setItems(observableList);
    }

    @FXML
    void delete(ActionEvent event) {
        CoursService Fs = new CoursService();
        boolean b = listView.getItems().removeAll(listView.getSelectionModel().getSelectedItem());
        if(b)
        {
            try {
                int id = listView.getSelectionModel().getSelectedItem().getIdCours();
                Fs.delete(id);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success");
                alert.setContentText("Course deleted");
                alert.showAndWait();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
                System.err.println((e.getMessage()));
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void update(ActionEvent event) {
        Cours selectedFormation = listView.getSelectionModel().getSelectedItem();
        if (selectedFormation != null) {
            try {
                System.out.println(selectedFormation.getIdCours());
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/esprit/tn/formation/UpdateCours.fxml"));
                Parent root = fxmlLoader.load();
                UpdateCours updateFormationController = fxmlLoader.getController();
                updateFormationController.initializeData(selectedFormation.getIdCours());
                listView.getScene().setRoot(root);
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
                System.err.println((e.getMessage()));
                throw new RuntimeException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a formation to update");
            alert.showAndWait();
        }
    }

    public void refreshList(List<Cours> cours) {
        // Clear existing items
        listView.getItems().clear();

        // Add new courses to the ListView
        listView.getItems().addAll(cours);
    }

    public List<Cours> getAll() {
        CoursService Fs = new CoursService();
        return Fs.getAll();
    }

    public void setCours(CoursService coursService) {
        this.cs = coursService;
    }
}