package esprit.tn.formation.controllers;

import esprit.tn.formation.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import esprit.tn.formation.models.Cours;
import esprit.tn.formation.services.CoursService;

import java.io.IOException;

public class AddCours {


    @FXML
    private TextField tabF;

    @FXML
    private TextField tabN;

    @FXML
    private TextField tabD;

    @FXML
    private TextField tabC;

    @FXML
    private TextField tabP;

    private boolean validateInput() {
        String idFormationText = tabF.getText();
        String nomCoursText = tabN.getText();
        String descriptionText = tabD.getText();
        String categorieText = tabC.getText();
        String CoursText = tabP.getText();

        if (idFormationText.isEmpty() || nomCoursText.isEmpty() || descriptionText.isEmpty() || categorieText.isEmpty() || CoursText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Tous les champs doivent être remplis.");
            alert.showAndWait();
            return false;
        }

        if (!isNumeric(idFormationText)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("L'identifiant du formation doit être numérique.");
            alert.showAndWait();
            return false;
        }

        if (!isFloat(CoursText)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le Cours doit être un nombre à virgule.");
            alert.showAndWait();
            return false;
        }

        return true;
    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private boolean isFloat(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    @FXML
    void add(ActionEvent event) {
        if (!validateInput()) {
            return;
        }

        CoursService coursService = new CoursService();
        Cours cours = new Cours();
        cours.setIdFormation(Integer.parseInt(tabF.getText()));
        cours.setnomCours(tabN.getText());
        cours.setDescription(tabD.getText());
        cours.setCategorie(tabC.getText());
        cours.setcours((tabP.getText()));
        try {
            coursService.add(cours);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("Cours added");
            alert.showAndWait();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Invalid price format");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            throw new RuntimeException(e);
        }
    }

    @FXML
    void getAll(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/esprit/tn/formation/ListCours.fxml"));
        try {
            tabN.getScene().setRoot(fxmlLoader.load());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
