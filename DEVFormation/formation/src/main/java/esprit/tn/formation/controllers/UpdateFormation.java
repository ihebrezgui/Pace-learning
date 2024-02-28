package esprit.tn.formation.controllers;


import esprit.tn.formation.HelloApplication;
import esprit.tn.formation.models.Formation;
import esprit.tn.formation.services.FormationService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.List;

public class UpdateFormation {

    @FXML
    private TextField duree;
    @FXML
    private TextField prix;
    @FXML
    private ComboBox<String> typeF,status;
    @FXML
    private TextField imgpath;

    @FXML
    private ImageView imageView;
    private int selectedFormationId;

    public void initializeData(int formationId) {
        this.selectedFormationId = formationId;
        // Fetch the formation details based on the ID and populate the fields for updating
        FormationService formationService = new FormationService();
        Formation formation = formationService.searchidF(formationId);
        if (formation != null) {
            prix.setText(String.valueOf(formation.getPrix()));
            typeF.setValue(formation.getTypeF());
            duree.setText(formation.getDuree());
            imgpath.setText(formation.getImg());
            status.setValue(formation.getStatus());
            // You may need to load the image here if it's displayed
        } else {
            showAlert("Error", "Formation not found.");
        }
    }

    @FXML
    private void update() {
        try {
            // Validate input fields
            if (!validateInput()) {
                return; // Exit method if input is invalid
            }

            FormationService Fs = new FormationService();
            Formation F = new Formation();
            F.setIdFormation(selectedFormationId);
            F.setPrix(Float.parseFloat(prix.getText()));
            F.setTypeF(typeF.getValue());
            F.setDuree(duree.getText());
            F.setImg(imgpath.getText()); // Set the image path
            F.setStatus(status.getValue());
            Fs.update(F);
            System.out.println(imgpath.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("Formation updated");
            alert.showAndWait();

            // Refresh the list of formations
            // refreshList();
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid numeric value for IdFormation.");
        } catch (Exception e) {
            showAlert("Error", e.getMessage());
            System.err.println((e.getMessage()));
            throw new RuntimeException(e);
        }
    }

    private boolean validateInput() {
        String DureeText = duree.getText().trim();
        String prixText = prix.getText().trim();
        String typeFText = typeF.getValue().trim();
        String imgPathText = imgpath.getText().trim(); // Get the image path

        if (DureeText.isEmpty() || typeFText.isEmpty() || imgPathText.isEmpty() || prixText.isEmpty()) {
            showAlert("Error", "All fields must be filled out.");
            return false;
        }

        try {
            Float.parseFloat(prixText);
            return true;
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid numeric value for prix.");
            return false;
        }
    }
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Method to refresh the list of formations
    /*private void refreshList() {
        try {
            FormationService Fs = new FormationService();
            List<Formation> formations = Fs.getAll();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/esprit/tn/formation/ListFormation.fxml"));
            Parent root = loader.load();
            ListFormation listFormationController = loader.getController();
            listFormationController.refreshList(formations); // Pass the list of formations to refreshList()
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    // Method to navigate back to the list of formations
    @FXML
    void goToFormationList(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/esprit/tn/formation/ListFormation.fxml"));
        try {
            typeF.getScene().setRoot(fxmlLoader.load());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void initialize() {
        imageView = new ImageView();
        typeF.setItems(FXCollections.observableArrayList(
                "Devops",
                "Sim",
                "GL",
                "Santé",
                "Math et logique",
                "Devloppement Personel",
                "Data Science ",
                "Culture générale",
                "Finance"
        ));
        status.setItems(FXCollections.observableArrayList(
                "En cours",
                "En attente",
                "Terminé",
                "Archivé",
                "Evalué",
                "Non evalué"
        ));
    }
    public void loadImage(ActionEvent event) {
        String imagePath = imgpath.getText();
        if (!imagePath.isEmpty()) {
            try {
                Image image = new Image("file:///" + imagePath);
                imageView.setImage(image);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Impossible de charger l'image.");
                alert.showAndWait();
            }
        }
    }
    public void getAll() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/esprit/tn/formation/ListFormation.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
