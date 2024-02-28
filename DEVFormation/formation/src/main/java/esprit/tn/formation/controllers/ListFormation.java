package esprit.tn.formation.controllers;

import esprit.tn.formation.models.Formation;
import esprit.tn.formation.services.FormationService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ListCell;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ListFormation {

    @FXML
    private ListView<Formation> listView;

    @FXML
    private TextField searchField;

    private FormationService formationService;

    @FXML
    void initialize() {
        formationService = new FormationService();
        refreshList();
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            List<Formation> searchResults = formationService.search(newValue);
            refreshListView(searchResults);
        });

        // Set up cell factory to display images
        listView.setCellFactory(list -> new ListCell<Formation>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(Formation formation, boolean empty) {
                super.updateItem(formation, empty);

                if (empty || formation == null) {
                    setGraphic(null);
                } else {
                    // Load and display the image
                    String imagePath = formation.getImg();
                    if (imagePath != null && !imagePath.isEmpty()) {
                        try {
                            Image image = new Image(imagePath);
                            imageView.setImage(image);
                            imageView.setFitWidth(120); // Adjust image width as needed
                            imageView.setFitHeight(100); // Adjust image height as needed
                            setGraphic(imageView);
                        } catch (IllegalArgumentException e) {
                            // Handle invalid image path
                            System.err.println("Invalid image path: " + imagePath);
                            setGraphic(null);
                        }
                    } else {
                        // If image path is empty, clear the graphic
                        setGraphic(null);
                    }

                    // Display the formation name
                    setText(formation.getTypeF());
                }
            }
        });
    }

    private void refreshListView(List<Formation> formations) {
        ObservableList<Formation> observableList = FXCollections.observableArrayList(formations);
        listView.setItems(observableList);
    }

    @FXML
    void sort(ActionEvent event) {
        List<Formation> formations = formationService.getAll();
        formationService.sortByCategorie(formations);
        refreshListView(formations);
    }

    @FXML
    void search(ActionEvent event) {
        String searchTerm = searchField.getText();
        List<Formation> searchResults = formationService.search(searchTerm);
        refreshListView(searchResults);
    }

    @FXML
    void update(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/esprit/tn/formation/UpdateFormation.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void delete(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/esprit/tn/formation/DeleteFormation.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshList() {
        List<Formation> formations = formationService.getAll();
        refreshListView(formations);
    }
}
