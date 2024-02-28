package esprit.tn.formation.controllers;

import esprit.tn.formation.HelloApplication;
import esprit.tn.formation.models.Cours;
import esprit.tn.formation.services.CoursService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

public class UpdateCours {

    @FXML
    private TextField tabI;
    @FXML
    private TextField tabF;

    @FXML
    private TextField tabC;
    @FXML
    private TextField tabD;
    @FXML
    private TextField tabN;
    @FXML
    private TextField tabP;


    @FXML
    private void update() {
        try {
            // Validate input fields
            if (!validateInput()) {
                return; // Exit method if input is invalid
            }

            CoursService Fs = new CoursService();
            Cours F = new Cours();
            F.setIdCours(Integer.parseInt(tabI.getText()));
            F.setIdFormation(Integer.parseInt(tabF.getText()));
            F.setNomC(tabN.getText());
            F.setDescription(tabD.getText());
            F.setCategorie(tabC.getText());
            F.setPrix(Float.parseFloat(tabP.getText()));

            Fs.update(F);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("Cours updated");
            alert.showAndWait();

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/esprit/tn/formation/ListCours.fxml"));
            tabI.getScene().setRoot(fxmlLoader.load());
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter valid numeric values for IdCours, IdFormation, and Prix.");
        } catch (Exception e) {
            showAlert("Error", e.getMessage());
            System.err.println((e.getMessage()));
            throw new RuntimeException(e);
        }
    }

    private boolean validateInput() {
        String idCoursText = tabI.getText().trim();
        String idFormationText = tabF.getText().trim();
        String prixText = tabP.getText().trim();

        if (idCoursText.isEmpty() || idFormationText.isEmpty() || prixText.isEmpty()) {
            showAlert("Error", "All fields must be filled out.");
            return false;
        }

        try {
            Integer.parseInt(idCoursText);
            Integer.parseInt(idFormationText);
            Float.parseFloat(prixText);
            return true;
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter valid numeric values for IdCours, IdFormation, and Prix.");
            return false;
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }


    // Method to refresh the list of Courss
    private void refreshList() {
        try {
            CoursService Fs = new CoursService();
            List<Cours> Courss = Fs.getAll();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/esprit/tn/formation/ListCours.fxml"));
            Parent root = loader.load();
            ListCours listCoursController = loader.getController();
            listCoursController.refreshList(Courss); // Pass the list of Courss to refreshList()
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to navigate back to the list of Courss
    @FXML


    public void getAll() {
        try {
            CoursService Fs = new CoursService();
            List<Cours> Courss = Fs.getAll();

            // Update the list view in ListCours.fxml with the new list of Courss
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/esprit/tn/formation/ListCours.fxml"));
            Parent root = loader.load();
            ListCours listCoursController = loader.getController();
            listCoursController.refreshList(Courss); // Pass the list of Courss to refreshList()
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
