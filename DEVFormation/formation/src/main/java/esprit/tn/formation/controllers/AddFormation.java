package esprit.tn.formation.controllers;

import esprit.tn.formation.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import esprit.tn.formation.models.Formation;
import esprit.tn.formation.services.FormationService;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class AddFormation {
    @FXML
    private TextField IdFormation;

    @FXML
    private TextField typeF;

    @FXML
    private TextField imgPath;

    @FXML
    private ImageView imageView;

    @FXML
    void add(ActionEvent event) {
        if (!validateInput()) {
            return;
        }
        FormationService Fs = new FormationService();
        Formation F = new Formation();
        F.setIdFormation(Integer.parseInt(IdFormation.getText()));
        F.setTypeF(typeF.getText());
        F.setImg(imgPath.getText());
        try {
            String idFormationText = IdFormation.getText();
            String typeFText = typeF.getText();
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
            IdFormation.getScene().setRoot(fxmlLoader.load());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private boolean validateInput() {
        String idFormationText = IdFormation.getText();
        String typeFText = typeF.getText();
        if (!isNumeric(idFormationText)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("L'identifiant de la formation doit être numérique.");
            alert.showAndWait();
            return false;
        }
        if (idFormationText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("L'identifiant de la formation ne peut pas être vide.");
            alert.showAndWait();
            return false;
        }
        if (typeFText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le type de formation ne peut pas être vide.");
            alert.showAndWait();
            return false;
        }
        if (idFormationText.isEmpty() || typeFText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Les deux champs ne peuvent pas être vides.");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    public void initialize() {
        imageView = new ImageView();
    }

    public void loadImage(ActionEvent event) {
        String imagePath = imgPath.getText();
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
}
