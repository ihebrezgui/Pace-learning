package esprit.tn.formation.controllers;

import esprit.tn.formation.HelloApplication;
import esprit.tn.formation.models.Formation;
import esprit.tn.formation.services.FormationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

public class UpdateFormation {

    @FXML
    private TextField IdFormation;
    @FXML
    private TextField typeF;
    @FXML
    private TextField imgpath;

    @FXML
    private void update() {
        try {
            // Validate input fields
            if (!validateInput()) {
                return; // Exit method if input is invalid
            }

            FormationService Fs = new FormationService();
            Formation F = new Formation();
            F.setIdFormation(Integer.parseInt(IdFormation.getText()));
            F.setTypeF(typeF.getText());
            F.setImg(imgpath.getText()); // Set the image path

            Fs.update(F);

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
        String idFormationText = IdFormation.getText().trim();
        String typeFText = typeF.getText().trim();
        String imgPathText = imgpath.getText().trim(); // Get the image path

        if (idFormationText.isEmpty() || typeFText.isEmpty() || imgPathText.isEmpty()) {
            showAlert("Error", "All fields must be filled out.");
            return false;
        }

        try {
            Integer.parseInt(idFormationText);
            return true;
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid numeric value for IdFormation.");
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
            IdFormation.getScene().setRoot(fxmlLoader.load());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void getAll() {
        try {
            FormationService Fs = new FormationService();
            List<Formation> formations = Fs.getAll();

            // Update the list view in ListFormation.fxml with the new list of formations
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/esprit/tn/formation/ListFormation.fxml"));
            Parent root = loader.load();
            ListFormation listFormationController = loader.getController();
            //listFormationController.refreshList(formations); // Pass the list of formations to refreshList()
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
