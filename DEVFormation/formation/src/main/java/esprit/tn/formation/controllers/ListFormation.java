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
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ListFormation {

    @FXML
    private ListView<Formation> listView;

    @FXML
    private TextField searchField;

    private FormationService formationService;
    private String email,password;

    private int selectedFormationId;
    @FXML
    void initialize(String email,String password) {
        formationService = new FormationService();
        refreshList();
        this.email=email;
        this.password=password;
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
                    // setText(formation.getTypeF());
                    setText( " "+formation.getTypeF() + "                      " +
                            " " + String.valueOf(formation.getPrix())+ " TND                      " +
                            " " + formation.getDuree() + "                      ");
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
        formationService.sortByPrice(formations);
        refreshListView(formations);
    }

    @FXML
    void search(ActionEvent event) {
        String searchTerm = searchField.getText();
        List<Formation> searchResults = formationService.search(searchTerm);
        refreshListView(searchResults);
    }
    @FXML
    void Detailler(MouseEvent event) {
        // Add print statement to check if the method is called
        System.out.println("Detailler method called");

        // Check if an item is selected in the ListView
        Formation selectedFormation = listView.getSelectionModel().getSelectedItem();
        if (selectedFormation != null) {
            // Print the selectedFormation to verify it's not null
            System.out.println("Selected Formation: " + selectedFormation);

            try {
                CoursService cs = new CoursService();
                // Print the id of the selected formation
                System.out.println("Selected Formation ID: " + selectedFormation.getIdFormation());

                // Call getFormationCourses and print the result
                List<Cours> formationCourses = cs.getFormationCourses(selectedFormation.getIdFormation());
                System.out.println("Formation Courses: " + formationCourses);

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/esprit/tn/formation/ListCours.fxml"));
                Parent root = fxmlLoader.load();
                ListCours listCoursController = fxmlLoader.getController();
                listCoursController.setFormation(selectedFormation);
                listCoursController.initializeData(formationCourses);
                listView.getScene().setRoot(root);
            } catch (IOException e) {
                // Print the exception message if an IOException occurs
                System.err.println("IOException: " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
                throw new RuntimeException(e);
            }
        } else {
            // Add print statement to check if no formation is selected
            System.out.println("No formation selected");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a formation to detail");
            alert.showAndWait();
        }
    }


    @FXML
    void update(MouseEvent event) {
        Formation selectedFormation = listView.getSelectionModel().getSelectedItem();
        if (selectedFormation != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/esprit/tn/formation/UpdateFormation.fxml"));
                Parent root = fxmlLoader.load();
                UpdateFormation updateFormationController = fxmlLoader.getController();
                updateFormationController.initializeData(selectedFormation.getIdFormation());
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

    @FXML
    void add(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/esprit/tn/formation/AddFormation.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/esprit/tn/formation/DeleteFormation.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    @FXML
    void delete(ActionEvent event) {

        FormationService Fs = new FormationService();
        boolean b = listView.getItems().removeAll(listView.getSelectionModel().getSelectedItem());
        if(b) {
            try {
                int id = listView.getSelectionModel().getSelectedItem().getIdFormation();
                Fs.delete(id);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success");
                alert.setContentText("Formation deleted");
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
    }*/
    @FXML
    void delete(MouseEvent  event) {
        Formation selectedFormation = listView.getSelectionModel().getSelectedItem();
        if (selectedFormation != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/esprit/tn/formation/verification.fxml"));
                Parent root = fxmlLoader.load();
                Deleteverif deleteConfirmationController = fxmlLoader.getController();

                // Pass the selectedFormation to the DeleteConfirmationController
                deleteConfirmationController.setFormation(selectedFormation);

                // Display the confirmation dialog
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait(); // Wait for user confirmation before proceeding

                // If user confirms deletion
                if (deleteConfirmationController.isConfirmed()) {
                    FormationService Fs = new FormationService();
                    int id = selectedFormation.getIdFormation();
                    System.out.println(id);

                    // Attempt deletion
                    Fs.delete(id);

                    // Check if the item is still present in the list
                    if (!listView.getItems().contains(selectedFormation)) {
                        refreshList(); // Refresh the list view after deletion
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Success");
                        alert.setContentText("Formation deleted");
                        alert.showAndWait();
                    } else {
                        // Deletion failed
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setContentText("Failed to delete formation");
                        alert.showAndWait();
                    }
                }
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
            alert.setContentText("Please select a formation to delete");
            alert.showAndWait();
        }
    }

    @FXML
    void PassQuiz(MouseEvent event) {
        Formation selectedFormation = listView.getSelectionModel().getSelectedItem();
        if (selectedFormation != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/esprit/tn/formation/quiz.fxml"));
                Parent root = fxmlLoader.load();
                QuizController quizController = fxmlLoader.getController();
                quizController.initialize(selectedFormation,email,password);

                // Obtenez la scène à partir de n'importe quel élément de la fenêtre actuelle
                Scene scene = listView.getScene();

                // Changez la racine de la scène actuelle
                scene.setRoot(root);
            } catch (IOException e) {
                // Gérer les exceptions d'E/S lors du chargement du fichier FXML
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Failed to load quiz page");
                alert.showAndWait();
            }
        } else {
            // Affichez une alerte si aucune formation n'est sélectionnée
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a formation to pass the quiz");
            alert.showAndWait();
        }
    }



    public void refreshList() {
        List<Formation> formations = formationService.getAll();
        refreshListView(formations);
    }

}
