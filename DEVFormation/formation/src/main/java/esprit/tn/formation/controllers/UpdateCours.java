package esprit.tn.formation.controllers;


import esprit.tn.formation.HelloApplication;
import esprit.tn.formation.models.Cours;
import esprit.tn.formation.models.Formation;
import esprit.tn.formation.services.CoursService;
import esprit.tn.formation.services.FormationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.io.IOException;
import java.util.List;

public class UpdateCours {


    @FXML
    private TextField tabF;
    @FXML
    private TextField tabI;
    @FXML
    private TextField tabC;
    @FXML
    private TextField tabD;
    @FXML
    private TextField tabN;
    @FXML
    private TextField tabP;

    private int selectedCoursId;

    public void initializeData(int CoursId) {
        this.selectedCoursId = CoursId;
        // Fetch the formation details based on the ID and populate the fields for updating
        CoursService CoursService = new CoursService();
        Cours cours = CoursService.searchidF(CoursId);
        if (cours != null) {
            tabI.setText(String.valueOf(cours.getIdCours()));
            tabN.setText(cours.getnomCours());
            tabD.setText(cours.getDescription());
            tabC.setText(cours.getCategorie());
            tabP.setText(cours.getcours());
            tabF.setText(String.valueOf(cours.getIdFormation()));
        } else {
            showAlert("Error", "Course not found.");
        }
    }
    @FXML
    private void update() {
        try {
            // Validate input fields
            if (!validateInput()) {
                return; // Exit method if input is invalid
            }

            CoursService Fs = new CoursService();
            Cours F = new Cours();
            F.setIdCours(selectedCoursId);
            F.setIdFormation(Integer.parseInt(tabF.getText()));
            F.setnomCours(tabN.getText());
            F.setDescription(tabD.getText());
            F.setCategorie(tabC.getText());
            F.setcours(tabP.getText());
            Fs.update(F);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("Cours updated");
            alert.showAndWait();
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter valid numeric values for IdCours, IdFormation, and Prix.");
        } catch (Exception e) {
            showAlert("Error", e.getMessage());
            System.err.println((e.getMessage()));
            throw new RuntimeException(e);
        }
    }

    private boolean validateInput() {
        String idFormationText = tabF.getText().trim();
        String prixText = tabP.getText().trim();

        if ( idFormationText.isEmpty() || prixText.isEmpty()) {
            showAlert("Error", "All fields must be filled out.");
            return false;
        }

        try {
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
