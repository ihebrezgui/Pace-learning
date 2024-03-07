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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ListCours {
    @FXML
    private ListView<Cours> listView;

    @FXML
    private TextField searchField;

    private CoursService cs;
    Formation selectedFormation;

    @FXML
    void initialize() {
        cs = new CoursService();
        refreshListView();
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            List<Cours> searchResults = cs.search(newValue);
            refreshListView(searchResults);
        });

        refreshListView();
        // Set custom cell factory
        this.listView.setCellFactory(list -> new CoursList());

    }
    public void initializeData(List<Cours> formationCourses) {
        cs.getFormationCourses(selectedFormation.getIdFormation());
        refreshList(formationCourses);
    }
    @FXML
    void uploadFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload PDF File");
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            // Add code to handle the selected PDF file, like saving its path to the database
            String filePath = selectedFile.getAbsolutePath();
            System.out.println("Selected file: " + filePath);
        }
    }
    @FXML
    void downloadFile(ActionEvent event) {
        // Retrieve the selected item from the list view
        Cours selectedCours = listView.getSelectionModel().getSelectedItem();
        if (selectedCours != null) {
            String filePath = selectedCours.getcours();
            File file = new File(filePath);
            if (file.exists()) {
                // Use FileChooser to choose the download location
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save PDF File");
                fileChooser.setInitialFileName(file.getName());
                File selectedFile = fileChooser.showSaveDialog(new Stage());
                if (selectedFile != null) {
                    // Copy the file to the selected location
                    try {
                        // You can use any method to copy the file, like Files.copy() or manual byte copying
                        // For simplicity, I'm just renaming the file here
                        boolean success = file.renameTo(selectedFile);
                        if (success) {
                            System.out.println("File downloaded successfully.");
                        } else {
                            System.out.println("Failed to download file.");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("File does not exist: " + filePath);
            }
        } else {
            System.out.println("No item selected.");
        }
    }


    public void setFormation(Formation formation) {
        this.selectedFormation = formation;
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