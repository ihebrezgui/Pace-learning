package esprit.tn.formation.controllers;

import esprit.tn.formation.HelloApplication;
import esprit.tn.formation.models.Cours;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import esprit.tn.formation.models.Formation;
import esprit.tn.formation.services.FormationService;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AddFormation {
    @FXML
    private TextField duree;
    @FXML
    private TextField prix;
    @FXML
    private ComboBox<String> typeF;
    @FXML
    private ComboBox<String> Status;
    @FXML
    private TextField imgPath;

    private List<Cours> cours;

    @FXML
    private ImageView imageView;

    @FXML
    void add(ActionEvent event) {
        if (!validateInput()) {
            return;
        }
        FormationService Fs = new FormationService();
        Formation F = new Formation();
        F.setPrix(Float.parseFloat(prix.getText()));
        F.setTypeF(typeF.getValue());
        F.setImg(imgPath.getText());
        F.setDuree(duree.getText());
        F.setStatus(Status.getValue());
        try {
            String prixText = prix.getText();
            String typeFText = typeF.getValue();
            String statusText = Status.getValue();
            String dureeText = duree.getText();
            Fs.add(F);
            Alert Alert = new Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
            Alert.setTitle("Success");
            Alert.setContentText("Formation added");

            Alert.showAndWait();

        } catch (Exception e) {
            Alert Alert = new Alert(javafx.scene.control.Alert.AlertType.ERROR);
            Alert.setTitle("Erreur");
            Alert.setContentText(e.getMessage());
            Alert.showAndWait();
            System.err.println((e.getMessage()));
            throw new RuntimeException(e);
        }
    }

    @FXML
    void getAll(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/esprit/tn/formation/ListFormation.fxml"));
        try {
            typeF.getScene().setRoot(fxmlLoader.load());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private boolean validateInput() {
        String prixText = prix.getText();
        String typeFText = typeF.getValue();
        String imgPathText = imgPath.getText();
        String dureeText = duree.getText();
        //String statusText = Status.getValue();
        if (prixText.isEmpty() || typeFText.isEmpty() || imgPathText.isEmpty() || dureeText.isEmpty()) {
            showAlert("Erreur", "Les champs ne peuvent pas être vides.");
            return false;
        }
        if (!isNumeric(prixText)) {
            showAlert("Erreur", "Le prix de la formation doit être numérique.");
            return false;
        }
        if (prixText.isEmpty()) {
            showAlert("Erreur", "Le prix de la formation ne peut pas être vide.");
            return false;
        }
        if (typeFText.isEmpty()) {
            showAlert("Erreur", "Le type de formation ne peut pas être vide.");
            return false;
        }
        if (imgPathText.isEmpty()) {
            showAlert("Erreur", "Le chemin de l'image ne peut pas être vide.");
            return false;
        }
        if (dureeText.isEmpty()) {
            showAlert("Erreur", "Le chemin de l'image ne peut pas être vide.");
            return false;
        }
        return true;
    }

        private void showAlert(String title, String content) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();
        }
    public void initialize() {
        //imageView = new ImageView();
        Status.setItems(FXCollections.observableArrayList(
                "En cours",
                "En attente",
                "Terminé",
                "Archivé",
                "Evalué",
                "Non evalué"
        ));
        typeF.setItems(FXCollections.observableArrayList(
                "Devops",
                "Mobile",
                "GL",
                "Santé",
                "Math et logique",
                "Devloppement Personel",
                "Data Science ",
                "Culture générale",
                "Finance"
        ));

    }


}
