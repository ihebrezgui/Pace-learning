package esprit.tn.formation.controllers;

import esprit.tn.formation.models.Cours;
import esprit.tn.formation.models.Formation;
import esprit.tn.formation.services.CoursService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/esprit/tn/formation/DeleteCours.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void update(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/esprit/tn/formation/UpdateCours.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
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